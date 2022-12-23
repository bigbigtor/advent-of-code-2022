package service

import domain.MonkeyOp
import domain.MonkeyOp.*

class MonkeyMathParser:

  def parse(input: String): Seq[(String, MonkeyOp)] =
    input
      .split("\n")
      .map(
        _ match
          case s"${variable}: ${op1} + ${op2}" => (variable, TwoValueSum(op1, op2))
          case s"${variable}: ${op1} - ${op2}" => (variable, TwoValueSub(op1, op2))
          case s"${variable}: ${op1} * ${op2}" => (variable, TwoValueMul(op1, op2))
          case s"${variable}: ${op1} / ${op2}" => (variable, TwoValueDiv(op1, op2))
          case s"${variable}: ${value}" => (variable, Value(value.toLong))
      )
