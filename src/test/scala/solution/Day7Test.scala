package solution


import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

import scala.io.Source

class Day7Test extends AnyFunSuite {

  private val day7 = Day7()
  private val input = Source.fromResource("day7.txt").mkString

  test("Day 7 part 1 is solved correctly") {
    day7.part1(input) should equal (1723892)
  }

  test("Day 7 part 2 is solved correctly") {
    day7.part2(input) should equal (8474158)
  }
}
