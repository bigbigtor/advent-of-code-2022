package service

import domain.Op
import domain.Op.*

import scala.collection.mutable

class Cpu(inst: mutable.Queue[Op], var registerX: Int = 1, var currentCycle: Int = 1):

  private var currentInstruction: Op = Noop

  private var currentInstructionRemainingCycles: Int = 0

  def execute(): Unit =
    if (currentInstructionRemainingCycles == 0)
      currentInstruction = inst.dequeue
      currentInstructionRemainingCycles = currentInstruction match
        case Noop => 1
        case Addx(_) => 2

    currentInstructionRemainingCycles -= 1
    currentCycle += 1

    if (currentInstructionRemainingCycles == 0)
      currentInstruction match
        case Noop =>
        case Addx(v) => registerX += v

  def getSignalStrength: Int = currentCycle * registerX