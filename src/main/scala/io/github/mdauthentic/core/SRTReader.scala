package io.github.mdauthentic.core

import java.nio.file.{Files, Paths}

import StringFormatter._

import scala.util.Using

class SRTReader(val charLength: Int = 3) extends Utils {

  /**
   * Read [[SRT]] files from file
   *
   * @param filePath: file path
   * */
  def lineReader(filePath: String): List[String] = {
    try {
      val bufferedReader = Files.newBufferedReader(Paths.get(filePath))
      Using.resource(bufferedReader) { reader =>
        val lines = Iterator.unfold(())(_ => Option(reader.readLine()).map(_ -> ()))
        var linePrefix = ""
        while (lines.hasNext) {
          val nextLine = lines.next
          if (nextLine.trim.isEmpty) {
            linePrefix += setHashDelim(charLength)
          } else {
            linePrefix += nextLine + setStarDelim(charLength)
          }
        }
        linePrefix.split(setHashDelim).toList
      }
    } catch {
      case e: Exception => throw new Exception(e.getMessage)
    }
  } // End lineReader

  /**
   * Convert list of `.srt` strings to [[SRT]] type
   * */
  def convert2Type(xs: List[String]): List[SRT] = {

    xs.filterNot(_.isEmpty).map(string => {

      val resultArray = string.split("\\*\\*\\*")

      val sub2List =
        if (resultArray.size > 3) {
          val resultCopy = resultArray.map(identity)
          val result = resultCopy.patch(0, Nil, 2).toList
          result.map(e => cleanString(e))
        } else {
          List(cleanString(resultArray(2)))
        }

      val id = resultArray.head.toInt
      val time = cleanSubTime(resultArray(1))
      val (startTime, endTime) = (time.head, time.last)

      SRT(id, startTime, endTime, sub2List)
    })
  } // End SrtType

  /*def open(file: String): List[SRT] = convert2Type(lineReader(file))

  def reader(string: String): List[SRT] = Parser.parse(string)*/

}

object SRTReader {

  private val read = new SRTReader()

  /**
   * Read [[SRT]] from file
   *
   * @param file: file path
   * */
  def open(file: String): List[SRT] = read.convert2Type(read.lineReader(file))

  /**
   * Read inline `.srt` string
   *
   * @param string: string in `.srt` format
   * */
  def readInLine(string: String): List[SRT] = Parser.parse(string)
}
