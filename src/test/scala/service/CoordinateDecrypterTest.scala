package service

import domain.Coordinate
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.shouldBe

class CoordinateDecrypterTest extends AnyFunSuite {

  private val input = """1
                        |2
                        |-3
                        |3
                        |-2
                        |0
                        |4""".stripMargin

  private val coords = List(
    Coordinate(1),
    Coordinate(2),
    Coordinate(-3),
    Coordinate(3),
    Coordinate(-2),
    Coordinate(0),
    Coordinate(4),
  )

  private val mixedCoords = List(
    Coordinate(1),
    Coordinate(2),
    Coordinate(-3),
    Coordinate(4),
    Coordinate(0),
    Coordinate(3),
    Coordinate(-2)
  )

  private val sumOfCoords = 3

  private val decrypter = CoordinateDecrypter()

  test("Coordinates are parsed as expected"){
    decrypter.parse(input) shouldBe coords
  }

  test("Coordinates are mixed as expected"){
    decrypter.mix(coords) shouldBe mixedCoords
  }

  test("Sum of mixed coordinates returns the expected value"){
    decrypter.extractGroveCoords(mixedCoords) shouldBe sumOfCoords
  }
}
