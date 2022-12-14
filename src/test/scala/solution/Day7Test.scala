package solution


import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

import scala.io.Source

class Day7Test extends AnyFunSuite {

  private val day7 = Day7()
  private val input = Source.fromResource("day7.txt").mkString

  private val asdf = """$ cd /
                       |$ ls
                       |dir a
                       |dir b
                       |$ cd a
                       |$ ls
                       |1 some.txt
                       |$ cd ..
                       |$ cd b
                       |$ ls
                       |dir a
                       |$ cd a
                       |$ ls
                       |1 other.txt""".stripMargin

  private val fdsa = """$ cd /
                       |$ ls
                       |dir a
                       |dir b
                       |$ cd a
                       |$ ls
                       |1 first.txt
                       |1 second.txt
                       |$ cd ..
                       |$ cd b
                       |$ ls
                       |dir a
                       |$ cd a
                       |$ ls
                       |1 third.txt""".stripMargin

  private val sdfa = """$ cd /
                       |$ ls
                       |dir a
                       |dir aa
                       |$ cd a
                       |$ ls
                       |1 a.txt
                       |$ cd ..
                       |$ cd aa
                       |$ ls
                       |1 a.txt""".stripMargin

  private val fsda = """$ cd /
                       |$ ls
                       |dir a
                       |$ cd a
                       |$ ls
                       |dir a
                       |$ cd a
                       |$ ls
                       |123 file""".stripMargin

  private val safd = """$ cd /
                       |$ ls
                       |dir a
                       |dir b
                       |$ cd a
                       |$ ls
                       |dir a
                       |$ cd a
                       |$ ls
                       |99999 file
                       |$ cd ..
                       |$ cd ..
                       |$ cd b
                       |$ ls
                       |dir a
                       |$ cd a
                       |$ ls
                       |99999 file""".stripMargin

  private val dasf = """$ cd /
                       |$ ls
                       |dir parent-1
                       |dir parent-2
                       |$ cd parent-1
                       |$ ls
                       |dir nested-directory-same-name
                       |$ cd nested-directory-same-name
                       |$ ls
                       |10 a.txt
                       |20 b.txt
                       |$ cd ..
                       |$ cd ..
                       |$ cd parent-2
                       |$ ls
                       |dir nested-directory-same-name
                       |$ cd nested-directory-same-name
                       |$ ls
                       |30 c.txt
                       |40 d.txt""".stripMargin

  private val dsfa = """$ cd /
                       |$ ls
                       |dir a
                       |dir b
                       |$ cd a
                       |$ ls
                       |10 d.txt
                       |$ cd ..
                       |$ cd b
                       |$ ls
                       |dir a
                       |30 e.txt
                       |$ cd a
                       |$ ls
                       |20 f.txt""".stripMargin

  private val dafs = """$ cd /
                       |$ ls
                       |dir a
                       |dir b
                       |$ cd a
                       |$ ls
                       |1 a.txt
                       |$ cd ..
                       |$ cd b
                       |$ ls
                       |1 b.txt""".stripMargin

  private val fasd = """$ cd /
                       |$ ls
                       |dir a
                       |$ cd a
                       |$ ls
                       |dir a
                       |2 a.txt
                       |$ cd a
                       |$ ls
                       |99999 a.txt""".stripMargin

  private val dfas = """$ cd /
                       |$ ls
                       |dir allo
                       |14848514 b.txt
                       |8504156 c.dat
                       |dir d
                       |$ cd allo
                       |$ ls
                       |dir edf
                       |29116 f
                       |2557 g
                       |62596 h.lst
                       |$ cd edf
                       |$ ls
                       |dir h
                       |dir k
                       |584 i
                       |$ cd h
                       |$ ls
                       |41 ito
                       |$ cd ..
                       |$ cd k
                       |$ ls
                       |110 fk
                       |$ cd ..
                       |$ cd ..
                       |$ cd ..
                       |$ cd d
                       |$ ls
                       |4060174 j
                       |8033020 d.log
                       |5626152 d.ext
                       |7214296 k""".stripMargin

  private val fsad = """$ cd /
                       |$ ls
                       |dir a
                       |1 a.txt
                       |$ cd a
                       |$ ls
                       |dir a
                       |2 a.txt
                       |$ cd a
                       |$ ls
                       |3 a.txt""".stripMargin

  test("Day 7 part 1 is solved correctly") {
    day7.part1(asdf) should equal (5)
    day7.part1(fdsa) should equal (7)
    day7.part1(sdfa) should equal (4)
    day7.part1(fsda) should equal (369)
    day7.part1(safd) should equal (99999 * 4)
    day7.part1(dasf) should equal (300)
    day7.part1(dsfa) should equal (140)
    day7.part1(dafs) should equal (4)
    day7.part1(fasd) should equal (99999)
    day7.part1(dfas) should equal (95890)
    day7.part1(fsad) should equal (14)
    day7.part1(input) should equal (1723892)
  }
}
