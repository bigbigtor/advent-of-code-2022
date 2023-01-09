package service

import domain.Direction
import domain.Direction.*
import service.Tile.*

import scala.annotation.tailrec
import scala.collection.mutable

enum Tile:
  case Ground
  case Wall
  case MovingBlizzard(dir: Direction)
  case Blizzard

class BlizzardValleyParser:

  def findStart(input: String): (Int, Int) = (input.indexOf("."), 0)
  
  def findEnd(input: String): (Int, Int) =
    val lines = input.split("\n")
    val x = lines.last.indexOf(".")
    (x, lines.length - 1)
    
  def parse(input: String): Array[Array[Array[Tile]]] =
    val lines = input.split("\n")
    val (w, h) = (lines(0).length, lines.length)
    var grid = lines.map(
      _.map(c =>
        mutable.Set[Tile](mapToTile(c))
      ).toArray
    )
    val (xLen, yLen, zLen) = (w, h, lcm(w - 2, h - 2))
    val result = Array.ofDim[Tile](zLen, yLen, xLen)
    for (z <- 0 until zLen)
      val nextGrid = Array.fill[mutable.Set[Tile]](yLen, xLen)(mutable.Set())
      for (y <- 0 until yLen; x <- 0 until xLen)
        result(z)(y)(x) = getResultantTile(grid(y)(x))
        grid(y)(x).foreach(t =>
          t match
            case Ground | Wall => nextGrid(y)(x).add(t)
            case MovingBlizzard(dir) =>
              val pos = getWrappedPosition((x, y), (xLen, yLen), dir)
              nextGrid(pos._2)(pos._1).add(MovingBlizzard(dir))
        )
      grid = nextGrid
    result

  private def lcm(a: Int, b: Int): Int = (a / gcd(a, b)) * b

  @tailrec
  private def gcd(a: Int, b: Int): Int =
    if (a == 0)
      b
    else
      gcd(b % a, a)

  private def mapToTile(c: Char): Tile =
    c match
      case '>' => MovingBlizzard(Right)
      case '<' => MovingBlizzard(Left)
      case 'v' => MovingBlizzard(Bottom)
      case '^' => MovingBlizzard(Top)
      case '#' => Wall
      case '.' => Ground

  private def getResultantTile(tiles: mutable.Set[Tile]): Tile =
    val blizzards = Set(
      MovingBlizzard(Top),
      MovingBlizzard(Bottom),
      MovingBlizzard(Left),
      MovingBlizzard(Right)
    )
    if (tiles.exists(blizzards.contains))
      Blizzard
    else
      tiles.headOption.getOrElse(Ground)

  private def getWrappedPosition(currentPos: (Int, Int), maxDimensions: (Int, Int), dir: Direction): (Int, Int) =
    dir match
      case Top => (currentPos._1, wrap(currentPos._2 - 1, maxDimensions._2))
      case Bottom => (currentPos._1, wrap(currentPos._2 + 1, maxDimensions._2))
      case Left => (wrap(currentPos._1 - 1, maxDimensions._1), currentPos._2)
      case Right => (wrap(currentPos._1 + 1, maxDimensions._1), currentPos._2)

  private def wrap(pos: Int, maxDim: Int): Int =
    if (pos == 0)
      maxDim - 2
    else if (pos == (maxDim - 1))
      1
    else
      pos