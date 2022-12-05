package service

import domain.Movement

class CrateMovementParser:

  def getMovements(input: String): Array[Movement] =
    input.split("\\n")
      .map(_.split(" "))
      .map(move => Movement(move(1).toInt, move(3).toInt - 1, move(5).toInt - 1))
