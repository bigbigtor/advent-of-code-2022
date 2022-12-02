package solution

import munit.FunSuite

import scala.io.Source

class Day2Test extends FunSuite {

  private val day2 = Day2()
  private val input = Source.fromResource("day2.txt").mkString

  test("Day 2 part 1 is solved correctly") {
    assertEquals(day2.part1(input), 11386)
  }

  test("Day 2 part 2 is solved correctly") {
    assertEquals(day2.part2(input), 13600)
  }
}
