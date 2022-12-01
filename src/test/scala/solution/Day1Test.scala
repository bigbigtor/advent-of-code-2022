package solution

import munit.FunSuite
import scala.io.Source

class Day1Test extends FunSuite {

  private val day1 = Day1()
  private val input = Source.fromResource("day1.txt").mkString

  test("Day 1 part 1 is solved correctly") {
    assertEquals(66306, day1.part1(input))
  }
}
