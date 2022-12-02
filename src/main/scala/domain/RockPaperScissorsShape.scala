package domain

enum RockPaperScissorsShape(_score: Int):
  case Rock extends RockPaperScissorsShape(1)
  case Paper extends RockPaperScissorsShape(2)
  case Scissors extends RockPaperScissorsShape(3)
  
  def score: Int = _score