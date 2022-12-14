package service

import domain.{Directory, File, Node}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

import scala.collection.mutable

class TerminalOutputParserTest extends AnyFunSuite {

  private val output =
    """|$ cd /
       |$ ls
       |dir a
       |14848514 b.txt
       |8504156 c.dat
       |dir d
       |$ cd a
       |$ ls
       |dir e
       |29116 f
       |2557 g
       |62596 h.lst
       |$ cd e
       |$ ls
       |584 i
       |$ cd ..
       |$ cd ..
       |$ cd d
       |$ ls
       |4060174 j
       |8033020 d.log
       |5626152 d.ext
       |7214296 k""".stripMargin

  private val parser = TerminalOutputParser()

  test("The parser returns a correct file system for the given terminal output") {
    val parsedTree = parser.parse(output)
    assert(nodesAreEqual(parsedTree, getExpectedTree))
  }

  private def getExpectedTree: Node =
    val rootDir = Directory("/", null)
    val dirA = Directory("a", rootDir)
    val fileB = File("b.txt", 14848514, rootDir)
    val fileC = File("c.dat", 8504156, rootDir)
    val dirD = Directory("d", rootDir)
    rootDir.content = mutable.Set(dirA, fileB, fileC, dirD)
    val dirE = Directory("e", dirA)
    val fileF = File("f", 29116, dirA)
    val fileG = File("g", 2557, dirA)
    val fileH = File("h.lst",  62596, dirA)
    dirA.content = mutable.Set(dirE, fileF, fileG, fileH)
    val fileI = File("i", 584, dirE)
    dirE.content = mutable.Set(fileI)
    val fileJ = File("j", 4060174, dirD)
    val fileDLog = File("d.log", 8033020, dirD)
    val fileDExt = File("d.ext", 5626152, dirD)
    val fileK = File("k", 7214296, dirD)
    dirD.content = mutable.Set(fileJ, fileDLog, fileDExt, fileK)
    rootDir

  private def nodesAreEqual(l: Node, r: Node): Boolean =
    (l, r) match
      case (l: Directory, r: Directory) =>
        l.name == r.name &&
        l.size == r.size &&
        l.content
          .map(n => nodesAreEqual(n, r.content.find(_.name == n.name).get))
          .forall(identity)
      case (l: File, r: File) => l.name == r.name && l.size == r.size
}
