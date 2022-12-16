package service

import domain.SensorReading
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.contain
import org.scalatest.matchers.should.Matchers.{should, theSameElementsInOrderAs}

class SensorReadingParserTest extends AnyFunSuite {

  private val parser = SensorReadingParser()

  private val input = """Sensor at x=2, y=18: closest beacon is at x=-2, y=15
                        |Sensor at x=9, y=16: closest beacon is at x=10, y=16
                        |Sensor at x=13, y=2: closest beacon is at x=15, y=3
                        |Sensor at x=12, y=14: closest beacon is at x=10, y=16
                        |Sensor at x=10, y=20: closest beacon is at x=10, y=16
                        |Sensor at x=14, y=17: closest beacon is at x=10, y=16
                        |Sensor at x=8, y=7: closest beacon is at x=2, y=10
                        |Sensor at x=2, y=0: closest beacon is at x=2, y=10
                        |Sensor at x=0, y=11: closest beacon is at x=2, y=10
                        |Sensor at x=20, y=14: closest beacon is at x=25, y=17
                        |Sensor at x=17, y=20: closest beacon is at x=21, y=22
                        |Sensor at x=16, y=7: closest beacon is at x=15, y=3
                        |Sensor at x=14, y=3: closest beacon is at x=15, y=3
                        |Sensor at x=20, y=1: closest beacon is at x=15, y=3""".stripMargin

  private val expectedReadings = Array[SensorReading](
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

  test("Parser returns the expected readings for the given input") {
    parser.parse(input) should contain theSameElementsInOrderAs expectedReadings
  }
}
