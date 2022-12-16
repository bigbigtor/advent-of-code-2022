package service

import domain.Cave
import domain.CaveElement.*

class SandUnitCounter:

  private var flowingIntoAbyss = false

  private var sandSourceBlocked = false

  def count(cave: Cave): Int =
    val sandSourcePos: (Int, Int) = cave.getSandSourcePosition
    var fallingSandUnitPos: (Int, Int) = sandSourcePos
    while(!flowingIntoAbyss && !sandSourceBlocked)
      getNextPosition(fallingSandUnitPos, cave) match
        case Some(pos) => fallingSandUnitPos = pos
        case None if !flowingIntoAbyss =>
          cave.restSand(fallingSandUnitPos._1, fallingSandUnitPos._2)
          if (fallingSandUnitPos == sandSourcePos) sandSourceBlocked = true
          fallingSandUnitPos = sandSourcePos
        case _ =>
    flowingIntoAbyss = false
    sandSourceBlocked = false
    cave.countSand

  private def getNextPosition(sandUnitPos: (Int, Int), cave: Cave): Option[(Int, Int)] =
    cave.getElement(sandUnitPos._1, sandUnitPos._2 + 1) match
      case Some(Air) => Some((sandUnitPos._1, sandUnitPos._2 + 1))
      case Some(Rock) | Some(Sand) =>
        cave.getElement(sandUnitPos._1 - 1, sandUnitPos._2 + 1) match
          case Some(Air) => Some((sandUnitPos._1 - 1, sandUnitPos._2 + 1))
          case None =>
            flowingIntoAbyss = true
            None
          case Some(Rock) | Some(Sand) =>
            cave.getElement(sandUnitPos._1 + 1, sandUnitPos._2 + 1) match
              case Some(Air) => Some((sandUnitPos._1 + 1, sandUnitPos._2 + 1))
              case Some(Rock) | Some(Sand) => None
              case None =>
                flowingIntoAbyss = true
                None
      case None =>
        flowingIntoAbyss = true
        None