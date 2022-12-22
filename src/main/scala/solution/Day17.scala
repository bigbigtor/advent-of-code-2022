package solution

import service.CaveChamber

class Day17 {

  def part1(input: String): Int =
    val caveChamber = CaveChamber(7, (2022 * 4) + 4, input)
    (1 to 2022).foreach(caveChamber.simulateRockFall)
    caveChamber.getHighestRockPos + 1
}