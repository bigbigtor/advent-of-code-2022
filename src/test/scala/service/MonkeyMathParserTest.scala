package service

import org.scalatest.funsuite.AnyFunSuite
import domain.MonkeyOp.*
import org.scalatest.matchers.should.Matchers.shouldBe

class MonkeyMathParserTest extends AnyFunSuite {

  private val input = """root: pppw + sjmn
                        |dbpl: 5
                        |cczh: sllz + lgvd
                        |zczc: 2
                        |ptdq: humn - dvpt
                        |dvpt: 3
                        |lfqf: 4
                        |humn: 5
                        |ljgn: 2
                        |sjmn: drzm * dbpl
                        |sllz: 4
                        |pppw: cczh / lfqf
                        |lgvd: ljgn * ptdq
                        |drzm: hmdt - zczc
                        |hmdt: 32""".stripMargin

  private val expectedEquations = Seq(
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

  private val expectedCorrectEquations = Seq(
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

  private val parser = MonkeyMathParser()

  test("Parser returns expected entries") {
    parser.parse(input) shouldBe expectedEquations
  }

  test("Correct parser returns expected entries") {
    parser.correctParse(input) shouldBe expectedCorrectEquations
  }
}
