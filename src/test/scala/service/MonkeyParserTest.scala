package service

import domain.Monkey
import domain.MonkeyOp.*
import domain.MonkeyTest.DivBy
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

import scala.collection.mutable

class MonkeyParserTest extends AnyFunSuite {

  private val input = """Monkey 0:
                        |  Starting items: 79, 98
                        |  Operation: new = old * 19
                        |  Test: divisible by 23
                        |    If true: throw to monkey 2
                        |    If false: throw to monkey 3
                        |
                        |Monkey 1:
                        |  Starting items: 54, 65, 75, 74
                        |  Operation: new = old + 6
                        |  Test: divisible by 19
                        |    If true: throw to monkey 2
                        |    If false: throw to monkey 0
                        |
                        |Monkey 2:
                        |  Starting items: 79, 60, 97
                        |  Operation: new = old * old
                        |  Test: divisible by 13
                        |    If true: throw to monkey 1
                        |    If false: throw to monkey 3
                        |
                        |Monkey 3:
                        |  Starting items: 74
                        |  Operation: new = old + 3
                        |  Test: divisible by 17
                        |    If true: throw to monkey 0
                        |    If false: throw to monkey 1""".stripMargin

  private val parser = MonkeyParser()

  test("Expected monkeys are parsed for the given input") {
    parser.parse(input) should equal (getExpectedMonkeys)
  }

  private def getExpectedMonkeys: Array[Monkey] =
    Array(
      Monkey(mutable.Queue(79, 98), Mul(19), DivBy(23), 2, 3),
      Monkey(mutable.Queue(54, 65, 75, 74), Sum(6), DivBy(19), 2, 0),
      Monkey(mutable.Queue(79, 60, 97), Square, DivBy(13), 1, 3),
      Monkey(mutable.Queue(74), Sum(3), DivBy(17), 0, 1)
    )
}
