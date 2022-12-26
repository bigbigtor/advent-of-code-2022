package service

import domain.MonkeyOp.*
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.shouldBe

class MonkeyMathExecutorTest extends AnyFunSuite {

  private val equationSeq =  Seq(
    ("root", TwoValueSum(Variable("pppw"), Variable("sjmn"))),
    ("dbpl", Value(5)),
    ("cczh", TwoValueSum(Variable("sllz"), Variable("lgvd"))),
    ("zczc", Value(2)),
    ("ptdq", TwoValueSub(Variable("humn"), Variable("dvpt"))),
    ("dvpt", Value(3)),
    ("lfqf", Value(4)),
    ("humn", Value(5)),
    ("ljgn", Value(2)),
    ("sjmn", TwoValueMul(Variable("drzm"), Variable("dbpl"))),
    ("sllz", Value(4)),
    ("pppw", TwoValueDiv(Variable("cczh"), Variable("lfqf"))),
    ("lgvd", TwoValueMul(Variable("ljgn"), Variable("ptdq"))),
    ("drzm", TwoValueSub(Variable("hmdt"), Variable("zczc"))),
    ("hmdt", Value(32))
  )

  private val correctEquationSeq = Seq(
    ("root", Match(Variable("pppw"), Variable("sjmn"))),
    ("dbpl", Value(5)),
    ("cczh", TwoValueSum(Variable("sllz"), Variable("lgvd"))),
    ("zczc", Value(2)),
    ("ptdq", TwoValueSub(Variable("humn"), Variable("dvpt"))),
    ("dvpt", Value(3)),
    ("lfqf", Value(4)),
    ("ljgn", Value(2)),
    ("sjmn", TwoValueMul(Variable("drzm"), Variable("dbpl"))),
    ("sllz", Value(4)),
    ("pppw", TwoValueDiv(Variable("cczh"), Variable("lfqf"))),
    ("lgvd", TwoValueMul(Variable("ljgn"), Variable("ptdq"))),
    ("drzm", TwoValueSub(Variable("hmdt"), Variable("zczc"))),
    ("hmdt", Value(32))
  )

  private val executor = MonkeyMathExecutor()

  test("Computed value for root is correct for the given input") {
    executor.computeWithKnownTarget(equationSeq, "root") shouldBe 152
  }

  test("Computed value for humn with correct equations is correct for the given input") {
    executor.computeWithUnknownTarget(correctEquationSeq, "humn") shouldBe 301
  }
}
