package service

import domain.{PlantationMap, Tree}
class Quadcopter {

  def generateMap(input: String): PlantationMap =
    val numColumns = input.indexOf("\n")
    val numRows = input.length / numColumns
    var plantationMap: PlantationMap = Array.ofDim[Tree](numRows, numColumns)
    input.split("\n")
      .zipWithIndex
      .foreach((line, row) => {
        line.toCharArray
          .zipWithIndex
          .foreach((height, col) => {
            plantationMap(row)(col) = Tree(height.asDigit)
          })
      })
    plantationMap
}
