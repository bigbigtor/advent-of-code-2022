package service

class SurfaceAreaScanner:

  def scan(input: String): Int =
    val dropletPositions = input
      .split("\n")
      .map(line => {
        val coords = line.split(",")
        (coords(0).toInt, coords(1).toInt, coords(2).toInt)
      })
      .toSet
    val allAdjPos = dropletPositions
      .map(getAdjacentPositions)
      .foldLeft(Array.empty[(Int, Int, Int)])((acc, coords) => acc ++ coords)
    allAdjPos.count(p => !dropletPositions.contains(p))

  private def getAdjacentPositions(coords: (Int, Int, Int)): Array[(Int, Int, Int)] =
    Array((0, 0, -1), (0, 0, 1), (0, -1, 0), (0, 1, 0), (-1, 0, 0), (1, 0, 0))
      .map(offset => (coords._1 + offset._1, coords._2 + offset._2, coords._3 + offset._3))