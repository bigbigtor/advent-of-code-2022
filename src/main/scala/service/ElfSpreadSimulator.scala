package service

import domain.ElfPlanter

class ElfSpreadSimulator:

  private val northTest: (Int, Int, Set[(Int, Int)]) => Boolean =
    (x, y, positions) => Set((x - 1, y + 1), (x, y + 1), (x + 1, y + 1)).intersect(positions).isEmpty

  private val southTest: (Int, Int, Set[(Int, Int)]) => Boolean =
    (x, y, positions) => Set((x - 1, y - 1), (x, y - 1), (x + 1, y - 1)).intersect(positions).isEmpty

  private val westTest: (Int, Int, Set[(Int, Int)]) => Boolean =
    (x, y, positions) => Set((x - 1, y + 1), (x - 1, y), (x - 1, y - 1)).intersect(positions).isEmpty

  private val eastTest: (Int, Int, Set[(Int, Int)]) => Boolean =
    (x, y, positions) => Set((x + 1, y + 1), (x + 1, y), (x + 1, y - 1)).intersect(positions).isEmpty

  def getEmptyGroundTiles(elves: Array[ElfPlanter], rounds: Int): Int =
    for (round <- 1 to rounds)
      val positions = elves.map(e => (e.xPos, e.yPos)).toSet
      val proposedMoves = elves
        .indices
        .map(i => (i, getProposedMove(elves(i), positions, round)))
        .toMap
      val uniqueMoves = getUniqueMoves(proposedMoves)
      elves.indices
        .filter(i => uniqueMoves.contains(proposedMoves(i)))
        .foreach(i => elves(i) = ElfPlanter(proposedMoves(i)._1, proposedMoves(i)._2))
    calculateEmptyTiles(elves)


  def parse(input: String): Array[ElfPlanter] =
    input.split("\n")
      .reverse
      .zipWithIndex
      .flatMap((line, y) =>
        line.zipWithIndex
          .filter((c, _) => c == '#')
          .map((_, x) => ElfPlanter(x, y))
      )

  private def getUniqueMoves(proposedMoves: Map[Int, (Int, Int)]): Set[(Int, Int)] =
    proposedMoves
      .values
      .groupBy(identity)
      .view
      .mapValues(_.size)
      .filter(_._2 == 1)
      .keys
      .toSet

  private def calculateEmptyTiles(elves: Array[ElfPlanter]): Int =
    val positions = elves.map(e => (e.xPos, e.yPos)).toSet
    val minX = positions.map(_._1).min
    val maxX = positions.map(_._1).max
    val minY = positions.map(_._2).min
    val maxY = positions.map(_._2).max
    ((maxY - minY + 1) * (maxX - minX + 1)) - elves.length

  private def getProposedMove(elf: ElfPlanter, positions: Set[(Int, Int)], round: Int): (Int, Int) =
    val result = (elf.xPos, elf.yPos)
    val tests = Array(northTest, southTest, westTest, eastTest)
    if (tests.forall(_.apply(elf.xPos, elf.yPos, positions))) return result
    val orderedTests = Iterator.continually(tests).flatten
      .slice((round - 1) % tests.length, (round - 1) % tests.length + tests.length)
    for(test <- orderedTests)
      test.apply(elf.xPos, elf.yPos, positions) match
        case true if test == northTest => return (elf.xPos, elf.yPos + 1)
        case true if test == southTest => return (elf.xPos, elf.yPos - 1)
        case true if test == westTest => return (elf.xPos - 1, elf.yPos)
        case true if test == eastTest => return (elf.xPos + 1, elf.yPos)
        case _ =>
    result