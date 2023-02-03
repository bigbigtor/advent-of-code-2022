package solution

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

import scala.io.Source

class Day22Test extends AnyFunSuite {

  private val day22 = Day22()
  private val input = Source.fromResource("day22.txt").mkString

  test("Day 22 part 1 is solved correctly") {
    day22.part1(input) should equal(80392)
  }
}
