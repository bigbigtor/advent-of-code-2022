package service

import domain.Assignment
import domain.OverlapCountingStrategy
import domain.OverlapCountingStrategy.*

trait SectionAssignmentOverlapCounter {

  def countOverlappedAssignments(assignments: Array[List[Assignment]]): Int
}

object SectionAssignmentOverlapCounter  {
  def apply(strategy: OverlapCountingStrategy): SectionAssignmentOverlapCounter = {
    strategy match {
      case Total => SectionAssignmentTotalOverlapCounter()
      case Partial =>  SectionAssignmentPartialOverlapCounter()
    }
  }
}

