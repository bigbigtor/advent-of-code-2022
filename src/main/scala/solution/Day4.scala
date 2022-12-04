package solution

import domain.Assignment
import service.SectionAssignmentOverlapCounter


class Day4 {

  private val counter: SectionAssignmentOverlapCounter = SectionAssignmentOverlapCounter()
  def part1(input: String): Int = counter.countOverlappedAssignments(getAssignmentPairs(input))
  
  private def getAssignmentPairs(input: String): Array[List[Assignment]] =
    input.split("\\n")
      .map(line => {
        line.split(",")
          .map(_.split("-"))
          .map(split => (split(0).toInt to split(1).toInt).toSet)
          .toList
      })
}
