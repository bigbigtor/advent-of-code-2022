package solution

import service.{BlizzardValleyParser, BlizzardValleySolver, Valley}

class Day24 {

  private val parser = BlizzardValleyParser()
  private val solver = BlizzardValleySolver()
  
  def part1(input: String): Int =
    val (valley, start, end) = parse(input)
    solver.getMinutesFromStartToEnd(valley, (start._1, start._2, 0), end)

  def part2(input: String): Int =
    val (valley, start, end) = parse(input)
    solver.getMinutesIncludingSnackRoundTrip(valley, (start._1, start._2, 0), end)

  private def parse(input: String): (Valley, (Int, Int), (Int, Int)) =
    val valley = parser.parse(input)
    val start = parser.findStart(input)
    val end = parser.findEnd(input)
    (valley, start, end)
}