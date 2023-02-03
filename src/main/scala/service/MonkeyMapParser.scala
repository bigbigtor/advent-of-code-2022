package service

import service.MonkeyInst.*

import scala.collection.mutable

type MonkeyMap = Array[Array[Char]]

enum MonkeyInst:
  case Walk(steps: Int) extends MonkeyInst
  case Rotate(dir: Char) extends MonkeyInst

class MonkeyMapParser:

  def parse(input: String): (MonkeyMap, List[MonkeyInst]) =
    val Array(mapInput, instInput) = input.split("\n\n")
    (parseMap(mapInput), parseInst(instInput))

  private def parseMap(input: String): MonkeyMap =
    input.split("\n").map(_.toCharArray)
  private def parseInst(input: String): List[MonkeyInst] =
    val inst = mutable.ListBuffer[MonkeyInst]()
    var currentSteps = 0
    for (c <- input.toCharArray)
      c match
        case d if ('0' to '9').contains(d) => currentSteps = (currentSteps * 10) + d.getNumericValue
        case 'L' | 'R' =>
          inst.addOne(Walk(currentSteps))
          inst.addOne(Rotate(c))
          currentSteps = 0
    if (currentSteps > 0) inst.addOne(Walk(currentSteps))
    inst.toList