package domain

class Item(itType: Char) {

  private val _itemType: Char = itType
  
  def itemType: Char = _itemType
}
