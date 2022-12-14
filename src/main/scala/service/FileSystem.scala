package service

import domain.{Directory, File, Node}

class FileSystem:

  def listAllDirectories(node: Directory, filter: Int => Boolean): Set[Directory] =
    listAllDirectories(node).filter(d => filter.apply(d.size))

  def listAllDirectories(node: Directory): Set[Directory] =
    node.content
      .filter(_.isInstanceOf[Directory])
      .map(_.asInstanceOf[Directory])
      .flatMap(listAllDirectories)
      .toSet + node