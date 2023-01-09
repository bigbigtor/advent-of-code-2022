package solution

import service.{BlizzardValleyParser, BlizzardValleySolver}

class Day24 {

  private val parser = BlizzardValleyParser()
  private val solver = BlizzardValleySolver()
  
  def part1(input: String): Int =
    val valley = parser.parse(input)
    val start = parser.findStart(input)
    val end = parser.findEnd(input)
    solver.getStepsFromStartToEnd(valley, (start._1, start._2, 0), end)
}