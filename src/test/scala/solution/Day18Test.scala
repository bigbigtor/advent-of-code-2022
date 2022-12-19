package solution

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

import scala.io.Source

class Day18Test extends AnyFunSuite {

  private val day18 = Day18()
  private val input = Source.fromResource("day18.txt").mkString

  test("Day 18 part 1 is solved correctly") {
    day18.part1(input) should equal(3498)
  }

  test("Day 18 part 2 is solved correctly") {
    day18.part2(input) should equal(2008)
  }
}
