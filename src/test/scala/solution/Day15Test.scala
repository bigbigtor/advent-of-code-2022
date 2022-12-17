package solution

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.contain
import org.scalatest.matchers.should.Matchers.{equal, should, theSameElementsInOrderAs}

import scala.io.Source

class Day15Test extends AnyFunSuite {

  private val day15 = Day15()
  private val input = Source.fromResource("day15.txt").mkString

  test("Day 15 part 1 is solved correctly") {
    day15.part1(input) should equal(5564017)
  }

  test("Day 15 part 2 is solved correctly") {
    day15.part2(input) should contain theSameElementsInOrderAs Array(
      10112730000000L,
      12035738768339L,
      11186434942369L,
      11558423398893L,
      7237279712572L,
      14921643774311L
    )
  }
}
