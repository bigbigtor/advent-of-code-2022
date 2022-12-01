package domain

class Elf(foodItems: List[FoodItem]) {

  private var _items: List[FoodItem] = foodItems

  def items: List[FoodItem] = _items
}
