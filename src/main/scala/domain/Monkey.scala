package domain

import scala.collection.mutable

case class Monkey(
              items: mutable.Queue[WorryLevel],
              operation: MonkeyOp,
              test: Long,
              targetIfTrue: Int,
              targetIfFalse: Int,
              var numInspections: Int = 0)