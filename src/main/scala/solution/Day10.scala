package solution

import domain.Op
import service.{ClockCircuit, Cpu, CpuInstructionParser, Crt, SignalStrengthSampler}

import scala.collection.mutable


class Day10 {

  private val sampler: SignalStrengthSampler = SignalStrengthSampler()

  private val parser: CpuInstructionParser = CpuInstructionParser()

  private var instQueue: mutable.Queue[Op] = mutable.Queue[Op]()

  private val crt: Crt = Crt()

  def part1(input: String): Int =
    val cpu = Cpu(getProgram(input))
    val clock = ClockCircuit(cpu, crt)
    sampler.getSumOfStrengths(clock, cpu)

  def part2(input: String): String =
    instQueue = getProgram(input)
    val cpu = Cpu(instQueue)
    val clock = ClockCircuit(cpu, crt)
    while (instQueue.nonEmpty)
      clock.tick()
    crt.draw

  private def getProgram(input: String): mutable.Queue[Op] =
    val queue = mutable.Queue[Op]()
    queue ++= parser.parse(input).toList
    queue
}
