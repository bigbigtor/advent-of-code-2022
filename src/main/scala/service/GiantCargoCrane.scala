package service

import domain.{CargoDeck, Movement}

class GiantCargoCrane:

  def rearrangeCrates(movements: Array[Movement], cargoDeck: CargoDeck): CargoDeck =
    val result = cargoDeck.clone
    movements.foreach(move => {
      1 to move.numCrates foreach { _ => result(move.endStack).push(result(move.initialStack).pop()) }
    })
    result
