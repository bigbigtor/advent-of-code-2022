package service

import domain.{PlantationMap, Tree}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.*

class QuadcopterTest extends AnyFunSuite {

  private val quadcopter = Quadcopter()

  private val input =
    """|30373
       |25512
       |65332
       |33549
       |35390""".stripMargin

  test("Quadcopter should generate a correct map") {
    quadcopter.generateMap(input) should equal (expectedMap)
  }

  private def expectedMap: PlantationMap =
    val plantationMap = Array.ofDim[Tree](5, 5)
    plantationMap(0)(0) = Tree(3)
    plantationMap(0)(1) = Tree(0)
    plantationMap(0)(2) = Tree(3)
    plantationMap(0)(3) = Tree(7)
    plantationMap(0)(4) = Tree(3)
    plantationMap(1)(0) = Tree(2)
    plantationMap(1)(1) = Tree(5)
    plantationMap(1)(2) = Tree(5)
    plantationMap(1)(3) = Tree(1)
    plantationMap(1)(4) = Tree(2)
    plantationMap(2)(0) = Tree(6)
    plantationMap(2)(1) = Tree(5)
    plantationMap(2)(2) = Tree(3)
    plantationMap(2)(3) = Tree(3)
    plantationMap(2)(4) = Tree(2)
    plantationMap(3)(0) = Tree(3)
    plantationMap(3)(1) = Tree(3)
    plantationMap(3)(2) = Tree(5)
    plantationMap(3)(3) = Tree(4)
    plantationMap(3)(4) = Tree(9)
    plantationMap(4)(0) = Tree(3)
    plantationMap(4)(1) = Tree(5)
    plantationMap(4)(2) = Tree(3)
    plantationMap(4)(3) = Tree(9)
    plantationMap(4)(4) = Tree(0)
    plantationMap
}
