package solution

import domain.Directory
import service.{FileSystem, TerminalOutputParser}


class Day7 {

  private val parser: TerminalOutputParser = TerminalOutputParser()
  
  private val fs: FileSystem = FileSystem()

  private val atMostFilter: Int => Boolean = s => s <= 100000
  def part1(input: String): Int =
    val rootDir = parser.parse(input)
    val dirs = fs.listAllDirectories(rootDir, atMostFilter)
    dirs.foldLeft(0)((acc, dir) => acc + dir.size)
}
