package solution

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

import scala.io.Source

class Day16Test extends AnyFunSuite {

  private val day16 = Day16()
  private val input = Source.fromResource("day16.txt").mkString

  test("Day 16 part 1 is solved correctly") {
    day16.part1(input) should equal(1617)
  }
}
