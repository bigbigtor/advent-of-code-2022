package service

import domain.Item
import domain.Rucksack
import service.ItemTypeToPriorityConverter

class RucksackDuplicateTypePriorityAggregator {

  private val converter: ItemTypeToPriorityConverter = ItemTypeToPriorityConverter()

  def getSumOfPriorities(rucksacks: Array[Rucksack]): Int =
    rucksacks.map(rucksack => {
      val firstCompartmentSet = rucksack.firstCompartmentItems.toSet
      val secondCompartmentSet = rucksack.secondCompartmentItems.toSet
      firstCompartmentSet.intersect(secondCompartmentSet).head
    }).map(converter.convert).sum
}
