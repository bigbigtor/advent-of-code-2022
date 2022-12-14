package solution

import service.PacketComparator
import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}

class Day13 {

  private val mapper = ObjectMapper()

  private val comparator = PacketComparator()

  def part1(input: String): Int =
    val pairs = input
      .split("\n\n")
      .map(lines =>
        val packetPairs = lines.split("\n").map(mapper.readTree)
        (packetPairs(0), packetPairs(1))
      )
    pairs
      .map((left, right) => comparator.compare(left, right))
      .zipWithIndex
      .filter((res, _) => res == -1)
      .map((_, i) => i + 1)
      .sum
}