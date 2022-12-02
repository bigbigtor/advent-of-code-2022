package service

import munit.FunSuite
import domain.RockPaperScissorsShape.*

class RockPaperScissorsScoreCalculatorTest extends FunSuite {

  private val calculator = RockPaperScissorsScoreCalculator()

  test("Round score is correct for the given shapes") {
    assertEquals(calculator.getRoundScore(Rock, Paper), 8)
    assertEquals(calculator.getRoundScore(Rock, Scissors), 3)
    assertEquals(calculator.getRoundScore(Rock, Rock), 4)
    assertEquals(calculator.getRoundScore(Paper, Paper), 5)
    assertEquals(calculator.getRoundScore(Paper, Scissors), 9)
    assertEquals(calculator.getRoundScore(Paper, Rock), 1)
    assertEquals(calculator.getRoundScore(Scissors, Paper), 2)
    assertEquals(calculator.getRoundScore(Scissors, Scissors), 6)
    assertEquals(calculator.getRoundScore(Scissors, Rock), 7)
  }
}
