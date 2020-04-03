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
  def cleanSubTime(string: String): String = string.replaceAll(",", ".")
}
