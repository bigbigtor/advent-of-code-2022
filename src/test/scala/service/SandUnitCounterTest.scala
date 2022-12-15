package service

import domain.Cave
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

class SandUnitCounterTest extends AnyFunSuite {

  private val initialCave = """498,4 -> 498,6 -> 496,6
                              |503,4 -> 502,4 -> 502,9 -> 494,9""".stripMargin

  private val counter = SandUnitCounter()

  private val scanner = Scanner()

  private val expectedSandUnitCount = 24

  test("Sand unit count is correct for the given input") {
    val cave = scanner.scan(initialCave)
    counter.count(cave) should equal (expectedSandUnitCount)
  }
}
