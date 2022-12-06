package service

import domain.Signal

class DeviceCommunicationSystem:

  def lockOnSignal(signal: Signal): Int =
    signal.sliding(4)
      .zipWithIndex
      .find((marker, _) => marker.toSet.size == 4)
      .map((_, pos) => pos + 4) //+3 because looking for marker end index, +1 because of 1-based index
      .getOrElse(-1)