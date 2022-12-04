package solution

import munit.FunSuite

import scala.io.Source

class Day4Test extends FunSuite {

  private val day4 = Day4()
  private val input = Source.fromResource("day4.txt").mkString

  test("Day 4 part 1 is solved correctly") {
    assertEquals(day4.part1(input), 507)
  }

  test("Day 4 part 2 is solved correctly") {
    assertEquals(day4.part2(input), 897)
  }
}
