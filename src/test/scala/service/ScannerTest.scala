package service

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

class ScannerTest extends AnyFunSuite {

  private val input = """498,4 -> 498,6 -> 496,6
                        |503,4 -> 502,4 -> 502,9 -> 494,9""".stripMargin

  private val expectedOutput = """......+...
                                 |..........
                                 |..........
                                 |..........
                                 |....#...##
                                 |....#...#.
                                 |..###...#.
                                 |........#.
                                 |........#.
                                 |#########.""".stripMargin

  private val scanner = Scanner()

  test("The expected cave is generated for the given input") {
    scanner.scan(input).toString should equal (expectedOutput)
  }
}
