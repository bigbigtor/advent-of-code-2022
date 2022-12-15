package domain

import domain.CaveElement.*

class Cave(elements: Array[Array[CaveElement]]):

  def getSandSourcePosition: (Int, Int) =
    elements(0)
      .zipWithIndex
      .find((el, _) => el == SandSource)
      .map((_, pos) => (pos, 0))
      .get

  def getElement(x: Int, y: Int): Option[CaveElement] =
    (x, y) match
      case (x, y) if elements(0).indices.contains(x) && elements.indices.contains(y) =>
        Some(elements(y)(x))
      case _ => None

  def getBottommostRockPos: Int = elements.length - 1

  def getRightmostRockPos: Int = elements(0).length - 1

  def restSand(x: Int, y: Int): Unit = elements(y)(x) = Sand

  def countSand: Int = elements.flatten.count(_ == Sand)

  override def toString: String =
    elements.map(line => line.map(_.toString).mkString).mkString("\n")

object Cave:

  def apply(rockPositions: Set[(Int, Int)]): Cave =
    val yMax = rockPositions.map(_._2).max
    val closestRockX = rockPositions.map(_._1).min
    val furthestRockX = rockPositions.map(_._1).max
    val xMax = furthestRockX - closestRockX
    val elements = Array.ofDim[CaveElement](yMax + 1, xMax + 1)
    for (x <- 0 to xMax; y <- 0 to yMax)
      elements(y)(x) = if (rockPositions.contains((x + closestRockX, y))) Rock else Air
      //x positions corrected since we don't care what's left or right of the rocks
    elements(0)(500 - closestRockX) = SandSource
    new Cave(elements)
