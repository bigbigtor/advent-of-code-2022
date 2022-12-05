package service

import munit.FunSuite

class GiantCargoCrane9000Test extends FunSuite {

  private val input = """|    [D]
                         |[N] [C]
                         |[Z] [M] [P]
                         | 1   2   3 """.stripMargin

  private val moves =
    """|move 1 from 2 to 1
       |move 3 from 1 to 3
       |move 2 from 2 to 1
       |move 1 from 1 to 2
       |""".stripMargin

  private val stackParser = CrateStackParser()
  private val movementParser = CrateMovementParser()
  private val crane = GiantCargoCrane9000()

  test("Stacks after rearrangement are correct") {
    var cargoDeck = stackParser.getCargoDeckLayout(input)
    val movements = movementParser.getMovements(moves)
    cargoDeck = crane.rearrangeCrates(movements, cargoDeck)
    assertEquals(cargoDeck(0).pop().content, 'C')
    assertEquals(cargoDeck(1).pop().content, 'M')
    assertEquals(cargoDeck(2).pop().content, 'Z')
    assertEquals(cargoDeck(2).pop().content, 'N')
    assertEquals(cargoDeck(2).pop().content, 'D')
    assertEquals(cargoDeck(2).pop().content, 'P')
  }
}
