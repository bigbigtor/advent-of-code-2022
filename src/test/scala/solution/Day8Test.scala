package solution

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

import scala.io.Source

class Day8Test extends AnyFunSuite {

  private val day8 = Day8()
  private val input = Source.fromResource("day8.txt").mkString

  test("Day 8 part 1 is solved correctly") {
    day8.part1(input) should equal (1814)
  }

  test("Day 8 part 2 is solved correctly") {
    day8.part2(input) should equal (330786)
  }
}
