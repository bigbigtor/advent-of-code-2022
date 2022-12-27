package solution

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.shouldBe

import scala.io.Source

class Day20Test extends AnyFunSuite {

  private val day20 = Day20()
  private val input = Source.fromResource("day20.txt").mkString

  test("Day 20 part 1 is solved correctly") {
    day20.part1(input) shouldBe 8372
  }

  test("Day 20 part 2 is solved correctly") {
    day20.part2(input) shouldBe 7865110481723L
  }
}
