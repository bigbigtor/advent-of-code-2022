package service

import domain.{Direction, Rope, Knot}
import domain.Direction.*

class PlanckLengthSimulator:

  def applyMovement(movement: Direction, rope: Rope): Rope =
    val result: Rope = Rope(rope.knots.map(knot => Knot(knot.x, knot.y)))
    movement match
      case Left => result.knots(0).x -= 1
      case Right => result.knots(0).x += 1
      case Top => result.knots(0).y += 1
      case Bottom => result.knots(0).y -= 1
    result.knots.sliding(2, 1)
      .map(knots => (knots.last, knots.head))
      .foreach((current, previous) => {
        if (!endsAreTouching(current, previous))
          movement match
            case Left | Right =>
              current.x += previous.x.compareTo(current.x)
              current.y += previous.y.compareTo(current.y)
            case Top | Bottom =>
              current.y += previous.y.compareTo(current.y)
              current.x += previous.x.compareTo(current.x)
      })
    result

  def endsAreTouching(knot1: Knot, knot2: Knot): Boolean =
    (knot1.x - knot2.x).abs <= 1 && (knot1.y - knot2.y).abs <= 1
