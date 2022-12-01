package solution

import domain.Elf
import domain.FoodItem
import service.ElfCalorieAggregator


private class Day1 {

  private val aggregator = ElfCalorieAggregator()

  def part1(input: String) : Int = getElfCalories(input).max

  def part2(input: String) : Int =  getElfCalories(input).sorted.reverse.take(3).sum

  private def getElfCalories(input: String) : Seq[Int] =
    input.split("\\n\\n")
      .map(elfItems =>
        Elf(
          elfItems.split("\\n")
            .map(_.trim.toInt)
            .map(FoodItem(_))
            .toList
        )
      )
      .map(aggregator.getCarriedCalories)
}
