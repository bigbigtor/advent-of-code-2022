package service

import domain.LetterMappingStrategy.*
import domain.RockPaperScissorsShape.*
import munit.FunSuite


class RockPaperScissorsShapeMapperTest extends FunSuite {

  test("Correct instance is created for the given strategy") {
    assert(RockPaperScissorsShapeMapper(LetterMeansShape).isInstanceOf[ShapeStrategyMapper])
    assert(RockPaperScissorsShapeMapper(LetterMeansResult).isInstanceOf[ResultStrategyMapper])
  }
}
