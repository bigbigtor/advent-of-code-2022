package domain

import scala.collection.mutable

case class Monkey(
              items: mutable.Queue[WorryLevel],
              operation: MonkeyOp,
              test: MonkeyTest,
              targetIfTrue: Int,
              targetIfFalse: Int,
              var numInspections: Int = 0)