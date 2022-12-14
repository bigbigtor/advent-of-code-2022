package service

import domain.{Directory, File, Node}
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.{equal, should}

import scala.collection.mutable

class FileSystemTest extends AnyFunSuite, BeforeAndAfterEach {

  private val rootDir = Directory("/", null)
  private val dirA = Directory("a", rootDir)
  private val fileB = File("b.txt", 14848514, rootDir)
  private val fileC = File("c.dat", 8504156, rootDir)
  private val dirD = Directory("d", rootDir)
  private val dirE = Directory("e", dirA)
  private val fileF = File("f", 29116, dirA)
  private val fileG = File("g", 2557, dirA)
  private val fileH = File("h.lst", 62596, dirA)
  private val fileI = File("i", 584, dirE)
  private val fileJ = File("j", 4060174, dirD)
  private val fileDLog = File("d.log", 8033020, dirD)
  private val fileDExt = File("d.ext", 5626152, dirD)
  private val fileK = File("k", 7214296, dirD)

  private val emptyFilter: Int => Boolean = _ => true

  private val atMostFilter: Int => Boolean = size => size <= 100000

  private val fs: FileSystem = FileSystem()

  override def beforeEach(): Unit =
    rootDir.content = mutable.Set(dirA, fileB, fileC, dirD)
    dirA.content = mutable.Set(dirE, fileF, fileG, fileH)
    dirE.content = mutable.Set(fileI)
    dirD.content = mutable.Set(fileJ, fileDLog, fileDExt, fileK)

  test("List all directories returns all directories") {
    fs.listAllDirectories(rootDir) should equal (Set(rootDir, dirA, dirD, dirE))
  }

  test("List with filter returns the expected directories") {
    fs.listAllDirectories(rootDir, emptyFilter) should equal (Set(rootDir, dirA, dirD, dirE))
    fs.listAllDirectories(rootDir, atMostFilter) should equal (Set(dirA, dirE))
  }
}
