package domain

import domain.MonkeyOp.TwoValueSum

enum MonkeyOp:
  case Square
  case Mul(value: Long)
  case Sum(value: Long)
  case TwoValueSum(a: MonkeyOp, b: MonkeyOp)
  case TwoValueSub(a: MonkeyOp, b: MonkeyOp)
  case TwoValueMul(a: MonkeyOp, b: MonkeyOp)
  case TwoValueDiv(a: MonkeyOp, b: MonkeyOp)
  case Value(value: Long)
  case Variable(name: String)
  case Match(a: MonkeyOp, b: MonkeyOp)

  override def toString: String =
    this match
      case Match(a, b) => a.toString + " = " + b.toString
      case TwoValueSum(a, b) => "( " + a.toString + " + " + b.toString + " )"
      case TwoValueSub(a, b) => "( " + a.toString + " - " + b.toString + " )"
      case TwoValueMul(a, b) => "( " + a.toString + " * " + b.toString + " )"
      case TwoValueDiv(a, b) => "( " + a.toString + " / " + b.toString + " )"
      case Variable(v) => v
      case Value(v) => v.toString
