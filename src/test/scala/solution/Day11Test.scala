package solution

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

import scala.io.Source

class Day11Test extends AnyFunSuite {

  private val day11 = Day11()
  private val input = Source.fromResource("day11.txt").mkString

  test("Day 11 part 1 is solved correctly") {
    day11.part1(input) should equal(55930)
  }
}
