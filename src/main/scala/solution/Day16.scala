package solution

import service.PressureReleaseMaximizer

class Day16 {

  private val maximizer = PressureReleaseMaximizer()
  
  def part1(input: String): Int = maximizer.compute(input)
}