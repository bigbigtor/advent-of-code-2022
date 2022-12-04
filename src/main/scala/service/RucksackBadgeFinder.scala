package service

import domain.Item
import domain.Rucksack

class RucksackBadgeFinder {

  def findBadgeItem(rucksacks: Array[Rucksack]): Item =
    rucksacks.map(rucksack => {
      var distinctItems = rucksack.firstCompartmentItems.toSet
      distinctItems = distinctItems ++ rucksack.secondCompartmentItems.toSet
      distinctItems
    }).fold(Set.empty[Char])((acc, elem) => if (acc.nonEmpty) acc.intersect(elem) else acc.union(elem))
      .head
}
