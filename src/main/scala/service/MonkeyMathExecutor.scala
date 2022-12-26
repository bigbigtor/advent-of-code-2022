package service

import domain.MonkeyOp
import domain.MonkeyOp.*

import scala.collection.mutable

class MonkeyMathExecutor:

  def compute(equations: Seq[(String, MonkeyOp)], target: String): Long =
    if (equations.exists(_._1 == target))
      computeWithKnownTarget(equations, target)
    else
      computeWithUnknownTarget(equations, target)
      
  def computeWithKnownTarget(equations: Seq[(String, MonkeyOp)], target: String): Long =
    val pendingEquations = mutable.Queue[(String, MonkeyOp)]()
    equations.foreach(pendingEquations.enqueue)
    val solvedVariables = mutable.Map[String, Long]()
    while (pendingEquations.nonEmpty)
      pendingEquations.dequeue() match
        case (variable, Value(value)) => solvedVariables(variable) = value
        case (variable, TwoValueSum(Variable(op1), Variable(op2))) if solvedVariables.contains(op1) && solvedVariables.contains(op2) =>
          solvedVariables(variable) = solvedVariables(op1) + solvedVariables(op2)
        case (variable, TwoValueSub(Variable(op1), Variable(op2))) if solvedVariables.contains(op1) && solvedVariables.contains(op2) =>
          solvedVariables(variable) = solvedVariables(op1) - solvedVariables(op2)
        case (variable, TwoValueMul(Variable(op1), Variable(op2))) if solvedVariables.contains(op1) && solvedVariables.contains(op2) =>
          solvedVariables(variable) = solvedVariables(op1) * solvedVariables(op2)
        case (variable, TwoValueDiv(Variable(op1), Variable(op2))) if solvedVariables.contains(op1) && solvedVariables.contains(op2) =>
          solvedVariables(variable) = solvedVariables(op1) / solvedVariables(op2)
        case o => pendingEquations.enqueue(o)
    solvedVariables(target)
    
  def computeWithUnknownTarget(equations: Seq[(String, MonkeyOp)], target: String): Long =
    val equationMap = equations.toMap
    val root = expand(equationMap("root"), target, equationMap)
    var reduced = reduce(root)
    var nextReduced = reduce(reduced)
    while(nextReduced.toString != reduced.toString)
      reduced = nextReduced
      nextReduced = reduce(nextReduced)
    var solved = false
    var solution = 0L
    while(!solved)
      nextReduced = solve(nextReduced)
      nextReduced match
        case Match(Variable(t), Value(v)) if t == target =>
          solution = v
          solved = true
        case Match(Value(v), Variable(t)) if t == target =>
          solution = v
          solved = true
        case _ =>
    solution

  private def expand(op: MonkeyOp, target: String, equations: Map[String, MonkeyOp]): MonkeyOp =
    op match
      case Value(v) => Value(v)
      case Variable(v) if v == target => Variable(v)
      case Variable(v) => expand(equations(v), target, equations)
      case TwoValueSum(Value(a), Value(b)) => Value(a + b)
      case TwoValueSum(a, b) => TwoValueSum(expand(a, target, equations), expand(b, target, equations))
      case TwoValueSub(Value(a), Value(b)) => Value(a - b)
      case TwoValueSub(a, b) => TwoValueSub(expand(a, target, equations), expand(b, target, equations))
      case TwoValueMul(Value(a), Value(b)) => Value(a * b)
      case TwoValueMul(a, b) => TwoValueMul(expand(a, target, equations), expand(b, target, equations))
      case TwoValueDiv(Value(a), Value(b)) => Value(a / b)
      case TwoValueDiv(a, b) => TwoValueDiv(expand(a, target, equations), expand(b, target, equations))
      case Match(a, b) => Match(expand(a, target, equations), expand(b, target, equations))

  private def reduce(op: MonkeyOp): MonkeyOp =
    op match
      case Value(v) => Value(v)
      case Variable(v) => Variable(v)
      case TwoValueSum(Value(a), Value(b)) => Value(a + b)
      case TwoValueSum(a, b) => TwoValueSum(reduce(a), reduce(b))
      case TwoValueSub(Value(a), Value(b)) => Value(a - b)
      case TwoValueSub(a, b) => TwoValueSub(reduce(a), reduce(b))
      case TwoValueMul(Value(a), Value(b)) => Value(a * b)
      case TwoValueMul(a, b) => TwoValueMul(reduce(a), reduce(b))
      case TwoValueDiv(Value(a), Value(b)) => Value(a / b)
      case TwoValueDiv(a, b) => TwoValueDiv(reduce(a), reduce(b))
      case Match(a, b) => Match(reduce(a), reduce(b))

  private def solve(op: MonkeyOp): MonkeyOp =
    op match
      case Match(TwoValueSum(Value(a), other), Value(b)) => Match(other, Value(b - a))
      case Match(TwoValueSum(other, Value(a)), Value(b)) => Match(other, Value(b - a))
      case Match(TwoValueSub(Value(a), other), Value(b)) => Match(TwoValueMul(other, Value(-1L)), Value(b - a))
      case Match(TwoValueSub(other, Value(a)), Value(b)) => Match(other, Value(b + a))
      case Match(TwoValueMul(Value(a), other), Value(b)) => Match(other, Value(b / a))
      case Match(TwoValueMul(other, Value(a)), Value(b)) => Match(other, Value(b / a))
      case Match(TwoValueDiv(Value(a), other), Value(b)) => Match(Value(a), TwoValueMul(Value(b), other))
      case Match(TwoValueDiv(other, Value(a)), Value(b)) => Match(other, Value(b * a))
