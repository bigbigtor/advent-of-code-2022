package solution

import service.{SandUnitCounter, Scanner}

class Day14 {

  private val scanner = Scanner()

  private val counter = SandUnitCounter()

  def part1(input: String): Int = counter.count(scanner.scan(input))
}