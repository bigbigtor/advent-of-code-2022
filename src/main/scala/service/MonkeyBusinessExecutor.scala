package service

import domain.Monkey
import domain.MonkeyOp.*
import domain.MonkeyTest.DivBy

class MonkeyBusinessExecutor(monkeys: Array[Monkey]):

  def getLevel: Int =
    monkeys.map(_.numInspections).sorted.reverse.take(2).product
    
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
        val relievedLevel = Math.floorDiv(worryLevel, 3)
        val testResult = monkey.test match
          case DivBy(v) => (relievedLevel % v) == 0
        val targetMonkey = monkeys(if (testResult) monkey.targetIfTrue else monkey.targetIfFalse)
        targetMonkey.items.enqueue(relievedLevel)
      })