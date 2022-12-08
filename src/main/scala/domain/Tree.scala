package domain

import domain.Direction
case class Tree(height: Int):

  private var _visibleFrom: Set[Direction] = Set()
  
  def visibleFrom: Set[Direction] = _visibleFrom
  
  def visibleFrom_=(dirs: Set[Direction]): Unit = _visibleFrom = dirs