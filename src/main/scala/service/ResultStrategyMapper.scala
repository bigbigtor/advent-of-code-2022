package service

import domain.RockPaperScissorsResult.*
import domain.RockPaperScissorsShape.*
import domain.{RockPaperScissorsResult, RockPaperScissorsShape}


class ResultStrategyMapper extends RockPaperScissorsShapeMapper {
  override def getShapes(input: String): Array[RockPaperScissorsShape] =
    val letters = input.split(" ")
    val opponentShape = getShapeFromInput(letters(0))
    val result = getResultFromInput(letters(1))
    Array(
      opponentShape,
      getOwnShape(opponentShape, result)
    )

  private def getShapeFromInput(input: String): RockPaperScissorsShape = input match {
    case "A" => Rock
    case "B" => Paper
    case "C" => Scissors
  }

  private def getResultFromInput(input: String): RockPaperScissorsResult = input match {
    case "X" => Lose
    case "Y" => Draw
    case "Z" => Win
  }

  private def getOwnShape(opponentShape: RockPaperScissorsShape,
                          result: RockPaperScissorsResult): RockPaperScissorsShape =
    (opponentShape, result) match {
      case (Rock, Lose) => Scissors
      case (Rock, Draw) => Rock
      case (Rock, Win) => Paper
      case (Paper, Lose) => Rock
      case (Paper, Draw) => Paper
      case (Paper, Win) => Scissors
      case (Scissors, Lose) => Paper
      case (Scissors, Draw) => Scissors
      case (Scissors, Win) => Rock
    }
}
