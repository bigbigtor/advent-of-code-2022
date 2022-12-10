package service

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

class CrtTest extends AnyFunSuite {

  private val expected: String = """..............#.........................
                                   |.......................................#
                                   |.....................#..................
                                   |......#.................................
                                   |...............................#........
                                   |.................#......................""".stripMargin

  private val crt: Crt = Crt()

  test("Drawn image matches lit pixels in crt") {
    crt.lightUpPixel(14)
    crt.lightUpPixel(79)
    crt.lightUpPixel(101)
    crt.lightUpPixel(126)
    crt.lightUpPixel(191)
    crt.lightUpPixel(217)
    crt.draw should equal (expected)
  }
}
