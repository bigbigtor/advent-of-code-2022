package service

import domain.RockPaperScissorsShape
import domain.RockPaperScissorsShape.*


class ShapeStrategyMapper extends RockPaperScissorsShapeMapper {
  override def getShapes(input: String): Array[RockPaperScissorsShape] =
    input.split(" ").map(getShapeFromInput)

  private def getShapeFromInput(input: String): RockPaperScissorsShape = input match {
    case "A" | "X" => Rock
    case "B" | "Y" => Paper
    case "C" | "Z" => Scissors
  }
}

