package service

import domain.{Node, Directory, File}

import scala.collection.mutable


class TerminalOutputParser:

  def parse(output: String): Directory =
    var currentNode: Directory = null
    output.split("\n")
      .foreach(line => {
        line match
          case s"$$ cd .." => currentNode = currentNode.parent
          case s"$$ cd ${name}" if currentNode == null => currentNode = Directory(name, null)
          case s"$$ cd ${name}" => currentNode = currentNode.content.find(_.name == name).get.asInstanceOf[Directory]
          case s"$$ ls" =>
          case s"dir ${name}" if !currentNode.content.exists(_.name == name) =>
            currentNode.content.add(Directory(name, currentNode))
          case s"${size} ${name}" if !currentNode.content.exists(_.name == name) =>
            currentNode.content.add(File(name, size.toInt, currentNode))
      })
    while (currentNode.parent != null) currentNode = currentNode.parent
    currentNode