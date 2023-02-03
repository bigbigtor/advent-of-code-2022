package service

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.shouldBe
import service.MonkeyInst.{Rotate, Walk}

class MonkeyMapWalkerTest extends AnyFunSuite {

  private val map = Array(
    Array[Char](' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '.', '.', '.', '#'),
    Array[Char](' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '.', '#', '.', '.'),
    Array[Char](' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '#', '.', '.', '.'),
    Array[Char](' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '.', '.', '.', '.'),

    Array[Char]('.', '.', '.', '#', '.', '.', '.', '.', '.', '.', '.', '#'),
    Array[Char]('.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.'),
    Array[Char]('.', '.', '#', '.', '.', '.', '.', '#', '.', '.', '.', '.'),
    Array[Char]('.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.'),

    Array[Char](' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '.', '.', '.', '#', '.', '.', '.', '.'),
    Array[Char](' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '.', '.', '.', '.', '.', '#', '.', '.'),
    Array[Char](' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '.', '#', '.', '.', '.', '.', '.', '.'),
    Array[Char](' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '.', '.', '.', '.', '.', '.', '#', '.'),
  )

  private val inst = List(
    Walk(10),
    Rotate('R'),
    Walk(5),
    Rotate('L'),
    Walk(5),
    Rotate('R'),
    Walk(10),
    Rotate('L'),
    Walk(4),
    Rotate('R'),
    Walk(5),
    Rotate('L'),
    Walk(5)
  )
  
  private val walker = MonkeyMapWalker()
  
  test("Row, col and facing are correct for the given input") {
    walker.walk(map, inst) shouldBe (5, 7, 0)
  }
}
