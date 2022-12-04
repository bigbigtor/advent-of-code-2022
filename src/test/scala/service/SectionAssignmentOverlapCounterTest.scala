package service

import domain.Assignment
import munit.FunSuite

class SectionAssignmentOverlapCounterTest extends FunSuite {

  private val counter: SectionAssignmentOverlapCounter = SectionAssignmentOverlapCounter()

  test("Counter returns the correct amount of overlapped pairs for the given input") {
    assertEquals(counter.countOverlappedAssignments(getAssignments), 2)
  }

  private def getAssignments: Array[List[Assignment]] =
    """|2-4,6-8
       |2-3,4-5
       |5-7,7-9
       |2-8,3-7
       |6-6,4-6
       |2-6,4-8
       |""".stripMargin
      .split("\\n")
      .map(line => {
        line.split(",")
          .map(_.split("-"))
          .map(split => (split(0).toInt to split(1).toInt).toSet)
          .toList
      })
}
