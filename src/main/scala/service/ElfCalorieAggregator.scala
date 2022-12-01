package service

import domain.Elf

class ElfCalorieAggregator {

  def getCarriedCalories(elf: Elf): Int = elf.items.map(f => f.calories).sum
}
