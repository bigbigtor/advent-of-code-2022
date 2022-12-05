package service

import domain.CargoDeck
import domain.Crate

import scala.collection.mutable

class CrateStackParser:

  def getCargoDeckLayout(input: String): CargoDeck =
    val lines = input.split("\\n").toList
    val cargoDeck = Array.fill((lines.last.length / 4) + 1) { mutable.Stack[Crate]() }
    lines.slice(0, lines.length - 1)
      .reverse
      .foreach(line => {
        (0 until (line.length / 4) + 1)
          .map(pos => (pos, line((pos * 4) + 1)))
          .filter((_, item) => item != ' ')
          .foreach((pos, item) => cargoDeck(pos).push(Crate(item)))
      })
    cargoDeck