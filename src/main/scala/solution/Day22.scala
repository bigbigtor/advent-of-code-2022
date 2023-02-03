package solution

import service.{MonkeyMapParser, MonkeyMapWalker}

class Day22 {

  private val parser = MonkeyMapParser()
  
  private val walker = MonkeyMapWalker()
  
  def part1(input: String): Int =
    val (map, inst) = parser.parse(input)
    val (row, col, facing) = walker.walk(map, inst)
    (1000 * (row + 1)) + (4 * (col + 1)) + facing
}