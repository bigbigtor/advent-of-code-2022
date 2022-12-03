package service

import munit.FunSuite
import domain.Item

class ItemTypeToPriorityConverterTest extends FunSuite {

  private val converter: ItemTypeToPriorityConverter = ItemTypeToPriorityConverter()

  test("Item types get converted to the expected priority") {
    assertEquals(converter.convert(Item('p')), 16)
    assertEquals(converter.convert(Item('L')), 38)
    assertEquals(converter.convert(Item('P')), 42)
    assertEquals(converter.convert(Item('v')), 22)
    assertEquals(converter.convert(Item('t')), 20)
    assertEquals(converter.convert(Item('s')), 19)
  }
}
