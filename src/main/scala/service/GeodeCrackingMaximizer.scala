package service

import scala.collection.mutable
import service.Material.*

case class CrackingState(timeLeft: Int, robots: Int, materials: Int)

class GeodeCrackingMaximizer:

  def count(blueprint: Blueprint, timeLimit: Int): Int =
    val stack = mutable.Stack[CrackingState](CrackingState(timeLimit, 1, 0))
    var bestCount = 0
    while (stack.nonEmpty)
      val s = stack.pop()
      if (s.timeLeft == 0)
        bestCount = bestCount.max(s.materials >> 24)
      else
        getPossibleNextStates(s, blueprint, bestCount).foreach(stack.push)
    bestCount

  def getPossibleNextStates(state: CrackingState, blueprint: Blueprint, bestCount: Int): Array[CrackingState] =
    Array(ore, clay, obsidian, geode)
      .map(_.ordinal)
      .filter(isNeeded(_, state.robots, blueprint))
      .map(pos => (pos, blueprint(pos)))
      .flatMap((pos, costs) => getTimeToBuild(state, costs).map(time => (pos, costs, time)))
      .map((pos, costs, time) => (harvest(state, time), costs, pos))
      .map(build)
      .filter(isBetterThanBest(_, bestCount))

  def isNeeded(robotOffset: Int, robots: Int, blueprint: Blueprint): Boolean =
    if (robotOffset == geode.ordinal) return true
    val mask = getMaskForPosition(robotOffset)
    (robots & mask) < blueprint.map(_ & mask).max
    
  def isBetterThanBest(state: CrackingState, bestCount: Int): Boolean =
    val triangularTimeLeft = state.timeLeft * (state.timeLeft + 1) / 2
    ((state.materials >> 24) + ((state.robots >> 24) * state.timeLeft) + triangularTimeLeft) > bestCount

  def build(state: CrackingState, costs: CostMap, robotOffset: Int): CrackingState =
    CrackingState(
      state.timeLeft,
      state.robots + (1 << (robotOffset * 8)),
      state.materials - costs
    )

  def harvest(state: CrackingState, time: Int): CrackingState =
    CrackingState(state.timeLeft - time, state.robots, state.materials + (state.robots * time))

  def getMaskForPosition(pos: Int): Int = 0xff << (pos * 8)

  def getTimeToBuild(state: CrackingState, costs: Int): Option[Int] =
    val requirements = (0 to 3)
      .map(getMaskForPosition)
      .map(mask => (
        state.robots & mask,
        state.materials & mask,
        costs & mask
      ))
      .filter((_, _, cost) => cost != 0)
    if (requirements.exists((robots, _, _) => robots == 0)) return None
    val time = requirements.map((r, m, c) => -math.floorDiv(m - c, r)).max.max(0) + 1
    if (time > state.timeLeft) return None
    Some(time)