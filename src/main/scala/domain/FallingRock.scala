package domain

import domain.RockShape.*

enum RockShape:
  case Flat, Cross, LShaped, Tall, Square

case class FallingRock(shape: RockShape, bottomLeftPos: (Int, Int)):

  def getOccupiedPositions: Set[(Int, Int)] =
    val (blx, bly) = bottomLeftPos
    shape match
      case Flat => Set((blx, bly), (blx + 1, bly), (blx + 2, bly), (blx + 3, bly))
      case Cross => Set((blx + 1, bly), (blx, bly + 1), (blx + 1, bly + 1), (blx + 2, bly + 1), (blx + 1, bly + 2))
      case LShaped => Set((blx, bly), (blx + 1, bly), (blx + 2, bly), (blx + 2, bly + 1), (blx + 2, bly + 2))
      case Tall => Set((blx, bly), (blx, bly + 1), (blx, bly + 2), (blx, bly + 3))
      case Square => Set((blx, bly), (blx, bly + 1), (blx + 1, bly), (blx + 1, bly + 1))
