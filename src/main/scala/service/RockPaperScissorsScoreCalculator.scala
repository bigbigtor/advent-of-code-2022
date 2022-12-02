package service

import domain.RockPaperScissorsResult.*
import domain.RockPaperScissorsShape.*
import domain.{RockPaperScissorsResult, RockPaperScissorsShape}

class RockPaperScissorsScoreCalculator {

  def getRoundScore(opponentShape: RockPaperScissorsShape,
                    ownShape: RockPaperScissorsShape): Int =
    ownShape.score + getRoundResult(opponentShape, ownShape).score

  private def getRoundResult(opponentShape: RockPaperScissorsShape,
                             ownShape: RockPaperScissorsShape): RockPaperScissorsResult =
    (opponentShape, ownShape) match {
      case (opponentShape, ownShape) if opponentShape == ownShape => Draw
      case (Rock, Paper) | (Paper, Scissors) | (Scissors, Rock) => Win
      case (Paper, Rock) | (Scissors, Paper) | (Rock, Scissors) => Lose
    }

}
