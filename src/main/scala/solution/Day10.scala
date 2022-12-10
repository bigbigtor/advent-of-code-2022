package solution

import domain.Op
import service.{ClockCircuit, Cpu, CpuInstructionParser, SignalStrengthSampler}

import scala.collection.mutable


class Day10 {

  private val sampler: SignalStrengthSampler = SignalStrengthSampler()

  private val parser: CpuInstructionParser = CpuInstructionParser()

  private val instQueue: mutable.Queue[Op] = mutable.Queue[Op]()

  def part1(input: String): Int =
    instQueue ++= parser.parse(input).toList
    val cpu = Cpu(instQueue)
    val clock = ClockCircuit(cpu)
    sampler.getSumOfStrengths(clock, cpu)
}
