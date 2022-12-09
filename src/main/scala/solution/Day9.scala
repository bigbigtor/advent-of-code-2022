package solution

import domain.{Rope, RopeEnd}
import domain.Direction.*
import service.PlanckLengthSimulator

import scala.collection.mutable

class Day9 {

  private val simulator = PlanckLengthSimulator()

  def part1(input: String): Int =
    val positionsVisitedByTail = mutable.Set[RopeEnd](RopeEnd(0, 0))
    var rope = Rope(RopeEnd(0, 0), RopeEnd(0, 0))
    val movements = input.split("\n")
      .flatMap(line => {
        val Array(dir, steps) = line.split(" ")
        List.fill(steps.toInt)(dir)
      })
      .map(
        _ match
          case "L" => Left
          case "R" => Right
          case "U" => Top
          case "D" => Bottom
      ).toList
    for (movement <- movements) {
      rope = simulator.applyMovement(movement, rope)
      positionsVisitedByTail.add(rope.tail)
    }
    positionsVisitedByTail.size
}
