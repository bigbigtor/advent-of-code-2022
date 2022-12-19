package service

import scala.collection.{MapView, mutable}

class SurfaceAreaScanner:

  def scan(input: String): Int =
    val dropletPositions = getDropletPositions(input)
    getAllSurfaces(dropletPositions)

  def scanExteriorSurface(input: String): Int =
    val dropletPositions = getDropletPositions(input)
    val container = getMinimumContainerVolume(dropletPositions)
    val goal = container.minBy(c => c._1 + c._2 + c._3)
    val air = container.diff(dropletPositions)
    val bubbles = air.filter(pos => !BFS(dropletPositions, pos, goal, container))
    val bubbleSurfaces = getAllSurfaces(bubbles)
    val allSurfaces = getAllSurfaces(dropletPositions)
    allSurfaces - bubbleSurfaces

  private def BFS(
                   dropletPos: Set[(Int, Int, Int)],
                   root: (Int, Int, Int),
                   goal: (Int, Int, Int),
                   container: Set[(Int, Int, Int)]
                 ): Boolean =
    val q = mutable.Queue[(Int, Int, Int)]()
    val exploredCubes = mutable.Set(root)
    q.enqueue(root)
    while (q.nonEmpty)
      val v = q.dequeue()
      if (v == goal) return true
      getAdjacentPositions(v)
        .filter(!dropletPos.contains(_))
        .filter(container.contains)
        .filter(!exploredCubes.contains(_))
        .foreach(w => {
          exploredCubes.add(w)
          q.enqueue(w)
        })
    false

  private def getMinimumContainerVolume(droplets: Set[(Int, Int, Int)]): Set[(Int, Int, Int)] =
    val minCoords = (
      droplets.map(_._1).min - 1,
      droplets.map(_._2).min - 1,
      droplets.map(_._3).min - 1)
    val maxCoords = (
      droplets.map(_._1).max + 1,
      droplets.map(_._2).max + 1,
      droplets.map(_._3).max + 1)
    val volume = for (
      i <- minCoords._1 to maxCoords._1;
      j <- minCoords._2 to maxCoords._2;
      k <- minCoords._3 to maxCoords._3
    ) yield (i, j, k)
    volume.toSet

  private def getAllSurfaces(positions: Set[(Int, Int, Int)]): Int =
    positions
      .map(getAdjacentPositions)
      .foldLeft(Array.empty[(Int, Int, Int)])((acc, coords) => acc ++ coords)
      .count(!positions.contains(_))

  private def getDropletPositions(input: String): Set[(Int, Int, Int)] =
    input
      .split("\n")
      .map(line => {
        val coords = line.split(",")
        (coords(0).toInt, coords(1).toInt, coords(2).toInt)
      })
      .toSet

  private def getAdjacentPositions(coords: (Int, Int, Int)): Array[(Int, Int, Int)] =
    Array((0, 0, -1), (0, 0, 1), (0, -1, 0), (0, 1, 0), (-1, 0, 0), (1, 0, 0))
      .map(offset => (coords._1 + offset._1, coords._2 + offset._2, coords._3 + offset._3))