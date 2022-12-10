package service

class ClockCircuit(cpu: Cpu, crt: Crt):

  def tick(): Unit =
    val spritePositions = cpu.registerX - 1 to cpu.registerX + 1
    if (spritePositions.contains((cpu.currentCycle - 1) % crt.width))
      crt.lightUpPixel(cpu.currentCycle - 1)
    cpu.execute()