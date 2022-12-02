package service

import domain.LetterMappingStrategy.{LetterMeansResult, LetterMeansShape}
import domain.RockPaperScissorsShape.{Paper, Rock, Scissors}
import munit.FunSuite

class ShapeStrategyMapperTest extends FunSuite {

  val mapper: RockPaperScissorsShapeMapper = RockPaperScissorsShapeMapper(LetterMeansShape)

  test("Shape strategy maps into correct shapes for the given input") {
    assertEquals(Tuple.fromArray(mapper.getShapes("A X")), (Rock, Rock))
    assertEquals(Tuple.fromArray(mapper.getShapes("A Y")), (Rock, Paper))
    assertEquals(Tuple.fromArray(mapper.getShapes("A Z")), (Rock, Scissors))
    assertEquals(Tuple.fromArray(mapper.getShapes("B X")), (Paper, Rock))
    assertEquals(Tuple.fromArray(mapper.getShapes("B Y")), (Paper, Paper))
    assertEquals(Tuple.fromArray(mapper.getShapes("B Z")), (Paper, Scissors))
    assertEquals(Tuple.fromArray(mapper.getShapes("C X")), (Scissors, Rock))
    assertEquals(Tuple.fromArray(mapper.getShapes("C Y")), (Scissors, Paper))
    assertEquals(Tuple.fromArray(mapper.getShapes("C Z")), (Scissors, Scissors))
  }

}
