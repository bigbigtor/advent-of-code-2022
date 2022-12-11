package service

import domain.MonkeyOp.*
import domain.MonkeyTest.DivBy
import domain.{Monkey, MonkeyOp, MonkeyTest, WorryLevel}

import scala.collection.mutable

class MonkeyParser:

  def parse(input: String): Array[Monkey] =
    input.split("\n\n")
      .map(_.split("\n"))
      .map(lines => {
        Monkey(
          parseStartingItems(lines(1).trim),
          parseOperation(lines(2).trim),
          parseTest(lines(3).trim),
          parseTrueAction(lines(4).trim),
          parseFalseAction(lines(5).trim)
        )
      })

  private def parseStartingItems(input: String): mutable.Queue[WorryLevel] =
    val result = mutable.Queue[WorryLevel]()
    input.replaceAll("Starting items: ", "")
      .split(", ")
      .map(_.toLong)
      .foreach(result.enqueue)
    result

  private def parseOperation(input: String): MonkeyOp =
    input.replaceAll("Operation: new = ", "") match
      case "old * old" => Square
      case s"old * ${value}" => Mul(value.toInt)
      case s"old + ${value}" => Sum(value.toInt)

  private def parseTest(input: String): MonkeyTest =
    input.replaceAll("Test: ", "") match
      case s"divisible by ${value}" => DivBy(value.toInt)

  private def parseTrueAction(input: String): Int =
    input.replaceAll("If true: throw to monkey ", "").toInt

  private def parseFalseAction(input: String): Int =
    input.replaceAll("If false: throw to monkey ", "").toInt