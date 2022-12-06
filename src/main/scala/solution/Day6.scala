package solution

import service.DeviceCommunicationSystem


class Day6 {

  private val device: DeviceCommunicationSystem = DeviceCommunicationSystem()

  def part1(input: String): Int = device.lockOnSignal(input)
  
  def part2(input: String): Int = device.lookForMessage(input)
}
