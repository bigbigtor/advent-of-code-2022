package solution

import domain.{Item, Rucksack}
import service.{ItemTypeToPriorityConverter, RucksackBadgeFinder, RucksackDuplicateTypePriorityAggregator}


class Day3 {

  private val converter: ItemTypeToPriorityConverter = ItemTypeToPriorityConverter()
  private val finder: RucksackBadgeFinder = RucksackBadgeFinder()
  private val aggregator: RucksackDuplicateTypePriorityAggregator = RucksackDuplicateTypePriorityAggregator()

  def part1(input: String): Int = aggregator.getSumOfPriorities(getRucksacks(input))

  def part2(input: String): Int =
    getRucksacks(input)
      .sliding(3, 3)
      .map(finder.findBadgeItem)
      .map(converter.convert)
      .sum

  private def getRucksacks(input: String): Array[Rucksack] =
    input.split("\\n")
      .map(
        line => {
          val firstCompartmentItems = line.substring(0, line.length / 2).toList
          val secondCompartmentItems = line.substring(line.length / 2).toList
          Rucksack(firstCompartmentItems, secondCompartmentItems)
        }
      )
}
