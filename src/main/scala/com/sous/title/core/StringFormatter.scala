package com.sous.title.core

object StringFormatter {

  def escapeString(string: String): String = {
    val escapeChar = "\\\\"
    var escapedString = ""
    string.indices.foreach(i => escapedString += escapeChar + string(i))
    escapedString
  }

  /**
   * Removes `,` from string and replace them with single space
   * @param string: string to clean
   * */
  def cleanString(string: String): String = string.replaceAll(",", " ")

  /**
   * Removes `,` from time and replace them with single `.`
   * @param string: string to clean
   * */
  def cleanSubTime(string: String): Array[String] = {
    val removeSep = string.replaceAll(",", ".")
    removeSep.split("-->").map(_.trim)
  }

  /**
   * Convert list containing user-defined headers to `String`
   *
   * @param header: file header list
   * @return comma-separated string
   * */
  def fileHeader(header: List[String]): String = {
    if (header.size < 4) throw new Exception("File header size cannot be less than 4.")
    else header.mkString(",")
  }
}
