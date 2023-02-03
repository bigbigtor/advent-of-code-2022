package service

import service.Facing.*
import service.MonkeyInst.*

enum Facing(val value: Int):
  case Up extends Facing(3)
  case Down extends Facing(1)
  case Left extends Facing(2)
  case Right extends Facing(0)

object Facing:

  def apply(inst: Char, facing: Facing): Facing =
    facing match
      case Up if inst == 'L' => Left
      case Up if inst == 'R' => Right
      case Down if inst == 'L' => Right
      case Down if inst == 'R' => Left
      case Left if inst == 'L' => Down
      case Left if inst == 'R' => Up
      case Right if inst == 'L' => Up
      case Right if inst == 'R' => Down

class MonkeyMapWalker:

  def walk(map: MonkeyMap, inst: List[MonkeyInst]): (Int, Int, Int) =
    var (row, col) = findStartingPoint(map)
    var facing = Right
    for (i <- inst)
      i match
        case Walk(steps) =>
          val (r, c) = getStoppingPoint(row, col, facing, steps, map)
          row = r
          col = c
        case Rotate(dir) => facing = Facing(dir, facing)
    (row, col, facing.value)

  private def findStartingPoint(map: MonkeyMap): (Int, Int) = (0, map(0).indexOf('.'))

  private def getStoppingPoint(row: Int, col: Int, facing: Facing, steps: Int, map: MonkeyMap): (Int, Int) =
    var remainingSteps = steps
    var (currentRow, currentCol) = (row, col)
    while (remainingSteps > 0)
      facing match
        case Up | Down =>
          getNextRow(currentRow, currentCol, facing, map) match
            case Some(r) => currentRow = r
            case None => remainingSteps = 0
        case Left | Right =>
          getNextCol(currentRow, currentCol, facing, map) match
            case Some(c) => currentCol = c
            case None => remainingSteps = 0
      remainingSteps -= 1
    (currentRow, currentCol)

  private def getNextRow(row: Int, col: Int, facing: Facing, map: MonkeyMap): Option[Int] =
    val transposedCol = map.map(row => if (row.length > col) row(col) else ' ')
    val wrappedPos = facing match
      case Up if row == 0 ||  transposedCol(row - 1) == ' ' =>
        transposedCol.lastIndexOf('.').max(transposedCol.lastIndexOf('#'))
      case Up => row - 1
      case Down if row == (transposedCol.length - 1) ||  transposedCol(row + 1) == ' ' =>
        transposedCol.indexOf('.').min(transposedCol.indexOf('#'))
      case Down => row + 1
    if (map(wrappedPos)(col) != '#') Some(wrappedPos) else None

  private def getNextCol(row: Int, col: Int, facing: Facing, map: MonkeyMap): Option[Int] =
    val wrappedPos = facing match
      case Left if col == 0 || map(row)(col - 1) == ' ' =>
        map(row).lastIndexOf('.').max(map(row).lastIndexOf('#'))
      case Left => col - 1
      case Right if col == (map(row).length - 1) || map(row)(col + 1) == ' ' =>
        map(row).indexOf('.').min(map(row).indexOf('#'))
      case Right => col + 1
    if (map(row)(wrappedPos) != '#') Some(wrappedPos) else None
