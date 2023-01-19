package service

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.shouldBe

class BlueprintParserTest extends AnyFunSuite {

  private val input = """Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
                        |Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay. Each geode robot costs 3 ore and 12 obsidian.""".stripMargin
  
  private val expectedBlueprints = Array(
    Array(0x00000004, 0x00000002, 0x00000e03, 0x00070002),
    Array(0x00000002, 0x00000003, 0x00000803, 0x000c0003)
  )

  private val parser = BlueprintParser()
  
  test("Parser returns the expected blueprints for the given input") {
    parser.parse(input) shouldBe expectedBlueprints
  }
}
