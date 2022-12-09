package solution

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

import scala.io.Source

class Day9Test extends AnyFunSuite {

  private val day9 = Day9()
  private val input = Source.fromResource("day9.txt").mkString

  test("Day 9 part 1 is solved correctly") {
    day9.part1(input) should equal(5960)
  }
}
