package service

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

class SurfaceAreaScannerTest extends AnyFunSuite {

  private val input = """2,2,2
                        |1,2,2
                        |3,2,2
                        |2,1,2
                        |2,3,2
                        |2,2,1
                        |2,2,3
                        |2,2,4
                        |2,2,6
                        |1,2,5
                        |3,2,5
                        |2,1,5
                        |2,3,5""".stripMargin
  
  private val scanner = SurfaceAreaScanner()
  
  test("Surface area is correct for the given input") {
    scanner.scan(input) should equal (64)
  }
}
