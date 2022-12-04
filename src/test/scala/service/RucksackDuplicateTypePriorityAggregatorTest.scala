package service

import domain.Item
import domain.Rucksack
import munit.FunSuite

class RucksackDuplicateTypePriorityAggregatorTest extends FunSuite {

  private val aggregator: RucksackDuplicateTypePriorityAggregator = RucksackDuplicateTypePriorityAggregator()

  private val rucksacks: Array[Rucksack] = getRucksacks

  test("Sum of priorities for the duplicate items in the given rucksacks is correct") {
    assertEquals(aggregator.getSumOfPriorities(rucksacks), 157)
  }

  private def getRucksacks: Array[Rucksack] =
    """|vJrwpWtwJgWrhcsFMMfFFhFp
      |jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
      |PmmdzqPrVvPwwTWBwg
      |wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
      |ttgJtRGJQctTZtZT
      |CrZsJsPPZsGzwwsLwLmpwMDw
      |""".stripMargin
      .split("\\n")
      .map(
        line => {
          val firstCompartmentItems = line.substring(0, line.length / 2).toList
          val secondCompartmentItems = line.substring(line.length / 2).toList
          Rucksack(firstCompartmentItems, secondCompartmentItems)
        }
      )
}
