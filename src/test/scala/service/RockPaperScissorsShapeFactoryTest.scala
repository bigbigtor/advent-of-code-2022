package service

import munit.FunSuite
import domain.RockPaperScissorsShape.*

class RockPaperScissorsShapeFactoryTest extends FunSuite {

  val factory: RockPaperScissorsShapeFactory = RockPaperScissorsShapeFactory()

  test("Returns a shape instance for defined inputs") {
    assertEquals(factory.getShapeFromInput("A"), Some(Rock))
    assertEquals(factory.getShapeFromInput("X"), Some(Rock))
    assertEquals(factory.getShapeFromInput("B"), Some(Paper))
    assertEquals(factory.getShapeFromInput("Y"), Some(Paper))
    assertEquals(factory.getShapeFromInput("C"), Some(Scissors))
    assertEquals(factory.getShapeFromInput("Z"), Some(Scissors))
  }

  test("No shape is returned for undefined inputs") {
    assertEquals(factory.getShapeFromInput("undefinedInput"), None)
  }
}
