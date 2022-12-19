package solution

import service.SurfaceAreaScanner

class Day18 {

  private val scanner = SurfaceAreaScanner()
  
  def part1(input: String): Int = scanner.scan(input)
}