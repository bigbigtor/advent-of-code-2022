package service

import domain.{Rope, RopeEnd}
import domain.Direction.*
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

class PlanckLengthSimulatorTest extends AnyFunSuite {

  private val simulator: PlanckLengthSimulator = PlanckLengthSimulator()

  test("endsAreTouching works for the given ropes") {
    simulator.endsAreTouching(Rope(RopeEnd(1, 1), RopeEnd(1, 2))) should equal (true)
    simulator.endsAreTouching(Rope(RopeEnd(1, 2), RopeEnd(2, 1))) should equal (true)
    simulator.endsAreTouching(Rope(RopeEnd(1, 1), RopeEnd(1, 1))) should equal (true)
    simulator.endsAreTouching(Rope(RopeEnd(1, 1), RopeEnd(1, 3))) should equal (false)
    simulator.endsAreTouching(Rope(RopeEnd(1, 1), RopeEnd(3, 1))) should equal (false)
  }

  test("applyMovement works for the given rope and movement") {
    simulator.applyMovement(Right, Rope(RopeEnd(2, 1), RopeEnd(1, 1))) should equal (Rope(RopeEnd(3, 1), RopeEnd(2, 1)))
    simulator.applyMovement(Bottom, Rope(RopeEnd(1, 2), RopeEnd(1, 3))) should equal (Rope(RopeEnd(1, 1), RopeEnd(1, 2)))
    simulator.applyMovement(Top, Rope(RopeEnd(2, 2), RopeEnd(1, 1))) should equal (Rope(RopeEnd(2, 3), RopeEnd(2, 2)))
    simulator.applyMovement(Right, Rope(RopeEnd(2, 2), RopeEnd(1, 1))) should equal (Rope(RopeEnd(3, 2), RopeEnd(2, 2)))
  }
}
