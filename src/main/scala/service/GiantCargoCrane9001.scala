package service

import domain.{CargoDeck, Movement}

class GiantCargoCrane9001 extends GiantCargoCrane:

  def rearrangeCrates(movements: Array[Movement], cargoDeck: CargoDeck): CargoDeck =
    val result = cargoDeck.clone
    movements.foreach(move => {
      val popped = for _ <- 1 to move.numCrates yield result(move.initialStack).pop()
      popped.reverse.foreach(result(move.endStack).push)
    })
    result
