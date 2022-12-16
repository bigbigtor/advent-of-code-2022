package service

import domain.SensorReading
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

class BeaconMapperTest extends AnyFunSuite {

  private val mapper = BeaconMapper()

  private val reading = SensorReading((8, 7), (2, 10))

  private val readings = Array[SensorReading](
    SensorReading((2, 18), (-2, 15)),
    SensorReading((9, 16), (10, 16)),
    SensorReading((13, 2), (15, 3)),
    SensorReading((12, 14), (10, 16)),
    SensorReading((10, 20), (10, 16)),
    SensorReading((14, 17), (10, 16)),
    SensorReading((8, 7), (2, 10)),
    SensorReading((2, 0), (2, 10)),
    SensorReading((0, 11), (2, 10)),
    SensorReading((20, 14), (25, 17)),
    SensorReading((17, 20), (21, 22)),
    SensorReading((16, 7), (15, 3)),
    SensorReading((14, 3), (15, 3)),
    SensorReading((20, 1), (15, 3)),
  )

  test("Manhattan distance for the given reading is correct") {
    mapper.getManhattanDistance(reading.sensorPos, reading.beaconPos) should equal (9)
  }

  test("Sensor range for row is correct for the given reading") {
    mapper.getSensorRangeForRow(reading, 12) should equal (Some(4 to 12))
    mapper.getSensorRangeForRow(reading, 17) should equal (None)
    mapper.getSensorRangeForRow(reading, 6) should equal (Some(0 to 16))
    mapper.getSensorRangeForRow(reading, 10) should equal (Some(3 to 14))
  }

  test("Non beacon positions are correct for the given readings") {
    mapper.getNonBeaconPositionsForRow(readings, 10) should equal (26)
  }
}
