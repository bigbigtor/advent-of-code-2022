package solution

import domain.{Item, Rucksack}
import service.RucksackDuplicateTypePriorityAggregator


class Day3 {

  private val aggregator: RucksackDuplicateTypePriorityAggregator = RucksackDuplicateTypePriorityAggregator()
  def part1(input: String): Int = aggregator.getSumOfPriorities(getRucksacks(input))

  private def getRucksacks(input: String): Array[Rucksack] =
    input.split("\\n")
      .map(
        line => {
          val firstCompartmentItems = line.substring(0, line.length / 2).map(Item(_)).toList
          val secondCompartmentItems = line.substring(line.length / 2).map(Item(_)).toList
          Rucksack(firstCompartmentItems, secondCompartmentItems)
        }
      )
}
