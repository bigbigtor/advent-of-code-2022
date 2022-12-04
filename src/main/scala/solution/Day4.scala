package solution

import domain.Assignment
import domain.OverlapCountingStrategy.*
import service.SectionAssignmentOverlapCounter


class Day4 {

  private val totalCounter: SectionAssignmentOverlapCounter = SectionAssignmentOverlapCounter(Total)
  private val partialCounter: SectionAssignmentOverlapCounter = SectionAssignmentOverlapCounter(Partial)

  def part1(input: String): Int = totalCounter.countOverlappedAssignments(getAssignmentPairs(input))

  def part2(input: String): Int = partialCounter.countOverlappedAssignments(getAssignmentPairs(input))

  private def getAssignmentPairs(input: String): Array[List[Assignment]] =
    input.split("\\n")
      .map(line => {
        line.split(",")
          .map(_.split("-"))
          .map(split => (split(0).toInt to split(1).toInt).toSet)
          .toList
      })
}
