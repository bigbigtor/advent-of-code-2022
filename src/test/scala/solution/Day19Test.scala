package solution

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

import scala.io.Source

class Day19Test extends AnyFunSuite {

  private val day19 = Day19()
  private val input = Source.fromResource("day19.txt").mkString

  test("Day 19 part 1 is solved correctly") {
    day19.part1(input) should equal(2301)
  }

  test("Day 19 part 2 is solved correctly") {
    day19.part2(input) should equal(10336)
  }
}
