package solution

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

import scala.io.Source

class Day15Test extends AnyFunSuite {

  private val day15 = Day15()
  private val input = Source.fromResource("day15.txt").mkString

  test("Day 15 part 1 is solved correctly") {
    day15.part1(input) should equal(5564017)
  }
}
