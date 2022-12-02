package domain

enum RockPaperScissorsResult(_score: Int):
  case Lose extends RockPaperScissorsResult(0)
  case Draw extends RockPaperScissorsResult(3)
  case Win extends RockPaperScissorsResult(6)
  
  def score: Int = _score