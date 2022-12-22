package service

import domain.{Direction, FallingRock, RockShape}
import domain.Direction.*

class CaveChamber(val chamberGrid: Array[Array[Char]], jetPattern: String):

  private var jetPatternIndex = 0

  def simulateRockFall(rockNum: Int): Unit =
    val shape = RockShape.fromOrdinal((rockNum - 1) % RockShape.values.length)
    val spawningPos = (2, getHighestRockPos + 4)
    var rock = FallingRock(shape, spawningPos)
    var rockResting = false
    while (!rockResting)
      val rockAfterJetHit = getRockWithUpdatedPosition(rock, getJetDirection)
        .filter(!rockCollides(_))
        .getOrElse(rock)
      val rockAfterFall = getRockWithUpdatedPosition(rockAfterJetHit, Bottom)
        .filter(!rockCollides(_))
        .getOrElse(rockAfterJetHit)
      if (rockAfterJetHit == rockAfterFall) rockResting = true
      rock = rockAfterFall
    rock.getOccupiedPositions.foreach((x, y) => chamberGrid(y)(x) = '#')

  def getHighestRockPos: Int =
    chamberGrid
      .zipWithIndex
      .findLast((row, _) => row.contains('#'))
      .map((_, pos) => pos)
      .getOrElse(-1)

  override def toString: String =
    chamberGrid
      .reverse
      .map(_.map(c => if (c == Char.MinValue) '.' else c).mkString)
      .mkString("\n")

  private def getRockWithUpdatedPosition(rock: FallingRock, dir: Direction): Option[FallingRock] =
    val nextRock = dir match
      case Left => FallingRock(rock.shape, (rock.bottomLeftPos._1 - 1, rock.bottomLeftPos._2))
      case Right => FallingRock(rock.shape, (rock.bottomLeftPos._1 + 1, rock.bottomLeftPos._2))
      case Bottom => FallingRock(rock.shape, (rock.bottomLeftPos._1, rock.bottomLeftPos._2 - 1))
      case _ => rock
    val outOfBounds: FallingRock => Boolean =
      n => n.bottomLeftPos._2 < 0 || n.getOccupiedPositions.exists((x, _) => x < 0 || x >= chamberGrid(0).length)
    nextRock match
      case n if !outOfBounds.apply(n) => Some(n)
      case _ => None

  private def getJetDirection: Direction =
    val dir = jetPattern(jetPatternIndex % jetPattern.length) match
      case '<' => Left
      case '>' => Right
    jetPatternIndex += 1
    dir


  private def rockCollides(rock: FallingRock): Boolean =
    rock.getOccupiedPositions.exists((x, y) => chamberGrid(y)(x) == '#')

object CaveChamber:

  def apply(w: Int, h: Int, jetPattern: String): CaveChamber =
    new CaveChamber(Array.ofDim[Char](h, w), jetPattern)