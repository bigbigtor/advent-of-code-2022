package service

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

class HeightMapTest extends AnyFunSuite {

  private val input =
    """Sabqponm
      |abcryxxl
      |accszExk
      |acctuvwj
      |abdefghi""".stripMargin

  private val expectedDistance = 31

  test("Distance from start to end position is correct for the given input") {
    val heightMap = HeightMap(input)
    heightMap.getDistanceToEnd should equal (expectedDistance)
  }
}
