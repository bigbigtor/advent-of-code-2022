package solution

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

import scala.io.Source

class Day23Test extends AnyFunSuite {

  private val day23 = Day23()
  private val input = Source.fromResource("day23.txt").mkString

  test("Day 23 part 1 is solved correctly") {
    day23.part1(input) should equal(4070)
  }
}
