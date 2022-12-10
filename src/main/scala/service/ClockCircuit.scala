package service

class ClockCircuit(cpu: Cpu):

  def tick(): Unit = cpu.execute()