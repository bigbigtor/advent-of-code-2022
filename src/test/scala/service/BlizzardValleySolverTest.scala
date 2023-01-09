package service

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.shouldBe
import service.Tile.{Blizzard, Ground, Wall}

class BlizzardValleySolverTest extends AnyFunSuite {

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

  private val solver = BlizzardValleySolver()

  test("Test minimum required minutes is correct for given valley") {
    solver.getMinutesFromStartToEnd(valley, (1, 0, 0), (5, 6)) shouldBe 10
  }

  test("Test minimum required minutes is correct for given blizzard filled valley") {
    solver.getMinutesFromStartToEnd(blizzardFilledValley, (1, 0, 0), (6, 5)) shouldBe 18
  }

  test("Test minimum required minutes is correct for given blizzard filled valley with snack round trip") {
    solver.getMinutesIncludingSnackRoundTrip(blizzardFilledValley, (1, 0, 0), (6, 5)) shouldBe 54
  }
}
