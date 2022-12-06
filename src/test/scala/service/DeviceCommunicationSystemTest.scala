package service

import munit.FunSuite

class DeviceCommunicationSystemTest extends FunSuite {

  private val device = DeviceCommunicationSystem()

  test("First start-of-packet marker is found correctly") {
    assertEquals(device.lockOnSignal("bvwbjplbgvbhsrlpgdmjqwftvncz"), 5)
    assertEquals(device.lockOnSignal("nppdvjthqldpwncqszvftbrmjlhg"), 6)
    assertEquals(device.lockOnSignal("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"), 10)
    assertEquals(device.lockOnSignal("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"), 11)
  }

  test("First start-of-packet marker is not found") {
    assertEquals(device.lockOnSignal("aaabbb"), -1)
  }
}
