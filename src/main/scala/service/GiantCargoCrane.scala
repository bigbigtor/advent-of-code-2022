package service

import domain.{CargoDeck, CraneModel, Movement}
import domain.CraneModel.*

trait GiantCargoCrane:

  def rearrangeCrates(movements: Array[Movement], cargoDeck: CargoDeck): CargoDeck

object GiantCargoCrane:

  def apply(model: CraneModel): GiantCargoCrane =
    model match
      case Model9000 => GiantCargoCrane9000()
      case Model9001 => GiantCargoCrane9001()
