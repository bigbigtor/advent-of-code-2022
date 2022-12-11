package solution

import service.{MonkeyBusinessExecutor, MonkeyParser}

class Day11 {

  private val parser = MonkeyParser()

  def part1(input: String): Long =
    val monkeys = parser.parse(input)
    val executor = MonkeyBusinessExecutor(monkeys)
    (0 until 20).foreach(_ => executor.executeRound())
    executor.getLevel
}
