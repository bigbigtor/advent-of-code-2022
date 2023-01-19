package service

import java.nio.{ByteBuffer, ByteOrder}

enum Material:
  case ore, clay, obsidian, geode

object Material:
  def apply(name: String): Material = Material.valueOf(name)

type CostMap = Int

type Blueprint = Array[CostMap]

class BlueprintParser:

  def parse(input: String): Array[Blueprint] =
    input
      .split("\n")
      .map(b => b.substring(b.indexOf(":") + 2))
      .map(parseCosts)

  private def parseCosts(input: String): Blueprint =
    val result = Array.ofDim[CostMap](4)
    input.split("\\. ")
      .map(_.trim)
      .map(_.replace(".", ""))
      .map(parseCost)
      .foreach((mat, costMap) => result(mat.ordinal) = costMap)
    result

  private def parseCost(input: String): (Material, CostMap) =
    input match
      case s"Each $material robot costs $costComponents" =>
        (Material(material), parseCostComponents(costComponents))

  private def parseCostComponents(input: String): CostMap =
    val result = Array.fill[Byte](4)(0)
    input match
      case s"$qty1 $mat1 and $qty2 $mat2" =>
        result(Material(mat1).ordinal) = qty1.toByte
        result(Material(mat2).ordinal) = qty2.toByte
      case s"$qty $material" =>
        result(Material(material).ordinal) = qty.toByte
    result.foldRight(0)((b, sum) => b + (sum << 8))