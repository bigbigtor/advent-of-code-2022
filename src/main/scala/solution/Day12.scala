package solution

import service.HeightMap

class Day12 {

  def part1(input: String): Int = HeightMap(input).getDistanceToEnd

  def part2(input: String): Int = HeightMap(input).getShorterDistanceFromLowestElevations
}
