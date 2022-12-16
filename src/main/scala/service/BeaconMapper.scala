package service

import domain.SensorReading

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
    reading.beaconPos._1 match
      case x if x == rangeStart => Some(rangeStart + 1 to rangeEnd)
      case x if x == rangeEnd => Some(rangeStart until rangeEnd)
      case _ => Some(rangeStart to rangeEnd)

  def getNonBeaconPositionsForRow(readings: Array[SensorReading], row: Int): Int =
    readings
      .map(getSensorRangeForRow(_, row))
      .flatMap(_.toSet.flatten)
      .toSet
      .size