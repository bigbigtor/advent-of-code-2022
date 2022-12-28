package solution

import service.ElfSpreadSimulator

class Day23 {

  private val simulator = ElfSpreadSimulator()
  def part1(input: String): Int = simulator.getEmptyGroundTiles(simulator.parse(input), 10)
}