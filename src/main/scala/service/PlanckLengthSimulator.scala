package service

import domain.{Direction, Rope, RopeEnd}
import domain.Direction.*

class PlanckLengthSimulator:

  def applyMovement(movement: Direction, rope: Rope): Rope =
    val result: Rope = Rope(RopeEnd(rope.head.x, rope.head.y), RopeEnd(rope.tail.x, rope.tail.y))
    movement match
      case Left => result.head.x -= 1
      case Right => result.head.x += 1
      case Top => result.head.y += 1
      case Bottom => result.head.y -= 1
    if (!endsAreTouching(result)) result.tail = rope.head
    result

  def endsAreTouching(rope: Rope): Boolean =
    (rope.head.x - rope.tail.x).abs <= 1 && (rope.head.y - rope.tail.y).abs <= 1
