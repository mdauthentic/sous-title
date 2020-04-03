package com.sous.title.core

import java.nio.file.{Files, Paths, StandardOpenOption}

import com.sous.title.core.CSVConverter._

object SRTWriter {

  /**
   * Write converted srt to file
   *
   * @param file: file name (append to file if exist or created if it does not exist already)
   * @param data: text to write to file
   * */
  def writeSRT(file: String, data: String): Unit = {
    val path = Paths.get(file)
    try {
      if (Files.notExists(path)) {
        Files.createDirectories(path.getParent)
        Files.createFile(path)
        Files.write(path, data.getBytes)
      } else {
        Files.write(path, data.getBytes("utf-8"),
          StandardOpenOption.CREATE, StandardOpenOption.APPEND)
      }
    } catch {
      case e: Exception => e.printStackTrace();
    }

  } // End writeSRT

  /**
   * Convert [[SRT]] files to `CSV`
   *
   * @param source: a list of converted [[SRT]] strings
   * */
  def SRT2CSV(source: List[SRT]): List[String] = source.map(e => e.toCSV + "\n")

  /**
   * Convert [[SRT]] files to `CSV`
   *
   * @param sourceFile:     source file name
   * @param outputFileName: output file path
   * @return                `CSV` file
   * */
  def SRT2CSV(sourceFile: String, outputFileName: String): Unit = {
    new SRTReader().open(sourceFile).foreach(e => writeSRT(outputFileName, e.toCSV.dropRight(1) + "\n"))
    println("File converted and written to CSV.")
  }

}
