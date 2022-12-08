package service

import domain.{PlantationMap, Tree}
import domain.Direction.*
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

class TreeVisibilityVerifierTest extends AnyFunSuite {

  private val verifier = TreeVisibilityVerifier()

  test("Verify works for the given input") {
    val map = getMap
    verifier.verify(map, 1, 1) should equal (Set(Left, Top))
    verifier.verify(map, 1, 2) should equal (Set(Right, Top))
    verifier.verify(map, 1, 3) should equal (Set())
    verifier.verify(map, 2, 1) should equal (Set(Right))
    verifier.verify(map, 2, 2) should equal (Set())
    verifier.verify(map, 2, 3) should equal (Set(Right))
    verifier.verify(map, 3, 2) should equal (Set(Left, Bottom))
    verifier.verify(map, 3, 1) should equal (Set())
    verifier.verify(map, 3, 3) should equal (Set())
  }

  test("Scenic score is correct for the given input") {
    val map = getMap
    verifier.getScenicScore(map, 1, 2) should equal (4)
    verifier.getScenicScore(map, 3, 2) should equal (8)
  }

  private def getMap: PlantationMap =
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
