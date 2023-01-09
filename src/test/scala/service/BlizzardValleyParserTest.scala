package service

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.shouldBe
import service.Tile.*

class BlizzardValleyParserTest extends AnyFunSuite {

  private val input =
    """#.#####
      |#.....#
      |#>....#
      |#.....#
      |#...v.#
      |#.....#
      |#####.#""".stripMargin

  private val blizzardFilledInput =
    """#.######
      |#>>.<^<#
      |#.<..<<#
      |#>v.><>#
      |#<^v^^>#
      |######.#""".stripMargin

  private val valley = Array[Array[Array[Tile]]](
    Array[Array[Tile]](
      Array(Wall, Ground, Wall, Wall, Wall, Wall, Wall),
      Array(Wall, Ground, Ground, Ground, Ground, Ground, Wall),
      Array(Wall, Blizzard, Ground, Ground, Ground, Ground, Wall),
      Array(Wall, Ground, Ground, Ground, Ground, Ground, Wall),
      Array(Wall, Ground, Ground, Ground, Blizzard, Ground, Wall),
      Array(Wall, Ground, Ground, Ground, Ground, Ground, Wall),
      Array(Wall, Wall, Wall, Wall, Wall, Ground, Wall),
    ),
    Array[Array[Tile]](
      Array(Wall, Ground, Wall, Wall, Wall, Wall, Wall),
      Array(Wall, Ground, Ground, Ground, Ground, Ground, Wall),
      Array(Wall, Ground, Blizzard, Ground, Ground, Ground, Wall),
      Array(Wall, Ground, Ground, Ground, Ground, Ground, Wall),
      Array(Wall, Ground, Ground, Ground, Ground, Ground, Wall),
      Array(Wall, Ground, Ground, Ground, Blizzard, Ground, Wall),
      Array(Wall, Wall, Wall, Wall, Wall, Ground, Wall),
    ),
    Array[Array[Tile]](
      Array(Wall, Ground, Wall, Wall, Wall, Wall, Wall),
      Array(Wall, Ground, Ground, Ground, Blizzard, Ground, Wall),
      Array(Wall, Ground, Ground, Blizzard, Ground, Ground, Wall),
      Array(Wall, Ground, Ground, Ground, Ground, Ground, Wall),
      Array(Wall, Ground, Ground, Ground, Ground, Ground, Wall),
      Array(Wall, Ground, Ground, Ground, Ground, Ground, Wall),
      Array(Wall, Wall, Wall, Wall, Wall, Ground, Wall),
    ),
    Array[Array[Tile]](
      Array(Wall, Ground, Wall, Wall, Wall, Wall, Wall),
      Array(Wall, Ground, Ground, Ground, Ground, Ground, Wall),
      Array(Wall, Ground, Ground, Ground, Blizzard, Ground, Wall),
      Array(Wall, Ground, Ground, Ground, Ground, Ground, Wall),
      Array(Wall, Ground, Ground, Ground, Ground, Ground, Wall),
      Array(Wall, Ground, Ground, Ground, Ground, Ground, Wall),
      Array(Wall, Wall, Wall, Wall, Wall, Ground, Wall),
    ),
    Array[Array[Tile]](
      Array(Wall, Ground, Wall, Wall, Wall, Wall, Wall),
      Array(Wall, Ground, Ground, Ground, Ground, Ground, Wall),
      Array(Wall, Ground, Ground, Ground, Ground, Blizzard, Wall),
      Array(Wall, Ground, Ground, Ground, Blizzard, Ground, Wall),
      Array(Wall, Ground, Ground, Ground, Ground, Ground, Wall),
      Array(Wall, Ground, Ground, Ground, Ground, Ground, Wall),
      Array(Wall, Wall, Wall, Wall, Wall, Ground, Wall),
    ),
  )

  private val blizzardFilledValley = Array[Array[Array[Tile]]](
    Array(
      Array(Wall, Ground, Wall, Wall, Wall, Wall, Wall, Wall),
      Array(Wall, Blizzard, Blizzard, Ground, Blizzard, Blizzard, Blizzard, Wall),
      Array(Wall, Ground, Blizzard, Ground, Ground, Blizzard, Blizzard, Wall),
      Array(Wall, Blizzard, Blizzard, Ground, Blizzard, Blizzard, Blizzard, Wall),
      Array(Wall, Blizzard, Blizzard, Blizzard, Blizzard, Blizzard, Blizzard, Wall),
      Array(Wall, Wall, Wall, Wall, Wall, Wall, Ground, Wall)
    ),
    Array(
      Array(Wall, Ground, Wall, Wall, Wall, Wall, Wall, Wall),
      Array(Wall, Ground, Blizzard, Blizzard, Ground, Blizzard, Ground, Wall),
      Array(Wall, Blizzard, Ground, Ground, Blizzard, Blizzard, Ground, Wall),
      Array(Wall, Blizzard, Blizzard, Ground, Blizzard, Blizzard, Ground, Wall),
      Array(Wall, Blizzard, Blizzard, Ground, Ground, Blizzard, Blizzard, Wall),
      Array(Wall, Wall, Wall, Wall, Wall, Wall, Ground, Wall)
    ),
    Array(
      Array(Wall, Ground, Wall, Wall, Wall, Wall, Wall, Wall),
      Array(Wall, Ground, Blizzard, Blizzard, Blizzard, Ground, Ground, Wall),
      Array(Wall, Ground, Blizzard, Blizzard, Blizzard, Blizzard, Blizzard, Wall),
      Array(Wall, Ground, Blizzard, Blizzard, Ground, Blizzard, Blizzard, Wall),
      Array(Wall, Ground, Blizzard, Ground, Ground, Blizzard, Ground, Wall),
      Array(Wall, Wall, Wall, Wall, Wall, Wall, Ground, Wall)
    ),
    Array(
      Array(Wall, Ground, Wall, Wall, Wall, Wall, Wall, Wall),
      Array(Wall, Blizzard, Blizzard, Blizzard, Blizzard, Blizzard, Ground, Wall),
      Array(Wall, Ground, Blizzard, Blizzard, Ground, Blizzard, Ground, Wall),
      Array(Wall, Blizzard, Blizzard, Blizzard, Blizzard, Ground, Ground, Wall),
      Array(Wall, Ground, Ground, Blizzard, Blizzard, Ground, Ground, Wall),
      Array(Wall, Wall, Wall, Wall, Wall, Wall, Ground, Wall)
    ),
    Array(
      Array(Wall, Ground, Wall, Wall, Wall, Wall, Wall, Wall),
      Array(Wall, Ground, Blizzard, Ground, Ground, Blizzard, Blizzard, Wall),
      Array(Wall, Blizzard, Blizzard, Ground, Blizzard, Ground, Ground, Wall),
      Array(Wall, Blizzard, Blizzard, Ground, Blizzard, Blizzard, Ground, Wall),
      Array(Wall, Ground, Blizzard, Blizzard, Blizzard, Blizzard, Ground, Wall),
      Array(Wall, Wall, Wall, Wall, Wall, Wall, Ground, Wall)
    ),
    Array(
      Array(Wall, Ground, Wall, Wall, Wall, Wall, Wall, Wall),
      Array(Wall, Blizzard, Ground, Blizzard, Ground, Blizzard, Blizzard, Wall),
      Array(Wall, Blizzard, Ground, Blizzard, Ground, Ground, Blizzard, Wall),
      Array(Wall, Ground, Blizzard, Blizzard, Blizzard, Blizzard, Blizzard, Wall),
      Array(Wall, Ground, Blizzard, Ground, Ground, Blizzard, Ground, Wall),
      Array(Wall, Wall, Wall, Wall, Wall, Wall, Ground, Wall)
    ),
    Array(
      Array(Wall, Ground, Wall, Wall, Wall, Wall, Wall, Wall),
      Array(Wall, Blizzard, Blizzard, Ground, Blizzard, Ground, Blizzard, Wall),
      Array(Wall, Ground, Blizzard, Blizzard, Blizzard, Blizzard, Blizzard, Wall),
      Array(Wall, Blizzard, Ground, Ground, Blizzard, Blizzard, Blizzard, Wall),
      Array(Wall, Blizzard, Ground, Ground, Ground, Ground, Blizzard, Wall),
      Array(Wall, Wall, Wall, Wall, Wall, Wall, Ground, Wall)
    ),
    Array(
      Array(Wall, Ground, Wall, Wall, Wall, Wall, Wall, Wall),
      Array(Wall, Ground, Blizzard, Blizzard, Blizzard, Blizzard, Ground, Wall),
      Array(Wall, Blizzard, Blizzard, Ground, Blizzard, Blizzard, Ground, Wall),
      Array(Wall, Blizzard, Blizzard, Blizzard, Blizzard, Blizzard, Ground, Wall),
      Array(Wall, Blizzard, Ground, Ground, Ground, Ground, Blizzard, Wall),
      Array(Wall, Wall, Wall, Wall, Wall, Wall, Ground, Wall)
    ),
    Array(
      Array(Wall, Ground, Wall, Wall, Wall, Wall, Wall, Wall),
      Array(Wall, Ground, Blizzard, Blizzard, Blizzard, Blizzard, Ground, Wall),
      Array(Wall, Ground, Ground, Blizzard, Blizzard, Ground, Blizzard, Wall),
      Array(Wall, Ground, Blizzard, Blizzard, Ground, Ground, Blizzard, Wall),
      Array(Wall, Ground, Blizzard, Blizzard, Blizzard, Blizzard, Ground, Wall),
      Array(Wall, Wall, Wall, Wall, Wall, Wall, Ground, Wall)
    ),
    Array(
      Array(Wall, Ground, Wall, Wall, Wall, Wall, Wall, Wall),
      Array(Wall, Blizzard, Ground, Blizzard, Blizzard, Blizzard, Ground, Wall),
      Array(Wall, Ground, Blizzard, Blizzard, Ground, Blizzard, Ground, Wall),
      Array(Wall, Blizzard, Blizzard, Blizzard, Blizzard, Blizzard, Ground, Wall),
      Array(Wall, Ground, Blizzard, Blizzard, Blizzard, Blizzard, Ground, Wall),
      Array(Wall, Wall, Wall, Wall, Wall, Wall, Ground, Wall)
    ),
    Array(
      Array(Wall, Ground, Wall, Wall, Wall, Wall, Wall, Wall),
      Array(Wall, Ground, Blizzard, Ground, Ground, Blizzard, Blizzard, Wall),
      Array(Wall, Blizzard, Blizzard, Blizzard, Blizzard, Blizzard, Ground, Wall),
      Array(Wall, Blizzard, Blizzard, Ground, Blizzard, Blizzard, Ground, Wall),
      Array(Wall, Ground, Ground, Blizzard, Blizzard, Ground, Ground, Wall),
      Array(Wall, Wall, Wall, Wall, Wall, Wall, Ground, Wall)
    ),
    Array(
      Array(Wall, Ground, Wall, Wall, Wall, Wall, Wall, Wall),
      Array(Wall, Blizzard, Blizzard, Ground, Blizzard, Blizzard, Blizzard, Wall),
      Array(Wall, Blizzard, Blizzard, Blizzard, Ground, Blizzard, Blizzard, Wall),
      Array(Wall, Ground, Ground, Blizzard, Ground, Blizzard, Blizzard, Wall),
      Array(Wall, Ground, Blizzard, Ground, Ground, Blizzard, Ground, Wall),
      Array(Wall, Wall, Wall, Wall, Wall, Wall, Ground, Wall)
    )
  )

  private val parser = BlizzardValleyParser()

  test("Generated valley model is correct for the given input") {
    parser.parse(input) shouldBe valley
  }

  test("Generated valley model is correct for the given blizzard filled input") {
    parser.parse(blizzardFilledInput) shouldBe blizzardFilledValley
  }

  test("Start position is correct for the given input") {
    parser.findStart(input) shouldBe(1, 0)
  }

  test("End position is correct for the given input") {
    parser.findEnd(input) shouldBe(5, 6)
  }

  test("Start position is correct for the given blizzard filled input") {
    parser.findStart(input) shouldBe(1, 0)
  }

  test("End position is correct for the given blizzard filled input") {
    parser.findEnd(blizzardFilledInput) shouldBe(6, 5)
  }
}
