package service

import domain.Monkey
import domain.MonkeyOp.*

class MonkeyBusinessExecutor(monkeys: Array[Monkey], reliefFunction: Long => Long):

  def getLevel: Long =
    monkeys.map(_.numInspections.toLong).sorted.reverse.take(2).product

  def executeRound(): Unit =
    monkeys.foreach(executeTurn)

  private def executeTurn(monkey: Monkey): Unit =
    monkey
      .items
      .indices
      .foreach(_ => {
        monkey.numInspections += 1
        val itemToInspect = monkey.items.dequeue
        val worryLevel = monkey.operation match
          case Square => itemToInspect * itemToInspect
          case Mul(v) => itemToInspect * v
          case Sum(v) => itemToInspect + v
        val relievedLevel = reliefFunction.apply(worryLevel)
        val testResult = (relievedLevel % monkey.test) == 0
        val targetMonkey = monkeys(if (testResult) monkey.targetIfTrue else monkey.targetIfFalse)
        targetMonkey.items.enqueue(relievedLevel)
      })