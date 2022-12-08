package service

import domain.{Direction, PlantationMap, Tree}
import domain.Direction.*

class TreeVisibilityVerifier:

  def verify(map: PlantationMap, row: Int, column: Int): Set[Direction] =
    Direction.values.filter(visible(map, row, column, _)).toSet

  def getScenicScore(map: PlantationMap, row: Int, column: Int): Int =
    Direction.values.map(getViewingDistance(map, row, column, _)).product

  private def visible(map: PlantationMap, row: Int, column: Int, dir: Direction): Boolean =
    val target = map(row)(column)
    val anyTreeBlocks = getPossiblyBlockingTrees(map, row, column, dir).exists(_.height >= target.height)
    !anyTreeBlocks

  private def getViewingDistance(map: PlantationMap, row: Int, col: Int, dir: Direction): Int =
    val target = map(row)(col)
    val treesInLine = getPossiblyBlockingTrees(map, row, col, dir)
    val orderedTrees = dir match
      case Left | Top => treesInLine.reverse
      case Right | Bottom => treesInLine
    val distance = orderedTrees.indexWhere(target.height <= _.height) + 1
    if (distance == 0) treesInLine.length else distance

  private def getPossiblyBlockingTrees(map: PlantationMap, row: Int, col: Int, dir: Direction): Array[Tree] =
    dir match
      case Left => (0 until col).map(map(row)(_)).toArray
      case Right => (col + 1 until map(0).length).map(map(row)(_)).toArray
      case Top => (0 until row).map(map(_)(col)).toArray
      case Bottom => (row + 1 until map.length).map(map(_)(col)).toArray
