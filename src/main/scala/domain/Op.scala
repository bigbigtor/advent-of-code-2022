package domain

enum Op:
  case Noop
  case Addx(value: Int)