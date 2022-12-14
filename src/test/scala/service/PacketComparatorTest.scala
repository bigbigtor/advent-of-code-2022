package service

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

class PacketComparatorTest extends AnyFunSuite {

  private val input = """[1,1,3,1,1]
                        |[1,1,5,1,1]
                        |
                        |[[5],[[[1,2,4,6],3,2,1],7,[[9,2,8],4,[1,0,5,9,4]]],[0,0,[[2,5]]]]
                        |[[[4,7,[3,9,10],[7,6,7,10,3]],10,10],[[],1],[[[7,0,4,7,2],[2],[],3,[10,10]]],[[[1,5,7,4,2],[8,7,6,6,9]],10,10,1]]
                        |
                        |[[1],[2,3,4]]
                        |[[1],4]
                        |
                        |[9]
                        |[[8,7,6]]
                        |
                        |[[4,4],4,4]
                        |[[4,4],4,4,4]
                        |
                        |[7,7,7,7]
                        |[7,7,7]
                        |
                        |[]
                        |[3]
                        |
                        |[[[]]]
                        |[[]]
                        |
                        |[1,[2,[3,[4,[5,6,7]]]],8,9]
                        |[1,[2,[3,[4,[5,6,0]]]],8,9]""".stripMargin

  private val expectedResults = Array(-1, 1, -1, 1, -1, 1, -1, 1, 1)

  private val comparator = PacketComparator()

  test("Comparator returns the correct results for the given input") {
    val packets = getPackets
    val results = packets.indices
      .map(i =>
        val (left, right) = packets(i)
        comparator.compare(left, right))
    results should equal (expectedResults)
  }

  private def getPackets: Array[(JsonNode, JsonNode)] =
    val mapper = ObjectMapper()
    input.split("\n\n")
      .map(pair =>
        val packetPair = pair.split("\n").map(line => mapper.readTree(line))
        (packetPair(0), packetPair(1))
      )
}
