package service

import domain.Assignment

class SectionAssignmentTotalOverlapCounter extends SectionAssignmentOverlapCounter {

  def countOverlappedAssignments(assignments: Array[List[Assignment]]): Int =
    assignments.count(pair => {
      val first = pair.head
      val second = pair(1)
      first.union(second).size.equals(first.size.max(second.size))
    })
}
