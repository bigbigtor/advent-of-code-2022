package service

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.shouldBe

class GeodeCrackingMaximizerTest extends AnyFunSuite {

  private val blueprint1 = Array(0x00000004, 0x00000002, 0x00000e03, 0x00070002)

  private val blueprint2 = Array(0x00000002, 0x00000003, 0x00000803, 0x000c0003)

  private val maximizer = GeodeCrackingMaximizer()

  test("Count for the given blueprints is correct") {
    maximizer.count(blueprint1, 24) shouldBe 9
    maximizer.count(blueprint2, 24) shouldBe 12
  }

  test("Generated mask is correct for the given position") {
    maximizer.getMaskForPosition(0) shouldBe 0xff
    maximizer.getMaskForPosition(1) shouldBe 0xff00
    maximizer.getMaskForPosition(2) shouldBe 0xff0000
    maximizer.getMaskForPosition(3) shouldBe 0xff000000
  }

  test("Possible next states are correct for the given input") {
    maximizer.getPossibleNextStates(CrackingState(10, 0x01010101, 0x00051908), blueprint1, 0) shouldBe Array(
      CrackingState(9, 0x01010102, 0x01061a05),
      CrackingState(9, 0x01010201, 0x01061a07),
      CrackingState(9, 0x01020101, 0x01060c06),
      CrackingState(7, 0x02010101, 0x03011c09)
    )
  }

  test("Is needed returns the expected result for the given input") {
    maximizer.isNeeded(0, 0x00060e03, blueprint1) shouldBe true
    maximizer.isNeeded(1, 0x00060e03, blueprint1) shouldBe false
    maximizer.isNeeded(2, 0x00060e03, blueprint1) shouldBe true
    maximizer.isNeeded(3, 0x10060e03, blueprint1) shouldBe true
  }

  test("Is better than best returns the expected result for the given input") {
    maximizer.isBetterThanBest(CrackingState(10, 0x01010101, 0x01010101), 67) shouldBe false
    maximizer.isBetterThanBest(CrackingState(10, 0x01010101, 0x01010101), 65) shouldBe true
  }

  test("Build returns the expected state for the given input") {
    maximizer.build(
      CrackingState(10, 0x01010304, 0x01090305),
      0x00070002,
      3
    ) shouldBe CrackingState(10, 0x02010304, 0x01020303)
  }

  test("Harvest returns the expected state for the given input") {
    maximizer.harvest(CrackingState(10, 0x01000001, 0xfd0000aa), 2) shouldBe CrackingState(8, 0x01000001, 0xff0000ac)
    maximizer.harvest(CrackingState(10, 0x00010100, 0xfe0000aa), 1) shouldBe CrackingState(9, 0x00010100, 0xfe0101aa)
  }

  test("Time to build returns the expected results for the given input") {
    maximizer.getTimeToBuild(CrackingState(10, 0x00020001, 0x00010001), 0x00070002) shouldBe Some(4)
    maximizer.getTimeToBuild(CrackingState(10, 0x00020001, 0x00000001), 0x00070002) shouldBe Some(5)
    maximizer.getTimeToBuild(CrackingState(10, 0x00020001, 0x00000001), 0x00020007) shouldBe Some(7)
    maximizer.getTimeToBuild(CrackingState(5, 0x00020001, 0x00000001), 0x00020007) shouldBe None
    maximizer.getTimeToBuild(CrackingState(10, 0x00000001, 0x00000001), 0x00020007) shouldBe None
    maximizer.getTimeToBuild(CrackingState(10, 0x00010001, 0x00020009), 0x00020007) shouldBe Some(1)
    maximizer.getTimeToBuild(CrackingState(10, 0x01010101, 0x00051908), 0x00000004) shouldBe Some(1)
  }
}
