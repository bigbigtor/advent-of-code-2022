package domain

enum MonkeyOp:
  case Square
  case Mul(value: Long)
  case Sum(value: Long)
  case TwoValueSum(a: String, b: String)
  case TwoValueSub(a: String, b: String)
  case TwoValueMul(a: String, b: String)
  case TwoValueDiv(a: String, b: String)
  case Value(value: Long)
