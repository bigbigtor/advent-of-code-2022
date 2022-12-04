package service

import munit.FunSuite
import domain.Item

class ItemTypeToPriorityConverterTest extends FunSuite {

  private val converter: ItemTypeToPriorityConverter = ItemTypeToPriorityConverter()

  test("Item types get converted to the expected priority") {
    assertEquals(converter.convert('p'), 16)
    assertEquals(converter.convert('L'), 38)
    assertEquals(converter.convert('P'), 42)
    assertEquals(converter.convert('v'), 22)
    assertEquals(converter.convert('t'), 20)
    assertEquals(converter.convert('s'), 19)
  }
}
