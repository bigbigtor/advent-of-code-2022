package service

class Crt:

  val width = 40

  val height = 6

  private val pixels: Array[Char] = Array.fill(width * height)('.')

  def lightUpPixel(pos: Int): Unit =
    pixels(pos) = '#'

  def draw: String =
    pixels.sliding(width, width)
      .map(_.mkString)
      .mkString("\n")