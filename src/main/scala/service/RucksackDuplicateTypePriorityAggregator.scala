package service

import domain.Item
import domain.Rucksack
import service.ItemTypeToPriorityConverter

class RucksackDuplicateTypePriorityAggregator {

  private val converter: ItemTypeToPriorityConverter = ItemTypeToPriorityConverter()

  def getSumOfPriorities(rucksacks: Array[Rucksack]): Int =
    rucksacks.map(rucksack => {
      val firstCompartmentSet = rucksack.firstCompartmentItems.distinctBy(_.itemType).map(_.itemType).toSet
      val secondCompartmentSet = rucksack.secondCompartmentItems.distinctBy(_.itemType).map(_.itemType).toSet
      val itemsIntersection = firstCompartmentSet.intersect(secondCompartmentSet).head
      Item(itemsIntersection)
    }).map(converter.convert).sum
}
