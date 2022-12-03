package domain

class Rucksack(itemsForFirstCompartment: List[Item], itemsForSecondCompartment: List[Item]) {

  private val _firstCompartmentItems: List[Item] = itemsForFirstCompartment
  private val _secondCompartmentItems: List[Item] = itemsForSecondCompartment

  def firstCompartmentItems: List[Item] = _firstCompartmentItems
  def secondCompartmentItems: List[Item] = _secondCompartmentItems
}
