package service

import domain.Op
import domain.Op.*

class CpuInstructionParser:

  def parse(input: String): Array[Op] =
    input.split("\n")
      .map(line => {
        line match
          case "noop" => Noop
          case s"addx ${value}" => Addx(value.toInt)
      })
