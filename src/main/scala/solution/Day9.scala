package solution

import domain.{Rope, Knot}
import domain.Direction.*
import service.PlanckLengthSimulator

import scala.collection.mutable

class Day9 {

  private val simulator = PlanckLengthSimulator()

  def part1(input: String): Int = simulate(input, 2)
   
  def part2(input: String): Int = simulate(input, 10)

  private def simulate(input: String, ropeSize: Int): Int =
    val positionsVisitedByTail = mutable.Set[Knot](Knot(0, 0))
    var rope = Rope(Array.fill(ropeSize)(Knot(0, 0)))
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
      positionsVisitedByTail.add(rope.knots.last)
    }
    positionsVisitedByTail.size
}
