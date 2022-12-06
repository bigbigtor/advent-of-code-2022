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

  test("First start-of-message marker is found correctly") {
    assertEquals(device.lookForMessage("mjqjpqmgbljsphdztnvjfqwrcgsmlb"), 19)
    assertEquals(device.lookForMessage("bvwbjplbgvbhsrlpgdmjqwftvncz"), 23)
    assertEquals(device.lookForMessage("nppdvjthqldpwncqszvftbrmjlhg"), 23)
    assertEquals(device.lookForMessage("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"), 29)
    assertEquals(device.lookForMessage("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"), 26)
  }

  test("First start-of-message marker is not found") {
    assertEquals(device.lookForMessage("aaabbbcccdddeeefffggghhhiii"), -1)
  }
}
