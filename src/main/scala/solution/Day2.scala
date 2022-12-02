package solution

import domain.LetterMappingStrategy.*
import domain.RockPaperScissorsShape
import service.{RockPaperScissorsScoreCalculator, RockPaperScissorsShapeMapper}

class Day2 {

  private val shapeStrategyMapper: RockPaperScissorsShapeMapper = RockPaperScissorsShapeMapper(LetterMeansShape)
  private val resultStrategyMapper: RockPaperScissorsShapeMapper = RockPaperScissorsShapeMapper(LetterMeansResult)
  private val calculator: RockPaperScissorsScoreCalculator = RockPaperScissorsScoreCalculator()

  def part1(input: String): Int = getTotalScore(input, shapeStrategyMapper)

  private def getTotalScore(input: String, mapper: RockPaperScissorsShapeMapper): Int =
    input.split("\\n")
      .map(mapper.getShapes)
      .map(shapes => calculator.getRoundScore(shapes(0), shapes(1)))
      .sum

  def part2(input: String): Int = getTotalScore(input, resultStrategyMapper)
}
