package service

import domain.Monkey
import domain.MonkeyOp.*
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

import scala.collection.mutable

class MonkeyBusinessExecutorTest extends AnyFunSuite {

  private def getMonkeys: Array[Monkey] = Array(
    Monkey(mutable.Queue(79, 98), Mul(19), 23, 2, 3),
    Monkey(mutable.Queue(54, 65, 75, 74), Sum(6), 19, 2, 0),
    Monkey(mutable.Queue(79, 60, 97), Square, 13, 1, 3),
    Monkey(mutable.Queue(74), Sum(3), 17, 0, 1)
  )

  private val expectedMonkeysRound1 = Array(
    Monkey(mutable.Queue(20, 23, 27, 26), Mul(19), 23, 2, 3, 2),
    Monkey(mutable.Queue(2080, 25, 167, 207, 401, 1046), Sum(6), 19, 2, 0, 4),
    Monkey(mutable.Queue(), Square, 13, 1, 3, 3),
    Monkey(mutable.Queue(), Sum(3), 17, 0, 1, 5)
  )

  private val expectedMonkeysRound2 = Array(
    Monkey(mutable.Queue(695, 10, 71, 135, 350), Mul(19), 23, 2, 3, 6),
    Monkey(mutable.Queue(43, 49, 58, 55, 362), Sum(6), 19, 2, 0, 10),
    Monkey(mutable.Queue(), Square, 13, 1, 3, 4),
    Monkey(mutable.Queue(), Sum(3), 17, 0, 1, 10)
  )

  private val expectedMonkeysRound3 = Array(
    Monkey(mutable.Queue(16, 18, 21, 20, 122), Mul(19), 23, 2, 3, 11),
    Monkey(mutable.Queue(1468, 22, 150, 286, 739), Sum(6), 19, 2, 0, 15),
    Monkey(mutable.Queue(), Square, 13, 1, 3, 4),
    Monkey(mutable.Queue(), Sum(3), 17, 0, 1, 15)
  )

  private val expectedMonkeysRound4 = Array(
    Monkey(mutable.Queue(491, 9, 52, 97, 248, 34), Mul(19), 23, 2, 3, 16),
    Monkey(mutable.Queue(39, 45, 43, 258), Sum(6), 19, 2, 0, 20),
    Monkey(mutable.Queue(), Square, 13, 1, 3, 4),
    Monkey(mutable.Queue(), Sum(3), 17, 0, 1, 20)
  )

  private val expectedMonkeysRound5 = Array(
    Monkey(mutable.Queue(15, 17, 16, 88, 1037), Mul(19), 23, 2, 3, 22),
    Monkey(mutable.Queue(20, 110, 205, 524, 72), Sum(6), 19, 2, 0, 24),
    Monkey(mutable.Queue(), Square, 13, 1, 3, 4),
    Monkey(mutable.Queue(), Sum(3), 17, 0, 1, 26)
  )

  private val expectedMonkeysRound6 = Array(
    Monkey(mutable.Queue(8, 70, 176, 26, 34), Mul(19), 23, 2, 3, 27),
    Monkey(mutable.Queue(481, 32, 36, 186, 2190), Sum(6), 19, 2, 0, 29),
    Monkey(mutable.Queue(), Square, 13, 1, 3, 5),
    Monkey(mutable.Queue(), Sum(3), 17, 0, 1, 31)
  )

  private val expectedMonkeysRound7 = Array(
    Monkey(mutable.Queue(162, 12, 14, 64, 732, 17), Mul(19), 23, 2, 3, 32),
    Monkey(mutable.Queue(148, 372, 55, 72), Sum(6), 19, 2, 0, 34),
    Monkey(mutable.Queue(), Square, 13, 1, 3, 5),
    Monkey(mutable.Queue(), Sum(3), 17, 0, 1, 36)
  )

  private val expectedMonkeysRound8 = Array(
    Monkey(mutable.Queue(51, 126, 20, 26, 136), Mul(19), 23, 2, 3, 38),
    Monkey(mutable.Queue(343, 26, 30, 1546, 36), Sum(6), 19, 2, 0, 38),
    Monkey(mutable.Queue(), Square, 13, 1, 3, 5),
    Monkey(mutable.Queue(), Sum(3), 17, 0, 1, 42)
  )

  private val expectedMonkeysRound9 = Array(
    Monkey(mutable.Queue(116, 10, 12, 517, 14), Mul(19), 23, 2, 3, 43),
    Monkey(mutable.Queue(108, 267, 43, 55, 288), Sum(6), 19, 2, 0, 43),
    Monkey(mutable.Queue(), Square, 13, 1, 3, 5),
    Monkey(mutable.Queue(), Sum(3), 17, 0, 1, 47)
  )

  private val expectedMonkeysRound10 = Array(
    Monkey(mutable.Queue(91, 16, 20, 98), Mul(19), 23, 2, 3, 48),
    Monkey(mutable.Queue(481, 245, 22, 26, 1092, 30), Sum(6), 19, 2, 0, 48),
    Monkey(mutable.Queue(), Square, 13, 1, 3, 6),
    Monkey(mutable.Queue(), Sum(3), 17, 0, 1, 52)
  )

  private val expectedMonkeysRound15 = Array(
    Monkey(mutable.Queue(83, 44, 8, 184, 9, 20, 26, 102), Mul(19), 23, 2, 3, 73),
    Monkey(mutable.Queue(110, 36), Sum(6), 19, 2, 0, 73),
    Monkey(mutable.Queue(), Square, 13, 1, 3, 6),
    Monkey(mutable.Queue(), Sum(3), 17, 0, 1, 77)
  )

  private val expectedMonkeysRound20 = Array(
    Monkey(mutable.Queue(10, 12, 14, 26, 34), Mul(19), 23, 2, 3, 101),
    Monkey(mutable.Queue(245, 93, 53, 199, 115), Sum(6), 19, 2, 0, 95),
    Monkey(mutable.Queue(), Square, 13, 1, 3, 7),
    Monkey(mutable.Queue(), Sum(3), 17, 0, 1, 105)
  )

  test("Monkeys are holding the expected items after 20 round") {
    val divBy3ReliefFunction: Long => Long = level => Math.floorDiv(level, 3)
    val monkeys = getMonkeys
    val executor = MonkeyBusinessExecutor(monkeys, divBy3ReliefFunction)
    executor.executeRound()
    monkeys should equal (expectedMonkeysRound1)
    executor.executeRound()
    monkeys should equal(expectedMonkeysRound2)
    executor.executeRound()
    monkeys should equal(expectedMonkeysRound3)
    executor.executeRound()
    monkeys should equal(expectedMonkeysRound4)
    executor.executeRound()
    monkeys should equal(expectedMonkeysRound5)
    executor.executeRound()
    monkeys should equal(expectedMonkeysRound6)
    executor.executeRound()
    monkeys should equal(expectedMonkeysRound7)
    executor.executeRound()
    monkeys should equal(expectedMonkeysRound8)
    executor.executeRound()
    monkeys should equal(expectedMonkeysRound9)
    executor.executeRound()
    monkeys should equal(expectedMonkeysRound10)
    executor.executeRound()
    executor.executeRound()
    executor.executeRound()
    executor.executeRound()
    executor.executeRound()
    monkeys should equal(expectedMonkeysRound15)
    executor.executeRound()
    executor.executeRound()
    executor.executeRound()
    executor.executeRound()
    executor.executeRound()
    monkeys should equal(expectedMonkeysRound20)
    executor.getLevel should equal (10605)
  }

  test("Executor still works for 10000 rounds") {
    val monkeys = getMonkeys
    val divisorsProduct = monkeys.map(_.test).product
    val worryLevelManagementFunction: Long => Long = level => level % divisorsProduct
    val executor = MonkeyBusinessExecutor(monkeys, worryLevelManagementFunction)
    (0 until 10000).foreach(_ => executor.executeRound())
    executor.getLevel should equal (2713310158L)
  }
}