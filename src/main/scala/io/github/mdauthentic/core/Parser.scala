package io.github.mdauthentic.core

import StringFormatter._


/**
 * Parse `srt` strings format to list
 * */
object Parser {

  /**
   * Parse multiline `srt` string
   *
   * @param string: multiline srt string
   * */
  def parse(string: String): List[SRT] = {

    /**
     * Split multiline strings
     * */
    def multiLineParser(string: String): Array[Array[String]] = {
      // split string by empty line
      val multiline = string.split("\n\n")
      // split each list to individual parts
      multiline.map(e => e.split("\n"))
    }

    /**
     * Convert strings to [[SRT]] type
     * */
    def convertToSRT(string: String): List[SRT] = {
      multiLineParser(string).map(array => {
        val subtitle2List =
          if (array.length > 3) {
            val resultCopy = array.map(identity)
            val result = resultCopy.patch(0, Nil, 2).toList
            result.map(elem => cleanString(elem))
          } else {
            List(cleanString(array(2)))
          }

        val id = array.head.toInt
        val time = cleanSubTime(array(1))
        val (startTime, endTime) = (time.head, time.last)

        SRT(id, startTime, endTime, subtitle2List)
      }).toList
    }
    // return results
    convertToSRT(string)

  }

}
