package solution

import service.{BeaconMapper, SensorReadingParser}


class Day15 {

  private val mapper = BeaconMapper()
  
  private val parser = SensorReadingParser()

  def part1(input: String): Int =
    mapper.getNonBeaconPositionsForRow(parser.parse(input), 2000000)

  def part2(input: String): Array[Long] =
    mapper.getTuningFrequencies(parser.parse(input), 0, 4000000)
}