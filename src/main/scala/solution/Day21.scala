package solution

import service.{MonkeyMathExecutor, MonkeyMathParser}

class Day21 {

  private val parser = MonkeyMathParser()
  
  private val executor = MonkeyMathExecutor()
  
  def part1(input: String): Long = executor.compute(parser.parse(input))
}