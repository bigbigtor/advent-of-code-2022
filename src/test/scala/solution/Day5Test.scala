package solution

import munit.FunSuite

import scala.io.Source

class Day5Test extends FunSuite {

  private val day5 = Day5()
  private val input = Source.fromResource("day5.txt").mkString

  test("Day 5 part 1 is solved correctly") {
    assertEquals(day5.part1(input), "HBTMTBSDC")
  }

  test("Day 5 part 2 is solved correctly") {
    assertEquals(day5.part2(input), "PQTJRSHWS")
  }
}
