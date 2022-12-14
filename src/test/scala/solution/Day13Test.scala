package solution

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

import scala.io.Source

class Day13Test extends AnyFunSuite {

  private val day13 = Day13()
  private val input = Source.fromResource("day13.txt").mkString

  test("Day 13 part 1 is solved correctly") {
    day13.part1(input) should equal(5675)
  }
}
