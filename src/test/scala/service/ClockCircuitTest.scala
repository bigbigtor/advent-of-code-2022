package service

import org.easymock.EasyMock.expectLastCall
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.easymock.EasyMockSugar.mock

class ClockCircuitTest extends AnyFunSuite {

  private val cpu = mock[Cpu]
  private val clockCircuit: ClockCircuit = ClockCircuit(cpu)
  test("tick works as expected") {
    clockCircuit.tick()
    cpu.execute()
    expectLastCall().times(1)
  }
}
