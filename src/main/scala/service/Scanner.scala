package service

import domain.{Cave, CaveElement}
import domain.CaveElement.*

class Scanner:

  def scan(input: String): Cave =
    val rockPositions = input.split("\n").flatMap(parseRow).toSet
    Cave(rockPositions)

  private def parseRow(row: String): Set[(Int, Int)] =
    row.split(" -> ")
      .map(_.split(","))
      .map(pos => (pos(0).toInt, pos(1).toInt))
      .sliding(2, 1)
      .map(getRanges)
      .flatMap((x, y) => {
        (x, y) match
          case (x, y) if x.size == 1 => y.map((x.head, _)).toSet
          case (x, y) if y.size == 1 => x.map((_, y.head)).toSet
      })
      .toSet

  private def getRanges(slice: Array[(Int, Int)]): (Range, Range) =
    val (iniX, iniY) = slice(0)
    val (endX, endY) = slice(1)
    val xRange = iniX to (endX, if (endX >= iniX) 1 else -1)
    val yRange = iniY to (endY, if (endY >= iniY) 1 else -1)
    (xRange, yRange)
