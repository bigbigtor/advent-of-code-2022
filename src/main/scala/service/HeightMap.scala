package service

import domain.Height

import scala.collection.mutable
import scala.collection.mutable.Queue

class HeightMap():
  private var mapWidth: Int = 0
  private var mapHeight: Int = 0
  private var start: Int = 0
  private var end: Int = 0
  private var heights: Array[Height] = Array()
  private var distances: Array[Int] = Array()

  def getDistanceToEnd: Int = distances(end)

object HeightMap:

  def apply(input: String): HeightMap =
    val heightMap = new HeightMap
    heightMap.mapWidth = getMapWidth(input)
    heightMap.mapHeight = getMapHeight(input)
    heightMap.start = getStart(input)
    heightMap.end = getEnd(input)
    heightMap.heights = getHeights(input)
    heightMap.distances = getDistances(
      heightMap.mapWidth,
      heightMap.mapHeight,
      heightMap.start,
      heightMap.heights)
    heightMap

  private def getMapWidth(input: String): Int = input.indexOf("\n")

  private def getMapHeight(input: String): Int = input.split("\n").length

  private def getStart(input: String): Int = input.replaceAll("\n", "").indexOf("S")

  private def getEnd(input: String): Int = input.replaceAll("\n", "").indexOf("E")

  private def getHeights(input: String): Array[Height] =
    input.split("\n")
      .flatMap(line => {
        line.toCharArray.map(c => {
          if (c == 'S') 0 else if (c == 'E') 'z' - 'a' else c - 'a'
        })
      })

  private def getDistances(w: Int, h: Int, start: Int, heights: Array[Height]): Array[Int] =
    val visitedPositions = mutable.Set[Int]()
    val distances = Array.fill(heights.length)(Int.MaxValue)
    distances(start) = 0
    while (visitedPositions.size < distances.length)
      val pos = distances
        .indices
        .filter(pos => !visitedPositions.contains(pos))
        .minBy(pos => distances(pos))
      visitedPositions.add(pos)
      getAdjacentPos(pos, w, h, heights)
        .foreach(adj => {
          if (distances(adj) > distances(pos) + 1)
            distances(adj) = distances(pos) + 1
        })
    distances

  private def getAdjacentPos(pos: Int, w: Int, h: Int, heights: Array[Height]): Array[Int] =
    val x = pos % w
    val y = pos / w
    Array(
      (x, y - 1),
      (x, y + 1),
      (x - 1, y),
      (x + 1, y))
      .filter((x, y) => (0 until w).contains(x) && (0 until h).contains(y))
      .map((x, y) => (y * w) + x)
      .filter(adj => (heights(adj) - heights(pos)) <= 1)