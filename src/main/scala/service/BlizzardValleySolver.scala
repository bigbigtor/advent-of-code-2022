package service

import scala.collection.mutable
import service.Tile.*

type Pos = (Int, Int, Int)
type Valley = Array[Array[Array[Tile]]]

class BlizzardValleySolver:

  def getMinutesIncludingSnackRoundTrip(valley: Valley, start: Pos, end: (Int, Int)): Int =
    val firstTripMinutes = getMinutesFromStartToEnd(valley, (start._1, start._2, 0), end)
    val secondTripMinutes = getMinutesFromStartToEnd(valley, (end._1, end._2, firstTripMinutes), (start._1, start._2))
    getMinutesFromStartToEnd(valley, (start._1, start._2, secondTripMinutes), end)

  def getMinutesFromStartToEnd(valley: Valley, start: Pos, end: (Int, Int)): Int =
    val q = mutable.Queue[Pos](start)
    val exploredPositions = mutable.Set(start)
    while (q.nonEmpty)
      val v = q.dequeue()
      if ((v._1, v._2) == (end._1, end._2)) return v._3
      getAdjacentPositions(v)
        .filter(pos => valley(0).indices.contains(pos._2))
        .filter(pos => valley(0)(0).indices.contains(pos._1))
        .filter(pos => valley(pos._3 % valley.length)(pos._2)(pos._1) == Ground)
        .filter(exploredPositions.add)
        .foreach(q.enqueue)
    0

  private def getAdjacentPositions(pos: Pos): Array[Pos] =
    Array(
      (pos._1,     pos._2,     pos._3 + 1),
      (pos._1 - 1, pos._2,     pos._3 + 1),
      (pos._1 + 1, pos._2,     pos._3 + 1),
      (pos._1,     pos._2 - 1, pos._3 + 1),
      (pos._1,     pos._2 + 1, pos._3 + 1)
    )