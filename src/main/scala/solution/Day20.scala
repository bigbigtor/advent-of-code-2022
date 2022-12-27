package solution

import service.CoordinateDecrypter

class Day20 {

  private val decrypter = CoordinateDecrypter()

  def part1(input: String): Long =
    decrypter.extractGroveCoords(decrypter.mix(decrypter.parse(input, 1), 1))

  def part2(input: String): Long =
    decrypter.extractGroveCoords(decrypter.mix(decrypter.parse(input, 811589153), 10))
}