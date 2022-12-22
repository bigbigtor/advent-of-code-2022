package solution

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

import scala.io.Source

class Day17Test extends AnyFunSuite {

  private val day17 = Day17()
  private val input = Source.fromResource("day17.txt").mkString

  test("Day 17 part 1 is solved correctly") {
    day17.part1(input) should equal(3163)
  }
}
