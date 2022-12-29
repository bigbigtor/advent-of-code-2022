package service

import scala.collection.mutable

class PressureReleaseMaximizer:

  def compute(input: String): Int =
    val valves = parse(input)
    val scores = getScores(valves)
    val graph = buildGraph(getConnectionMap(valves), scores)
    getOptimalPathRelease(List("AA"), 30, graph, scores)

  def getOptimalPathRelease(
                      visited: List[String],
                      timeLeft: Int,
                      graph: Map[(String, String), Int],
                      scores: Map[String, Int]
                    ): Int =
    val currentValve = visited.last
    val edges = graph
      .keys
      .filter(_._1 == currentValve)
      .filter(edge => !visited.contains(edge._2))
    val releases = edges
      .filter(edge => (timeLeft - (graph(edge) + 1)) >= 0)
      .map(edge =>
        getOptimalPathRelease(
          visited :+ edge._2,
          timeLeft - (graph(edge) + 1),
          graph,
          scores
        )
      )
    if (releases.nonEmpty)
      releases.max
    else
      getRelease(visited, graph, scores)

  def getRelease(
                  valves: List[String],
                  graph: Map[(String, String), Int],
                  scores: Map[String, Int]
                ): Int =
    var result = 0
    var timeLeft = 30
    valves.sliding(2, 1)
      .filter(_.length > 1)
      .foreach(slide =>
        val (start, end) = (slide.head, slide(1))
        timeLeft -= (graph((start, end)) + 1)
        result += timeLeft * scores(end)
      )
    result

  def getScores(valves: Array[(String, Int, Set[String])]): Map[String, Int] =
    valves.map((name, rate, _) => (name, rate)).toMap

  def parse(input: String): Array[(String, Int, Set[String])] =
    input.split("\n")
      .map(
        _ match
          case s"Valve ${name} has flow rate=${rate}; tunnels lead to valves ${connections}" =>
            (name, rate.toInt, connections.split(", ").toSet)
          case s"Valve ${name} has flow rate=${rate}; tunnel leads to valve ${connection}" =>
            (name, rate.toInt, Set(connection))
      )

  def buildGraph(
                  connections: Map[String, Set[String]],
                  scores: Map[String, Int]
                ): Map[(String, String), Int] =
    connections
      .keys
      .toList
      .combinations(2)
      .flatMap(valves => Array((valves.head, valves(1)), (valves(1), valves.head)))
      .filter((_, b) => scores(b) > 0)
      .map((a, b) => ((a, b), getDistancesBetweenValves(a, b, connections)))
      .toMap

  def getDistancesBetweenValves(
                                         start: String,
                                         end: String,
                                         connections: Map[String, Set[String]]
                                       ): Int =
    val visitedPositions = mutable.Set[String]()
    val distInitialization = connections.map((k, _) => (k, Int.MaxValue)).toMap
    val distances = mutable.Map[String, Int](distInitialization.toSeq: _*)
    distances(start) = 0
    while (visitedPositions.size < distances.size)
      val valve = distances
        .filter((name, _) => !visitedPositions.contains(name))
        .minBy((name, _) => distances(name))
        ._1
      visitedPositions.add(valve)
      connections(valve)
        .filter(distances(_) > distances(valve) + 1)
        .foreach(distances(_) = distances(valve) + 1)
    distances(end)

  def getConnectionMap(valves: Array[(String, Int, Set[String])]): Map[String, Set[String]] =
    valves.map((name, _, conn) => (name, conn)).toMap
