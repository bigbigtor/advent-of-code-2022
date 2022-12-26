package service

import domain.MonkeyOp
import domain.MonkeyOp.*

class MonkeyMathParser:

  def parse(input: String): Seq[(String, MonkeyOp)] =
    input
      .split("\n")
      .map(
        _ match
          case s"${variable}: ${op1} + ${op2}" => (variable, TwoValueSum(Variable(op1), Variable(op2)))
          case s"${variable}: ${op1} - ${op2}" => (variable, TwoValueSub(Variable(op1), Variable(op2)))
          case s"${variable}: ${op1} * ${op2}" => (variable, TwoValueMul(Variable(op1), Variable(op2)))
          case s"${variable}: ${op1} / ${op2}" => (variable, TwoValueDiv(Variable(op1), Variable(op2)))
          case s"${variable}: ${value}" => (variable, Value(value.toLong))
      )

  def correctParse(input: String): Seq[(String, MonkeyOp)] =
    input
      .split("\n")
      .filter(!_.startsWith("humn:"))
      .map(
        _ match
          case s"root: ${op1} ${_} ${op2}" => ("root", Match(Variable(op1), Variable(op2)))
          case s"${variable}: ${op1} + ${op2}" => (variable, TwoValueSum(Variable(op1), Variable(op2)))
          case s"${variable}: ${op1} - ${op2}" => (variable, TwoValueSub(Variable(op1), Variable(op2)))
          case s"${variable}: ${op1} * ${op2}" => (variable, TwoValueMul(Variable(op1), Variable(op2)))
          case s"${variable}: ${op1} / ${op2}" => (variable, TwoValueDiv(Variable(op1), Variable(op2)))
          case s"${variable}: ${value}" => (variable, Value(value.toLong))
      )