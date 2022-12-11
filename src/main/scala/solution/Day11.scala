package solution

import domain.Monkey
import service.{MonkeyBusinessExecutor, MonkeyParser}

class Day11 {

  private val parser = MonkeyParser()

  def part1(input: String): Long =
    val monkeys = parser.parse(input)
    getMonkeyBusinessLevel(monkeys, 20, level => Math.floorDiv(level, 3))

  def part2(input: String): Long =
    val monkeys = parser.parse(input)
    val divisorsProduct = monkeys.map(_.test).product
    val worryLevelManagementFunction: Long => Long = level => level % divisorsProduct
    getMonkeyBusinessLevel(monkeys, 10000, worryLevelManagementFunction)

  private def getMonkeyBusinessLevel(monkeys: Array[Monkey], rounds: Int, reliefFunction: Long => Long): Long =
    val executor = MonkeyBusinessExecutor(monkeys, reliefFunction)
    (0 until rounds).foreach(_ => executor.executeRound())
    executor.getLevel
}
