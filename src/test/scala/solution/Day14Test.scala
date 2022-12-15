package solution

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

import scala.io.Source

class Day14Test extends AnyFunSuite {

  private val day14 = Day14()
  private val input = Source.fromResource("day14.txt").mkString

  test("Day 14 part 1 is solved correctly") {
    day14.part1(input) should equal(592)
  }
}
