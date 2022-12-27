package solution

import service.CoordinateDecrypter

class Day20 {

  private val decrypter = CoordinateDecrypter()
  
  def part1(input: String): Int = decrypter.extractGroveCoords(decrypter.mix(decrypter.parse(input)))
}