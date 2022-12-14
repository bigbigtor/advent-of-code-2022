package domain

import scala.collection.mutable

trait Node(val name: String, val parent: Directory):

  def size: Int

class Directory(name: String, parent: Directory) extends Node(name, parent):

  var content: mutable.Set[Node] = mutable.LinkedHashSet[Node]()

  override def size: Int = content.foldLeft(0)((acc, node) => acc + node.size)
  
class File(name: String, fileSize: Int, parent: Directory) extends Node(name, parent):

  override def size: Int = fileSize