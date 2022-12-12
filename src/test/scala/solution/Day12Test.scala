package solution

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

import scala.io.Source

class Day12Test extends AnyFunSuite {

  private val day12 = Day12()
  private val input = Source.fromResource("day12.txt").mkString

  test("Day 12 part 1 is solved correctly") {
    day12.part1(input) should equal(394)
  }
}
