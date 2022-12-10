package service

class SignalStrengthSampler:

  private val samplingCycles = Set(20, 60, 100, 140, 180, 220)
  def getSumOfStrengths(clock: ClockCircuit, cpu: Cpu): Int =
    var result = 0
    while (cpu.currentCycle <= samplingCycles.max)
      if (samplingCycles.contains(cpu.currentCycle))
        result += cpu.getSignalStrength
      clock.tick()
    result