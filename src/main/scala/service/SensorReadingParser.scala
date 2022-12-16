package service

import domain.SensorReading

class SensorReadingParser:

  def parse(input: String): Array[SensorReading] =
    input.split("\n")
      .map(
        _ match
          case s"Sensor at x=${sX}, y=${sY}: closest beacon is at x=${bX}, y=${bY}" =>
            SensorReading((sX.toInt, sY.toInt), (bX.toInt, bY.toInt))
      )