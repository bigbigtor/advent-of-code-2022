package solution

import service.{Quadcopter, TreeVisibilityVerifier}

class Day8 {

  private val quadcopter = Quadcopter()

  private val verifier = TreeVisibilityVerifier()

  def part1(input: String): Int =
    val map = quadcopter.generateMap(input)
    map.zipWithIndex
      .map((row, rowIdx) => {
        row.zipWithIndex
          .count((tree, colIdx) => {
            tree.visibleFrom = verifier.verify(map, rowIdx, colIdx)
            tree.visibleFrom.nonEmpty
          })
      }).sum
}
