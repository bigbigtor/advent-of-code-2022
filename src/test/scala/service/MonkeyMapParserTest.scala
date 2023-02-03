package service

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.shouldBe
import service.MonkeyInst.*

class MonkeyMapParserTest extends AnyFunSuite {

  private val input = """        ...#
                        |        .#..
                        |        #...
                        |        ....
                        |...#.......#
                        |........#...
                        |..#....#....
                        |..........#.
                        |        ...#....
                        |        .....#..
                        |        .#......
                        |        ......#.
                        |
                        |10R5L5R10L4R5L5""".stripMargin

  private val expectedMap = Array(
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

  private val expectedInst = List(
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

  private val parser = MonkeyMapParser()

  test("Map and instructions are correct for the given input") {
    val (map, inst) = parser.parse(input)
    map shouldBe expectedMap
    inst shouldBe expectedInst
  }
}
