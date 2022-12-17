package service

import domain.SensorReading

import scala.collection.mutable.ListBuffer

class BeaconMapper:

  def getManhattanDistance(start: (Int, Int), end: (Int, Int)): Int =
    (start._1 - end._1).abs + (start._2 - end._2).abs

  def getSensorRangeForRow(reading: SensorReading, row: Int): Option[Range] =
    val sensorToBeacon = getManhattanDistance(reading.sensorPos, reading.beaconPos)
    val sensorToRow = getManhattanDistance(reading.sensorPos, (reading.sensorPos._1, row))
    val distanceDiff = sensorToBeacon - sensorToRow
    if (distanceDiff < 0) return None
    val rangeStart = reading.sensorPos._1 - distanceDiff
    val rangeEnd = reading.sensorPos._1 + distanceDiff
    val range = reading.beaconPos._1 match
      case x if x == rangeStart => rangeStart + 1 to rangeEnd
      case x if x == rangeEnd => rangeStart until rangeEnd
      case _ => rangeStart to rangeEnd
    if (range.nonEmpty) Some(range) else None

  def getNonBeaconPositionsForRow(readings: Array[SensorReading], row: Int): Int =
    getMinimumNonBeaconRanges(readings, row)
      .foldLeft(0)((acc, r) => acc + r.length)

  def getMinimumNonBeaconRanges(readings: Array[SensorReading], row: Int): List[Range] =
    val result = ListBuffer[Range]()
    val ranges = readings.flatMap(getSensorRangeForRow(_, row))
    val valuesWithFlags = ranges
      .flatMap(r => List((r.head, 1), (r.last, -1)))
      .sortBy((v, f) => (v,-f))
    var counter = 0
    var rangeStart = 0
    for (pair <- valuesWithFlags)
      val prevCount = counter
      counter += pair._2
      counter match
        case c if c != 0 && prevCount == 0 =>
          rangeStart =
          if (result.nonEmpty && pair._1 == result.last.last + 1)
            val prevStart = result.last.head
            result.remove(result.size - 1)
            prevStart
          else
            pair._1
        case 0 if prevCount != 0 =>
          result.append(rangeStart to pair._1)
        case _ =>
    result.result()

  def getTuningFrequencies(readings: Array[SensorReading], minCoord: Int, maxCoord: Int): Array[Long] =
    val coords = (minCoord to maxCoord)
      .map(yPos => (getMinimumNonBeaconRanges(readings, yPos), yPos))
      .filter((ranges, _) => ranges.length > 1)
    coords
      .map((ranges, yPos) => (ranges(1).head.toLong - 1, yPos.toLong))
      .map((x, y) => x * 4000000 + y)
      .toArray
