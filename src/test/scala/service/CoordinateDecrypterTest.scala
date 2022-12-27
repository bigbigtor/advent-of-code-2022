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

  private val coordsWithCorrectKey = List(
    Coordinate(811589153),
    Coordinate(1623178306),
    Coordinate(-2434767459),
    Coordinate(2434767459L),
    Coordinate(-1623178306),
    Coordinate(0),
    Coordinate(3246356612L),
  )

  private val coordsWithCorrectKey1Time = List(
    Coordinate(0),
    Coordinate(-2434767459),
    Coordinate(3246356612L),
    Coordinate(-1623178306),
    Coordinate(2434767459L),
    Coordinate(1623178306),
    Coordinate(811589153),
  )

  private val coordsWithCorrectKey2Time = List(
    Coordinate(0),
    Coordinate(2434767459L),
    Coordinate(1623178306),
    Coordinate(3246356612L),
    Coordinate(-2434767459),
    Coordinate(-1623178306),
    Coordinate(811589153),
  )

  private val coordsWithCorrectKey3Time = List(
    Coordinate(0),
    Coordinate(811589153),
    Coordinate(2434767459L),
    Coordinate(3246356612L),
    Coordinate(1623178306),
    Coordinate(-1623178306),
    Coordinate(-2434767459),
  )

  private val coordsWithCorrectKey4Time = List(
    Coordinate(0),
    Coordinate(1623178306),
    Coordinate(-2434767459),
    Coordinate(811589153),
    Coordinate(2434767459L),
    Coordinate(3246356612L),
    Coordinate(-1623178306),
  )

  private val coordsWithCorrectKey5Time = List(
    Coordinate(0),
    Coordinate(811589153),
    Coordinate(-1623178306),
    Coordinate(1623178306),
    Coordinate(-2434767459),
    Coordinate(3246356612L),
    Coordinate(2434767459L),
  )

  private val coordsWithCorrectKey6Time = List(
    Coordinate(0),
    Coordinate(811589153),
    Coordinate(-1623178306),
    Coordinate(3246356612L),
    Coordinate(-2434767459),
    Coordinate(1623178306),
    Coordinate(2434767459L),
  )

  private val coordsWithCorrectKey7Time = List(
    Coordinate(0),
    Coordinate(-2434767459),
    Coordinate(2434767459L),
    Coordinate(1623178306),
    Coordinate(-1623178306),
    Coordinate(811589153),
    Coordinate(3246356612L),
  )

  private val coordsWithCorrectKey8Time = List(
    Coordinate(0),
    Coordinate(1623178306),
    Coordinate(3246356612L),
    Coordinate(811589153),
    Coordinate(-2434767459),
    Coordinate(2434767459L),
    Coordinate(-1623178306),
  )

  private val coordsWithCorrectKey9Time = List(
    Coordinate(0),
    Coordinate(811589153),
    Coordinate(1623178306),
    Coordinate(-2434767459),
    Coordinate(3246356612L),
    Coordinate(2434767459L),
    Coordinate(-1623178306),
  )

  private val mixedCoordsWithCorrectKey10Times = List(
    Coordinate(0),
    Coordinate(-2434767459),
    Coordinate(1623178306),
    Coordinate(3246356612L),
    Coordinate(-1623178306),
    Coordinate(2434767459L),
    Coordinate(811589153)
  )

  private val sumOfCoordsWithCorrectKey10Times = 1623178306

  private val decrypter = CoordinateDecrypter()

  test("Coordinates are parsed as expected"){
    decrypter.parse(input, 1) shouldBe coords
    decrypter.parse(input, 811589153) shouldBe coordsWithCorrectKey
  }

  test("Coordinates are mixed as expected"){
    decrypter.mix(coords, 1) shouldBe mixedCoords
    decrypter.mix(coordsWithCorrectKey, 1) shouldBe coordsWithCorrectKey1Time
    decrypter.mix(coordsWithCorrectKey, 2) shouldBe coordsWithCorrectKey2Time
    decrypter.mix(coordsWithCorrectKey, 3) shouldBe coordsWithCorrectKey3Time
    decrypter.mix(coordsWithCorrectKey, 4) shouldBe coordsWithCorrectKey4Time
    decrypter.mix(coordsWithCorrectKey, 5) shouldBe coordsWithCorrectKey5Time
    decrypter.mix(coordsWithCorrectKey, 6) shouldBe coordsWithCorrectKey6Time
    decrypter.mix(coordsWithCorrectKey, 7) shouldBe coordsWithCorrectKey7Time
    decrypter.mix(coordsWithCorrectKey, 8) shouldBe coordsWithCorrectKey8Time
    decrypter.mix(coordsWithCorrectKey, 9) shouldBe coordsWithCorrectKey9Time
    decrypter.mix(coordsWithCorrectKey, 10) shouldBe mixedCoordsWithCorrectKey10Times
  }

  test("Sum of mixed coordinates returns the expected value"){
    decrypter.extractGroveCoords(mixedCoords) shouldBe sumOfCoords
    decrypter.extractGroveCoords(mixedCoordsWithCorrectKey10Times) shouldBe sumOfCoordsWithCorrectKey10Times
  }
}
