package service

import domain.{Rope, Knot}
import domain.Direction.*
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should, contain, theSameElementsInOrderAs}

class PlanckLengthSimulatorTest extends AnyFunSuite {

  private val simulator: PlanckLengthSimulator = PlanckLengthSimulator()

  test("endsAreTouching works for the given ropes") {
    simulator.endsAreTouching(Knot(1, 1), Knot(1, 2)) should equal (true)
    simulator.endsAreTouching(Knot(1, 2), Knot(2, 1)) should equal (true)
    simulator.endsAreTouching(Knot(1, 1), Knot(1, 1)) should equal (true)
    simulator.endsAreTouching(Knot(1, 1), Knot(1, 3)) should equal (false)
    simulator.endsAreTouching(Knot(1, 1), Knot(3, 1)) should equal (false)
  }

  test("applyMovement works for the given rope and movement") {
    simulator.applyMovement(Right, Rope(Array(Knot(2, 1), Knot(1, 1)))).knots should contain theSameElementsInOrderAs Array(Knot(3, 1), Knot(2, 1))
    simulator.applyMovement(Bottom, Rope(Array(Knot(1, 2), Knot(1, 3)))).knots should contain theSameElementsInOrderAs Array(Knot(1, 1), Knot(1, 2))
    simulator.applyMovement(Top, Rope(Array(Knot(2, 2), Knot(1, 1)))).knots should contain theSameElementsInOrderAs Array(Knot(2, 3), Knot(2, 2))
    simulator.applyMovement(Right, Rope(Array(Knot(2, 2), Knot(1, 1)))).knots should contain theSameElementsInOrderAs Array(Knot(3, 2), Knot(2, 2))
  }

  test("applyMovement works for 10 knot rope") {
    val s00 = Array(Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))
    //R 4
    val s01 = Array(Knot(1, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))
    val s02 = Array(Knot(2, 0), Knot(1, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))
    val s03 = Array(Knot(3, 0), Knot(2, 0), Knot(1, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))
    val s04 = Array(Knot(4, 0), Knot(3, 0), Knot(2, 0), Knot(1, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))
    //U 4
    val s05 = Array(Knot(4, 1), Knot(3, 0), Knot(2, 0), Knot(1, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))
    val s06 = Array(Knot(4, 2), Knot(4, 1), Knot(3, 1), Knot(2, 1), Knot(1, 1), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))
    val s07 = Array(Knot(4, 3), Knot(4, 2), Knot(3, 1), Knot(2, 1), Knot(1, 1), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))
    val s08 = Array(Knot(4, 4), Knot(4, 3), Knot(4, 2), Knot(3, 2), Knot(2, 2), Knot(1, 1), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))
    //L 3
    val s09 = Array(Knot(3, 4), Knot(4, 3), Knot(4, 2), Knot(3, 2), Knot(2, 2), Knot(1, 1), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))
    val s10 = Array(Knot(2, 4), Knot(3, 4), Knot(3, 3), Knot(3, 2), Knot(2, 2), Knot(1, 1), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))
    val s11 = Array(Knot(1, 4), Knot(2, 4), Knot(3, 3), Knot(3, 2), Knot(2, 2), Knot(1, 1), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))
    //D 1
    val s12 = Array(Knot(1, 3), Knot(2, 4), Knot(3, 3), Knot(3, 2), Knot(2, 2), Knot(1, 1), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))
    //R 4
    val s13 = Array(Knot(2, 3), Knot(2, 4), Knot(3, 3), Knot(3, 2), Knot(2, 2), Knot(1, 1), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))
    val s14 = Array(Knot(3, 3), Knot(2, 4), Knot(3, 3), Knot(3, 2), Knot(2, 2), Knot(1, 1), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))
    val s15 = Array(Knot(4, 3), Knot(3, 3), Knot(3, 3), Knot(3, 2), Knot(2, 2), Knot(1, 1), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))
    val s16 = Array(Knot(5, 3), Knot(4, 3), Knot(3, 3), Knot(3, 2), Knot(2, 2), Knot(1, 1), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))
    //D 1
    val s17 = Array(Knot(5, 2), Knot(4, 3), Knot(3, 3), Knot(3, 2), Knot(2, 2), Knot(1, 1), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))
    //L 5
    val s18 = Array(Knot(4, 2), Knot(4, 3), Knot(3, 3), Knot(3, 2), Knot(2, 2), Knot(1, 1), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))
    val s19 = Array(Knot(3, 2), Knot(4, 3), Knot(3, 3), Knot(3, 2), Knot(2, 2), Knot(1, 1), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))
    val s20 = Array(Knot(2, 2), Knot(3, 2), Knot(3, 3), Knot(3, 2), Knot(2, 2), Knot(1, 1), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))
    val s21 = Array(Knot(1, 2), Knot(2, 2), Knot(3, 3), Knot(3, 2), Knot(2, 2), Knot(1, 1), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))
    val s22 = Array(Knot(0, 2), Knot(1, 2), Knot(2, 2), Knot(3, 2), Knot(2, 2), Knot(1, 1), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))
    //R 2
    val s23 = Array(Knot(1, 2), Knot(1, 2), Knot(2, 2), Knot(3, 2), Knot(2, 2), Knot(1, 1), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))
    val s24 = Array(Knot(2, 2), Knot(1, 2), Knot(2, 2), Knot(3, 2), Knot(2, 2), Knot(1, 1), Knot(0, 0), Knot(0, 0), Knot(0, 0), Knot(0, 0))

    //R 4
    simulator.applyMovement(Right, Rope(s00)).knots should contain theSameElementsInOrderAs s01
    simulator.applyMovement(Right, Rope(s01)).knots should contain theSameElementsInOrderAs s02
    simulator.applyMovement(Right, Rope(s02)).knots should contain theSameElementsInOrderAs s03
    simulator.applyMovement(Right, Rope(s03)).knots should contain theSameElementsInOrderAs s04
    //U 4
    simulator.applyMovement(Top, Rope(s04)).knots should contain theSameElementsInOrderAs s05
    simulator.applyMovement(Top, Rope(s05)).knots should contain theSameElementsInOrderAs s06
    simulator.applyMovement(Top, Rope(s06)).knots should contain theSameElementsInOrderAs s07
    simulator.applyMovement(Top, Rope(s07)).knots should contain theSameElementsInOrderAs s08
    //L 3
    simulator.applyMovement(Left, Rope(s08)).knots should contain theSameElementsInOrderAs s09
    simulator.applyMovement(Left, Rope(s09)).knots should contain theSameElementsInOrderAs s10
    simulator.applyMovement(Left, Rope(s10)).knots should contain theSameElementsInOrderAs s11
    //D 1
    simulator.applyMovement(Bottom, Rope(s11)).knots should contain theSameElementsInOrderAs s12
    //R 4
    simulator.applyMovement(Right, Rope(s12)).knots should contain theSameElementsInOrderAs s13
    simulator.applyMovement(Right, Rope(s13)).knots should contain theSameElementsInOrderAs s14
    simulator.applyMovement(Right, Rope(s14)).knots should contain theSameElementsInOrderAs s15
    simulator.applyMovement(Right, Rope(s15)).knots should contain theSameElementsInOrderAs s16
    //D 1
    simulator.applyMovement(Bottom, Rope(s16)).knots should contain theSameElementsInOrderAs s17
    //L 5
    simulator.applyMovement(Left, Rope(s17)).knots should contain theSameElementsInOrderAs s18
    simulator.applyMovement(Left, Rope(s18)).knots should contain theSameElementsInOrderAs s19
    simulator.applyMovement(Left, Rope(s19)).knots should contain theSameElementsInOrderAs s20
    simulator.applyMovement(Left, Rope(s20)).knots should contain theSameElementsInOrderAs s21
    simulator.applyMovement(Left, Rope(s21)).knots should contain theSameElementsInOrderAs s22
    //R 2
    simulator.applyMovement(Right, Rope(s22)).knots should contain theSameElementsInOrderAs s23
    simulator.applyMovement(Right, Rope(s23)).knots should contain theSameElementsInOrderAs s24
  }

  test("applyMovement works for 10 knot rope wider movement") {
    val s00 = Array(Knot(11, 5), Knot(11, 5), Knot(11, 5), Knot(11, 5), Knot(11, 5), Knot(11, 5), Knot(11, 5), Knot(11, 5), Knot(11, 5), Knot(11, 5))
    val s01 = Array(Knot(16, 5), Knot(15, 5), Knot(14, 5), Knot(13, 5), Knot(12, 5), Knot(11, 5), Knot(11, 5), Knot(11, 5), Knot(11, 5), Knot(11, 5))
    val s02 = Array(Knot(16, 13), Knot(16, 12), Knot(16, 11), Knot(16, 10), Knot(16, 9), Knot(15, 9), Knot(14, 8), Knot(13, 7), Knot(12, 6), Knot(11, 5))
    val s03 = Array(Knot(8, 13), Knot(9, 13), Knot(10, 13), Knot(11, 13), Knot(12, 13), Knot(12, 12), Knot(12, 11), Knot(12, 10), Knot(12, 9), Knot(12, 8))
    val s04 = Array(Knot(8, 10), Knot(8, 11), Knot(9, 12), Knot(10, 12), Knot(11, 12), Knot(12, 12), Knot(12, 11), Knot(12, 10), Knot(12, 9), Knot(12, 8))
    val s05 = Array(Knot(25, 10), Knot(24, 10), Knot(23, 10), Knot(22, 10), Knot(21, 10), Knot(20, 10), Knot(19, 10), Knot(18, 10), Knot(17, 10), Knot(16, 10))
    val s06 = Array(Knot(25, 0), Knot(25, 1), Knot(25, 2), Knot(25, 3), Knot(25, 4), Knot(25, 5), Knot(24, 5), Knot(23, 5), Knot(22, 5), Knot(21, 5))
    val s07 = Array(Knot(0, 0), Knot(1, 0), Knot(2, 0), Knot(3, 0), Knot(4, 0), Knot(5, 0), Knot(6, 0), Knot(7, 0), Knot(8, 0), Knot(9, 0))
    val s08 = Array(Knot(0, 20), Knot(0, 19), Knot(0, 18), Knot(0, 17), Knot(0, 16), Knot(0, 15), Knot(0, 14), Knot(0, 13), Knot(0, 12), Knot(0, 11))
    var rope = Rope(s00)
    //R 5
    (0 until 5).foreach(_ => rope = simulator.applyMovement(Right, rope))
    rope.knots should contain theSameElementsInOrderAs s01
    //U 8
    (0 until 8).foreach(_ => rope = simulator.applyMovement(Top, rope))
    rope.knots should contain theSameElementsInOrderAs s02
    //L 8
    (0 until 8).foreach(_ => rope = simulator.applyMovement(Left, rope))
    rope.knots should contain theSameElementsInOrderAs s03
    //D 3
    (0 until 3).foreach(_ => rope = simulator.applyMovement(Bottom, rope))
    rope.knots should contain theSameElementsInOrderAs s04
    //R 17
    (0 until 17).foreach(_ => rope = simulator.applyMovement(Right, rope))
    rope.knots should contain theSameElementsInOrderAs s05
    //D 10
    (0 until 10).foreach(_ => rope = simulator.applyMovement(Bottom, rope))
    rope.knots should contain theSameElementsInOrderAs s06
    //L 25
    (0 until 25).foreach(_ => rope = simulator.applyMovement(Left, rope))
    rope.knots should contain theSameElementsInOrderAs s07
    //U 20
    (0 until 20).foreach(_ => rope = simulator.applyMovement(Top, rope))
    rope.knots should contain theSameElementsInOrderAs s08
  }
}
