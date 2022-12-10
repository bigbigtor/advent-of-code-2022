package service

import domain.Op
import domain.Op.*
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

import scala.collection.mutable

class CpuTest extends AnyFunSuite, BeforeAndAfterEach {

  private val instQueue = mutable.Queue[Op]()

  private var cpu: Cpu = Cpu(instQueue)

  override def beforeEach(): Unit =
    instQueue ++= List(Noop, Addx(3), Addx(-5))
    cpu = Cpu(instQueue)

  test("Execute works as expected for the given instructions") {
    cpu.registerX should equal (1)
    cpu.currentCycle should equal (1)
    cpu.execute()
    cpu.registerX should equal (1)
    cpu.currentCycle should equal (2)
    cpu.execute()
    cpu.registerX should equal(1)
    cpu.currentCycle should equal(3)
    cpu.execute()
    cpu.registerX should equal(4)
    cpu.currentCycle should equal(4)
    cpu.execute()
    cpu.registerX should equal(4)
    cpu.currentCycle should equal(5)
    cpu.execute()
    cpu.registerX should equal(-1)
    cpu.currentCycle should equal(6)
  }

  test("Signal strength works as expected for the given instructions") {
    cpu.getSignalStrength should equal (1)
    cpu.execute()
    cpu.getSignalStrength should equal (2)
    cpu.execute()
    cpu.getSignalStrength should equal(3)
    cpu.execute()
    cpu.getSignalStrength should equal(16)
    cpu.execute()
    cpu.getSignalStrength should equal(20)
    cpu.execute()
    cpu.getSignalStrength should equal(-6)
  }
}
