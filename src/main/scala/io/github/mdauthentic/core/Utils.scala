package io.github.mdauthentic.core

trait Utils {

  val setHashDelim = "#"
  val setStarDelim = "*"

  val n = 3

  /**
   * create repeated `*`
   * @param n: length of character to be created
   * */
  def setHashDelim(n: Int = n): String = setHashDelim * n

  /**
   * create repeated `*`
   * @param n: length of character to be created
   * */
  def setStarDelim(n: Int = n): String = setStarDelim * n

  /**
   * Get file extension
   *
   * @param filename: full file path
   * */
  def getFileExtension(filename: String): Option[String] =
    Some(filename).filter(_.contains(".")).map(_.split("\\.").last)

}
