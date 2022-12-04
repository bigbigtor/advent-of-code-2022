package service

import domain.Assignment

class SectionAssignmentPartialOverlapCounter extends SectionAssignmentOverlapCounter {

  def countOverlappedAssignments(assignments: Array[List[Assignment]]): Int =
    assignments.count(pair => pair.head.intersect(pair(1)).nonEmpty)
}
