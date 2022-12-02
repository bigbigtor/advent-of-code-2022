package service


import domain.LetterMappingStrategy.*
import domain.RockPaperScissorsResult.*
import domain.RockPaperScissorsShape.*
import domain.{LetterMappingStrategy, RockPaperScissorsResult, RockPaperScissorsShape}

trait RockPaperScissorsShapeMapper {

  def getShapes(input: String): Array[RockPaperScissorsShape]
}

object RockPaperScissorsShapeMapper {
  def apply(strategy: LetterMappingStrategy): RockPaperScissorsShapeMapper = {
    strategy match {
      case LetterMeansShape => ShapeStrategyMapper()
      case LetterMeansResult => ResultStrategyMapper()
    }
  }
}

