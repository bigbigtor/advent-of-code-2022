package solution

import service.{BeaconMapper, SensorReadingParser}


class Day15 {

  private val mapper = BeaconMapper()
  
  private val parser = SensorReadingParser()

  def part1(input: String): Int =
    mapper.getNonBeaconPositionsForRow(parser.parse(input), 2000000)
}