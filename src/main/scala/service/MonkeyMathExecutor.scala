package service

import domain.MonkeyOp
import domain.MonkeyOp.*

import scala.collection.mutable

class MonkeyMathExecutor:

  def compute(equations: Seq[(String, MonkeyOp)]): Long =
    val solvedVariables = mutable.Map[String, Long]()
    val pendingEquations = mutable.Queue[(String, MonkeyOp)]()
    equations.foreach(pendingEquations.enqueue)
    while(pendingEquations.nonEmpty)
      pendingEquations.dequeue() match
        case (variable, Value(value)) => solvedVariables(variable) = value
        case (variable, TwoValueSum(op1, op2)) if solvedVariables.contains(op1) && solvedVariables.contains(op2) =>
          solvedVariables(variable) = solvedVariables(op1) + solvedVariables(op2)
        case (variable, TwoValueSub(op1, op2)) if solvedVariables.contains(op1) && solvedVariables.contains(op2) =>
          solvedVariables(variable) = solvedVariables(op1) - solvedVariables(op2)
        case (variable, TwoValueMul(op1, op2)) if solvedVariables.contains(op1) && solvedVariables.contains(op2) =>
          solvedVariables(variable) = solvedVariables(op1) * solvedVariables(op2)
        case (variable, TwoValueDiv(op1, op2)) if solvedVariables.contains(op1) && solvedVariables.contains(op2) =>
          solvedVariables(variable) = solvedVariables(op1) / solvedVariables(op2)
        case o => pendingEquations.enqueue(o)
    solvedVariables("root")