package service

import domain.Movement
import munit.FunSuite

class CrateMovementParserTest extends FunSuite {

  private val moves =
    """|move 1 from 2 to 1
       |move 3 from 1 to 3
       |move 2 from 2 to 1
       |move 1 from 1 to 2
       |""".stripMargin

  private val parser: CrateMovementParser = CrateMovementParser()

  test("Input is parsed correctly into movements") {
    val movements = parser.getMovements(moves)
    assertEquals(movements(0), Movement(1, 1, 0))
    assertEquals(movements(1), Movement(3, 0, 2))
    assertEquals(movements(2), Movement(2, 1, 0))
    assertEquals(movements(3), Movement(1, 0, 1))
  }
}
