package solution

import service.{CrateMovementParser, CrateStackParser, GiantCargoCrane}


class Day5 {

  private val crane: GiantCargoCrane = GiantCargoCrane()
  private val crateParser: CrateStackParser = CrateStackParser()
  private val movementParser: CrateMovementParser = CrateMovementParser()

  def part1(input: String): String = {
    val Array(stackInput, movementInput) = input.split("\\n\\n")
    val cargoDeck = crateParser.getCargoDeckLayout(stackInput)
    val movements = movementParser.getMovements(movementInput)
    crane.rearrangeCrates(movements, cargoDeck)
      .map(_.pop)
      .map(_.content)
      .mkString
  }

}
