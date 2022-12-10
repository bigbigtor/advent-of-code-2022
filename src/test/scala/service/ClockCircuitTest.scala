package service

import org.mockito.Mockito.{reset, verify, verifyNoInteractions, verifyNoMoreInteractions, when}
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar.mock

class ClockCircuitTest extends AnyFunSuite, BeforeAndAfterEach {

  private val cpu = mock[Cpu]
  private val crt = mock[Crt]
  private val clockCircuit: ClockCircuit = ClockCircuit(cpu, crt)

  override def beforeEach(): Unit =
    reset(cpu)
    reset(crt)

  test("tick works as expected when sprite should be drawn") {
    when(cpu.registerX).thenReturn(5)
    when(cpu.currentCycle).thenReturn(45)
    when(crt.width).thenReturn(40)
    clockCircuit.tick()
    verify(cpu).execute()
    verify(crt).lightUpPixel(44)
    verify(crt).width
  }

  test("tick works as expected when sprite should not be drawn") {
    when(cpu.registerX).thenReturn(5)
    when(cpu.currentCycle).thenReturn(100)
    when(crt.width).thenReturn(40)
    clockCircuit.tick()
    verify(cpu).execute()
    verify(crt).width
    verifyNoMoreInteractions(crt)
  }
}
