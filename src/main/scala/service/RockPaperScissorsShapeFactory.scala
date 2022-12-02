package service

import domain.RockPaperScissorsShape

class RockPaperScissorsShapeFactory {

  def getShapeFromInput(input: String): Option[RockPaperScissorsShape] = input match {
    case "A" | "X" => Some(RockPaperScissorsShape.Rock)
    case "B" | "Y" => Some(RockPaperScissorsShape.Paper)
    case "C" | "Z" => Some(RockPaperScissorsShape.Scissors)
    case _ => None
  }
}
