package solution

import service.PacketComparator
import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}

class Day13 {

  private val mapper = ObjectMapper()

  private val comparator = PacketComparator()

  private val divider1 = "[[2]]"

  private val divider2 = "[[6]]"

  def part1(input: String): Int =
    val pairs = getPackets(input)
    pairs
      .map((left, right) => comparator.compare(left, right))
      .zipWithIndex
      .filter((res, _) => res == -1)
      .map((_, i) => i + 1)
      .sum

  def part2(input: String): Int =
    val pairs = getPackets(input) :+ (mapper.readTree(divider1), mapper.readTree(divider2))
    val flatPackets: Array[JsonNode] = pairs
      .flatMap((a, b) => Array(a, b))
      .sortWith((l, r) => comparator.compare(l, r) == -1)
    val serializedPackets = flatPackets.map(mapper.writeValueAsString)
    findDividerPos(serializedPackets, divider1) * findDividerPos(serializedPackets, divider2)

  private def getPackets(input: String): Array[(JsonNode, JsonNode)] =
    input
      .split("\n\n")
      .map(lines =>
        val packetPairs = lines.split("\n").map(mapper.readTree)
        (packetPairs(0), packetPairs(1))
      )

  private def findDividerPos(packets: Array[String], divider: String): Int =
    packets.zipWithIndex.find((packet, _) => packet == divider).map(_._2).get + 1
}