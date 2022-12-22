package service

import org.mockito.Mockito.{reset, verify, verifyNoInteractions, verifyNoMoreInteractions, when}
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.shouldBe
import org.scalatestplus.mockito.MockitoSugar.mock

class CaveChamberTest extends AnyFunSuite, BeforeAndAfterEach {

  private val jetPattern = ">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"

  private val caveAfter =
    """.......
      |.......
      |.......
      |.......
      |.......
      |.......
      |.......
      |....#..
      |....#..
      |....##.
      |##..##.
      |######.
      |.###...
      |..#....
      |.####..
      |....##.
      |....##.
      |....#..
      |..#.#..
      |..#.#..
      |#####..
      |..###..
      |...#...
      |..####.""".stripMargin

  test("Simulate rock fall works as expected") {
    var caveChamber = CaveChamber(7, 24, jetPattern)
    (1 to 10).foreach(caveChamber.simulateRockFall(_))
    caveChamber.toString shouldBe caveAfter
    caveChamber = CaveChamber(7, (2022 * 4) + 4, jetPattern)
    (1 to 2022).foreach(caveChamber.simulateRockFall(_))
    caveChamber.getHighestRockPos + 1 shouldBe 3068
  }
}
