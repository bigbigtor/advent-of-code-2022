package service

import domain.Coordinate

import scala.collection.mutable

class CoordinateDecrypter:

  private val shiftForward: (Int, List[Coordinate], Int) => List[Coordinate] =
    (endPos, list, elemPos) =>
      list.slice(0, elemPos)
        ++ list.slice(elemPos + 1, endPos + 1)
        ++ list.slice(elemPos, elemPos + 1)
        ++ list.slice(endPos + 1, list.length)

  private val shiftBackward: (Int, List[Coordinate], Int) => List[Coordinate] =
    (endPos, list, elemPos) =>
      endPos match
        case 0 => list.slice(0, elemPos) ++ list.slice(elemPos + 1, list.length) ++ list.slice(elemPos, elemPos + 1)
        case _ => list.slice(0, endPos) ++ list.slice(elemPos, elemPos + 1) ++ list.slice(endPos, elemPos) ++ list.slice(elemPos + 1, list.length)

  private val noShift: (List[Coordinate], Int) => List[Coordinate] =
    (list, elemPos) =>
      list.slice(0, elemPos)
        ++ list.slice(elemPos, elemPos + 1)
        ++ list.slice(elemPos + 1, list.length)

  def parse(input: String): List[Coordinate] =
    input.split("\n")
      .map(c => Coordinate(c.toInt))
      .toList

  def mix(coords: List[Coordinate]): List[Coordinate] =
    val pending = mutable.Queue[Coordinate]()
    val module = coords.length - 1
    coords.foreach(pending.enqueue)
    var result = coords
    while (pending.nonEmpty)
      val coord = pending.dequeue()
      val pos = result.zipWithIndex.filter((c, _) => c eq coord).head._2
      val endPos = (((pos + coord.num) % module) + module) % module
      result = pos.compare(endPos) match
        case r if r < 0 => shiftForward.apply(endPos, result, pos)
        case r if r > 0 => shiftBackward.apply(endPos, result, pos)
        case r if r == 0 => noShift.apply(result, pos)
    result

  def extractGroveCoords(coords: List[Coordinate]): Int =
    val baseIdx = coords
      .zipWithIndex
      .find((c, _) => c.num == 0)
      .map((_, pos) => pos)
      .getOrElse(0)
    val firstIdx = (baseIdx + 1000) % coords.length
    val secondIdx = (baseIdx + 2000) % coords.length
    val thirdIdx = (baseIdx + 3000) % coords.length
    coords(firstIdx).num + coords(secondIdx).num + coords(thirdIdx).num