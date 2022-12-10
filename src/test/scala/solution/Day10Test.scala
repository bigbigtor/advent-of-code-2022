package solution

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

import scala.io.Source

class Day10Test extends AnyFunSuite {

  private val day10 = Day10()
  private val input = Source.fromResource("day10.txt").mkString

  private val expectedPart2Output = """###..#....####.####.#..#.#....###..###..
                                      |#..#.#....#....#....#..#.#....#..#.#..#.
                                      |#..#.#....###..###..#..#.#....#..#.###..
                                      |###..#....#....#....#..#.#....###..#..#.
                                      |#....#....#....#....#..#.#....#....#..#.
                                      |#....####.####.#.....##..####.#....###..""".stripMargin

  test("Day 10 part 1 is solved correctly") {
    day10.part1(input) should equal(16480)
  }

  test("Day 10 part 2 is solved correctly") {
    day10.part2(input) should equal(expectedPart2Output)
  }
}
