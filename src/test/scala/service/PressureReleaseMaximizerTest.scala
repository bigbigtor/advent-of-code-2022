package service

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.contain
import org.scalatest.matchers.should.Matchers.{equal, should, theSameElementsInOrderAs, theSameElementsAs}

class PressureReleaseMaximizerTest extends AnyFunSuite {

  private val input = """Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
                        |Valve BB has flow rate=13; tunnels lead to valves CC, AA
                        |Valve CC has flow rate=2; tunnels lead to valves DD, BB
                        |Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE
                        |Valve EE has flow rate=3; tunnels lead to valves FF, DD
                        |Valve FF has flow rate=0; tunnels lead to valves EE, GG
                        |Valve GG has flow rate=0; tunnels lead to valves FF, HH
                        |Valve HH has flow rate=22; tunnel leads to valve GG
                        |Valve II has flow rate=0; tunnels lead to valves AA, JJ
                        |Valve JJ has flow rate=21; tunnel leads to valve II""".stripMargin

  private val valves = Array(
    ("AA", 0, Set("DD", "II", "BB")),
    ("BB", 13, Set("CC", "AA")),
    ("CC", 2, Set("DD", "BB")),
    ("DD", 20, Set("CC", "AA", "EE")),
    ("EE", 3, Set("FF", "DD")),
    ("FF", 0, Set("EE", "GG")),
    ("GG", 0, Set("FF", "HH")),
    ("HH", 22, Set("GG")),
    ("II", 0, Set("AA", "JJ")),
    ("JJ", 21, Set("II"))
  )

  private val connections = Map(
    ("AA", Set("DD", "II", "BB")),
    ("BB", Set("CC", "AA")),
    ("CC", Set("DD", "BB")),
    ("DD", Set("CC", "AA", "EE")),
    ("EE", Set("FF", "DD")),
    ("FF", Set("EE", "GG")),
    ("GG", Set("FF", "HH")),
    ("HH", Set("GG")),
    ("II", Set("AA", "JJ")),
    ("JJ", Set("II")),
  )

  private val graph = Map(
    (("FF", "DD"), 2),
    (("EE", "HH"), 3),
    (("II", "CC"), 3),
    (("CC", "HH"), 5),
    (("AA", "BB"), 1),
    (("BB", "DD"), 2),
    (("DD", "BB"), 2),
    (("GG", "BB"), 5),
    (("GG", "JJ"), 6),
    (("FF", "HH"), 2),
    (("EE", "BB"), 3),
    (("DD", "JJ"), 3),
    (("BB", "DD"), 2),
    (("CC", "EE"), 2),
    (("GG", "HH"), 1),
    (("CC", "BB"), 1),
    (("JJ", "EE"), 4),
    (("DD", "HH"), 4),
    (("AA", "JJ"), 2),
    (("II", "JJ"), 1),
    (("AA", "DD"), 1),
    (("GG", "JJ"), 6),
    (("FF", "HH"), 2),
    (("HH", "EE"), 3),
    (("HH", "CC"), 5),
    (("HH", "JJ"), 7),
    (("EE", "DD"), 1),
    (("JJ", "BB"), 3),
    (("DD", "CC"), 1),
    (("JJ", "HH"), 7),
    (("FF", "EE"), 1),
    (("FF", "CC"), 3),
    (("GG", "EE"), 2),
    (("BB", "EE"), 3),
    (("II", "HH"), 6),
    (("AA", "EE"), 2),
    (("BB", "HH"), 6),
    (("EE", "JJ"), 4),
    (("GG", "CC"), 4),
    (("EE", "CC"), 2),
    (("II", "CC"), 3),
    (("FF", "JJ"), 5),
    (("GG", "DD"), 3),
    (("JJ", "CC"), 4),
    (("AA", "CC"), 2),
    (("II", "BB"), 2),
    (("AA", "HH"), 5),
    (("II", "DD"), 2),
    (("JJ", "DD"), 3),
    (("FF", "BB"), 4),
    (("DD", "EE"), 1),
    (("HH", "BB"), 6),
    (("CC", "JJ"), 4),
    (("II", "EE"), 3),
    (("BB", "JJ"), 3),
    (("HH", "DD"), 4),
    (("CC", "DD"), 1),
    (("BB", "CC"), 1),
    (("FF", "EE"), 1)
  )

  private val scores = Map[String, Int](
    ("AA", 0),
    ("BB", 13),
    ("CC", 2),
    ("DD", 20),
    ("EE", 3),
    ("FF", 0),
    ("GG", 0),
    ("HH", 22),
    ("II", 0),
    ("JJ", 21)
  )

  private val exampleValves = List("AA", "DD", "BB", "JJ", "HH", "EE", "CC")

  private val maximizer = PressureReleaseMaximizer()

  test("Compute returns the expected release for the given input") {
    maximizer.compute(input) should equal (1651)
  }

  test("Parse returns the expected results") {
    maximizer.parse(input) should contain theSameElementsInOrderAs valves
  }

  test("Build graph returns the expected graph for the given valves") {
    maximizer.buildGraph(connections, scores) should contain theSameElementsAs graph
  }

  test("Distance between valves are correct for the given input") {
    maximizer.getDistancesBetweenValves("AA", "BB", connections) should equal (1)
    maximizer.getDistancesBetweenValves("AA", "EE", connections) should equal (2)
    maximizer.getDistancesBetweenValves("AA", "HH", connections) should equal (5)
    maximizer.getDistancesBetweenValves("II", "EE", connections) should equal (3)
  }

  test("Connection map is build correctly for the given input"){
    maximizer.getConnectionMap(valves) should equal (connections)
  }

  test("Score map is build correctly for the given input"){
    maximizer.getScores(valves) should equal (scores)
  }

  test("Total release is correct for the given valves") {
    maximizer.getRelease(exampleValves, graph, scores) should equal (1651)
  }
}
