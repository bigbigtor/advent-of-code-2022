package service

import domain.Item

class ItemTypeToPriorityConverter {
  
  // Leveraging ascii codes to obtain priority
  def convert(item: Item): Int =
    item match
      case i if 'A' to 'Z' contains i => i.toInt - 38
      case i if 'a' to 'z' contains i => i.toInt - 96
}
