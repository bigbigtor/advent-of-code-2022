package service

import munit.FunSuite

class CrateStackParserTest extends FunSuite {

  private val input = """|    [D]
                         |[N] [C]
                         |[Z] [M] [P]
                         | 1   2   3 """.stripMargin

  private val parser: CrateStackParser = CrateStackParser()

  test("Input is parsed correctly into crate stacks") {
    val cargoDeck = parser.getCargoDeckLayout(input)
    assertEquals(cargoDeck.length, 3)
    assertEquals(cargoDeck(0).pop().content, 'N')
    assertEquals(cargoDeck(0).pop().content, 'Z')
    assertEquals(cargoDeck(1).pop().content, 'D')
    assertEquals(cargoDeck(1).pop().content, 'C')
    assertEquals(cargoDeck(1).pop().content, 'M')
    assertEquals(cargoDeck(2).pop().content, 'P')
  }
}
