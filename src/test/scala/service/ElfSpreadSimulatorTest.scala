package service

import domain.ElfPlanter
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.shouldBe

class ElfSpreadSimulatorTest extends AnyFunSuite {

  private val simulator = ElfSpreadSimulator()

  private val input = """....#..
                        |..###.#
                        |#...#.#
                        |.#...##
                        |#.###..
                        |##.#.##
                        |.#..#..""".stripMargin

  private def elves = Array(
    ElfPlanter(1, 0),
    ElfPlanter(4, 0),
    ElfPlanter(0, 1),
    ElfPlanter(1, 1),
    ElfPlanter(3, 1),
    ElfPlanter(5, 1),
    ElfPlanter(6, 1),
    ElfPlanter(0, 2),
    ElfPlanter(2, 2),
    ElfPlanter(3, 2),
    ElfPlanter(4, 2),
    ElfPlanter(1, 3),
    ElfPlanter(5, 3),
    ElfPlanter(6, 3),
    ElfPlanter(0, 4),
    ElfPlanter(4, 4),
    ElfPlanter(6, 4),
    ElfPlanter(2, 5),
    ElfPlanter(3, 5),
    ElfPlanter(4, 5),
    ElfPlanter(6, 5),
    ElfPlanter(4, 6),
  )

  private val emptyTiles = 110

  private val staticRound = 20

  test("Parse returns the expected set of elves") {
    simulator.parse(input) shouldBe elves
  }

  test("Grid size is correct for simulation with the given elves") {
    simulator.getEmptyGroundTiles(elves, 10) shouldBe emptyTiles
  }

  test("First round with no movement is correct for simulation with the given elves") {
    simulator.getFirstRoundWithNoMovement(elves) shouldBe staticRound
  }
}
