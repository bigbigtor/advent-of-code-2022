package service

import domain.Item
import domain.Rucksack
import munit.FunSuite

class RucksackBadgeFinderTest extends FunSuite {

  private val finder: RucksackBadgeFinder = RucksackBadgeFinder()

  private val input1 =
    """|vJrwpWtwJgWrhcsFMMfFFhFp
       |jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
       |PmmdzqPrVvPwwTWBwg"""

  private val input2 =
    """|wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
       |ttgJtRGJQctTZtZT
       |CrZsJsPPZsGzwwsLwLmpwMDw"""

  test("Badge found for the given rucksacks is correct") {
    assertEquals(finder.findBadgeItem(getRucksacks(input1)).itemType, 'r')
    assertEquals(finder.findBadgeItem(getRucksacks(input2)).itemType, 'Z')
  }

  private def getRucksacks(input: String): Array[Rucksack] =
    input.stripMargin
      .split("\\n")
      .map(
        line => {
          val firstCompartmentItems = line.substring(0, line.length / 2).map(Item(_)).toList
          val secondCompartmentItems = line.substring(line.length / 2).map(Item(_)).toList
          Rucksack(firstCompartmentItems, secondCompartmentItems)
        }
      )
}
