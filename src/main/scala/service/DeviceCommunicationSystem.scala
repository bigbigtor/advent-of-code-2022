package service

import domain.Signal

class DeviceCommunicationSystem:

  private val StartOfPacketMarkerSize = 4
  private val StartOfMessageMarkerSize = 14

  def lockOnSignal(signal: Signal): Int = findMarker(signal, StartOfPacketMarkerSize)

  def lookForMessage(signal: Signal): Int = findMarker(signal, StartOfMessageMarkerSize)

  private def findMarker(signal: Signal, markerSize: Int): Int =
    signal.sliding(markerSize)
      .zipWithIndex
      .find((marker, _) => marker.toSet.size == markerSize)
      .map((_, pos) => pos + markerSize) //+markerSize-1 because looking for marker end index, +1 because of 1-based index
      .getOrElse(-1)
