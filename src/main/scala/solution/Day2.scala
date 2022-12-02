package solution

import domain.RockPaperScissorsShape
import service.{RockPaperScissorsScoreCalculator, RockPaperScissorsShapeFactory}

class Day2 {

  private val shapeFactory: RockPaperScissorsShapeFactory = RockPaperScissorsShapeFactory()
  private val calculator: RockPaperScissorsScoreCalculator = RockPaperScissorsScoreCalculator()
  def part1(input: String) : Int = getRoundShapes(input)
    .map(shapes => calculator.getRoundScore(shapes(0), shapes(1)))
    .sum

  private def getRoundShapes(input: String): Seq[Array[RockPaperScissorsShape]] =
    input.split("\\n")
      .map(
        line => line.split(" ")
          .flatMap(shapeFactory.getShapeFromInput)
      )
}
