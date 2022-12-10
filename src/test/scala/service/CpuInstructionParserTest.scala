package service

import domain.Op
import domain.Op.*
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

class CpuInstructionParserTest extends AnyFunSuite {

  private val input: String = """|noop
                                 |addx 3
                                 |addx -5""".stripMargin

  private val expectedOutput: Array[Op] = Array(Noop, Addx(3), Addx(-5))

  private val parser: CpuInstructionParser = CpuInstructionParser()

  test("Parser returns the expected instructions for the given input") {
    parser.parse(input) should equal (expectedOutput)
  }
}
