package com.sous.title.core

import java.nio.file.{Files, Paths}

import com.sous.title.core.StringFormatter._

import scala.util.Using
import scala.jdk.CollectionConverters._


class SRTReader(val charLength: Int = 3) extends Utils {

  private def lineReader(file: String): List[String] = {
    try {
      val bufferedReader = Files.newBufferedReader(Paths.get(file))
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


  private def lineReader2(file: String): List[String] = {
    var linePrefix = ""
    try {
      val lines = Files.readAllLines(Paths.get(file)).asScala.toList
      lines.foreach(nextLine => {
        if (nextLine.trim.isEmpty) {
          linePrefix += "\n"
        } else {
          linePrefix += nextLine + setStarDelim(charLength)
        }
      })

      val result = linePrefix.split("\n{2,}")
        .map(_.split("\n").toList).toList
      result.flatten
    } catch {
      case e: Exception => throw new Exception(e.getMessage)
    }
  } // End lineReader2

  private def convert2Type(xs: List[String]): List[SRT] = {

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

      SRT(id, time, sub2List)
    })
  } // End SrtType

  def open(file: String): List[SRT] = convert2Type(lineReader(file))

  def reader(string: String): List[List[String]] = Parser.parse(string)

}
