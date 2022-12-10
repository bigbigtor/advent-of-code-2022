package service

import domain.Op
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

import scala.collection.mutable

class SignalStrengthSamplerTest extends AnyFunSuite, BeforeAndAfterEach {

  private val input: String = """|addx 15
                                 |addx -11
                                 |addx 6
                                 |addx -3
                                 |addx 5
                                 |addx -1
                                 |addx -8
                                 |addx 13
                                 |addx 4
                                 |noop
                                 |addx -1
                                 |addx 5
                                 |addx -1
                                 |addx 5
                                 |addx -1
                                 |addx 5
                                 |addx -1
                                 |addx 5
                                 |addx -1
                                 |addx -35
                                 |addx 1
                                 |addx 24
                                 |addx -19
                                 |addx 1
                                 |addx 16
                                 |addx -11
                                 |noop
                                 |noop
                                 |addx 21
                                 |addx -15
                                 |noop
                                 |noop
                                 |addx -3
                                 |addx 9
                                 |addx 1
                                 |addx -3
                                 |addx 8
                                 |addx 1
                                 |addx 5
                                 |noop
                                 |noop
                                 |noop
                                 |noop
                                 |noop
                                 |addx -36
                                 |noop
                                 |addx 1
                                 |addx 7
                                 |noop
                                 |noop
                                 |noop
                                 |addx 2
                                 |addx 6
                                 |noop
                                 |noop
                                 |noop
                                 |noop
                                 |noop
                                 |addx 1
                                 |noop
                                 |noop
                                 |addx 7
                                 |addx 1
                                 |noop
                                 |addx -13
                                 |addx 13
                                 |addx 7
                                 |noop
                                 |addx 1
                                 |addx -33
                                 |noop
                                 |noop
                                 |noop
                                 |addx 2
                                 |noop
                                 |noop
                                 |noop
                                 |addx 8
                                 |noop
                                 |addx -1
                                 |addx 2
                                 |addx 1
                                 |noop
                                 |addx 17
                                 |addx -9
                                 |addx 1
                                 |addx 1
                                 |addx -3
                                 |addx 11
                                 |noop
                                 |noop
                                 |addx 1
                                 |noop
                                 |addx 1
                                 |noop
                                 |noop
                                 |addx -13
                                 |addx -19
                                 |addx 1
                                 |addx 3
                                 |addx 26
                                 |addx -30
                                 |addx 12
                                 |addx -1
                                 |addx 3
                                 |addx 1
                                 |noop
                                 |noop
                                 |noop
                                 |addx -9
                                 |addx 18
                                 |addx 1
                                 |addx 2
                                 |noop
                                 |noop
                                 |addx 9
                                 |noop
                                 |noop
                                 |noop
                                 |addx -1
                                 |addx 2
                                 |addx -37
                                 |addx 1
                                 |addx 3
                                 |noop
                                 |addx 15
                                 |addx -21
                                 |addx 22
                                 |addx -6
                                 |addx 1
                                 |noop
                                 |addx 2
                                 |addx 1
                                 |noop
                                 |addx -10
                                 |noop
                                 |noop
                                 |addx 20
                                 |addx 1
                                 |addx 2
                                 |addx 2
                                 |addx -6
                                 |addx -11
                                 |noop
                                 |noop
                                 |noop""".stripMargin

  private val parser: CpuInstructionParser = CpuInstructionParser()

  private val crt: Crt = Crt()

  private val inst: mutable.Queue[Op] = mutable.Queue[Op]()

  private var cpu: Cpu = Cpu(inst)

  private var clock: ClockCircuit = ClockCircuit(cpu, crt)

  private val sampler: SignalStrengthSampler = SignalStrengthSampler()

  override def beforeEach(): Unit =
    inst ++= parser.parse(input).toList
    cpu = Cpu(inst)
    clock = ClockCircuit(cpu, crt)

  test("The sum of signal strengths is correct for the given input") {
    sampler.getSumOfStrengths(clock, cpu) should equal (13140)
  }
}
