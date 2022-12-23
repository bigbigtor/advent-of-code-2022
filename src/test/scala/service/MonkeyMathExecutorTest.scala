package service

import domain.MonkeyOp.*
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.shouldBe

class MonkeyMathExecutorTest extends AnyFunSuite {

  private val equationSeq = Seq(
    ("root", TwoValueSum("pppw", "sjmn")),
    ("dbpl", Value(5)),
    ("cczh", TwoValueSum("sllz", "lgvd")),
    ("zczc", Value(2)),
    ("ptdq", TwoValueSub("humn", "dvpt")),
    ("dvpt", Value(3)),
    ("lfqf", Value(4)),
    ("humn", Value(5)),
    ("ljgn", Value(2)),
    ("sjmn", TwoValueMul("drzm", "dbpl")),
    ("sllz", Value(4)),
    ("pppw", TwoValueDiv("cczh", "lfqf")),
    ("lgvd", TwoValueMul("ljgn", "ptdq")),
    ("drzm", TwoValueSub("hmdt", "zczc")),
    ("hmdt", Value(32))
  )

  private val executor = MonkeyMathExecutor()

  test("Computed value for root is correct for the given input") {
    executor.compute(equationSeq) shouldBe 152
  }
}
