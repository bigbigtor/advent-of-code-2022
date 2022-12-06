package solution

import munit.FunSuite

import scala.io.Source

class Day6Test extends FunSuite {

  private val day6 = Day6()
  private val input = Source.fromResource("day6.txt").mkString

  test("Day 6 part 1 is solved correctly") {
    assertEquals(day6.part1(input), 1210)
  }
}
