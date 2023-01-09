package solution

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

import scala.io.Source

class Day24Test extends AnyFunSuite {

  private val day24 = Day24()
  private val input = Source.fromResource("day24.txt").mkString

  test("Day 24 part 1 is solved correctly") {
    day24.part1(input) should equal(283)
  }
}
