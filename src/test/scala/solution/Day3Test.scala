package solution

import munit.FunSuite

import scala.io.Source

class Day3Test extends FunSuite {

  private val day3 = Day3()
  private val input = Source.fromResource("day3.txt").mkString

  test("Day 3 part 1 is solved correctly") {
    assertEquals(day3.part1(input), 7746)
  }

  test("Day 3 part 2 is solved correctly") {
    assertEquals(day3.part2(input), 2604)
  }
}
