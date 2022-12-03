package service

import domain.Item
import domain.Rucksack

class RucksackBadgeFinder {

  def findBadgeItem(rucksacks: Array[Rucksack]): Item =
    val badge = rucksacks.map(rucksack => {
      var distinctItems = rucksack.firstCompartmentItems.map(_.itemType).distinct.toSet
      distinctItems = distinctItems ++ rucksack.secondCompartmentItems.map(_.itemType).distinct.toSet
      distinctItems
    }).fold(Set.empty[Char])((acc, elem) => if (acc.nonEmpty) acc.intersect(elem) else acc.union(elem))
      .head
    Item(badge)
}
