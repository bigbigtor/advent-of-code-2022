package solution

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

import scala.io.Source

class Day21Test extends AnyFunSuite {

  private val day21 = Day21()
  private val input = Source.fromResource("day21.txt").mkString

  test("Day 21 part 1 is solved correctly") {
    day21.part1(input) should equal(49288254556480L)
  }

  test("Day 21 part 2 is solved correctly") {
    day21.part2(input) should equal(3558714869436L)
  }
}
