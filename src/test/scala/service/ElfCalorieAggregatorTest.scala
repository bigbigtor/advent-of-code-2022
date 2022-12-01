package service

import domain.Elf
import domain.FoodItem
import service.ElfCalorieAggregator

class ElfCalorieAggregatorTest extends munit.FunSuite {

    private val aggregator = ElfCalorieAggregator()

    private val foodItem1 = FoodItem(1)
    private val foodItem2 = FoodItem(2)
    private val foodItem3 = FoodItem(3)

    private val elf1 = Elf(List(foodItem1, foodItem2, foodItem3))
    private val elf2 = Elf(List(foodItem2, foodItem3))
    private val elf3 = Elf(List())

    test("Carried calories for each elf are aggregated correctly") {
        assertEquals(6, aggregator.getCarriedCalories(elf1))
        assertEquals(5, aggregator.getCarriedCalories(elf2))
        assertEquals(0, aggregator.getCarriedCalories(elf3))
    }

}