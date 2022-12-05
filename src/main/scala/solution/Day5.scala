package solution

import domain.CraneModel.*
import service.{CrateMovementParser, CrateStackParser, GiantCargoCrane}


class Day5 {

  private val craneModel9000: GiantCargoCrane = GiantCargoCrane(Model9000)
  private val craneModel9001: GiantCargoCrane = GiantCargoCrane(Model9001)
  private val crateParser: CrateStackParser = CrateStackParser()
  private val movementParser: CrateMovementParser = CrateMovementParser()

  def part1(input: String): String = rearrange(input, craneModel9000)

  def part2(input: String): String = rearrange(input, craneModel9001)

  private def rearrange(input: String, crane: GiantCargoCrane): String = {
    val Array(stackInput, movementInput) = input.split("\\n\\n")
    val cargoDeck = crateParser.getCargoDeckLayout(stackInput)
    val movements = movementParser.getMovements(movementInput)
    crane.rearrangeCrates(movements, cargoDeck)
      .map(_.pop)
      .map(_.content)
      .mkString
  }
}
