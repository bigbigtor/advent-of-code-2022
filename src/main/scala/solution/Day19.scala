package solution

import service.{BlueprintParser, GeodeCrackingMaximizer}

class Day19 {

  private val maximizer = GeodeCrackingMaximizer()
  private val parser = BlueprintParser()

  def part1(input: String): Int =
    parser
      .parse(input)
      .zipWithIndex
      .map((bp, idx) => (maximizer.count(bp, 24), idx))
      .map((count, idx) => count * (idx + 1))
      .sum
    
  def part2(input: String): Int =
    parser
      .parse(input)
      .slice(0, 3)
      .map(maximizer.count(_, 32))
      .product
}