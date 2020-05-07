package io.github.mdauthentic.core

import java.nio.file.{Files, Paths, StandardOpenOption}

import CSVConverter._
import StringFormatter.fileHeader

import scala.util.{Failure, Success, Try}

class SRTWriter {
  /**
   * Write converted srt to file
   *
   * @param file: file name (append to file if exist or created if it does not exist already)
   * @param data: text to write to file
   * */
  def writeSRT(file: String, data: String): Unit = {
    try {
      val path = Paths.get(file)
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
}

object SRTWriter {

  private val writer = new SRTWriter

  /**
   * Convert [[SRT]] files to `CSV`
   *
   * @param source: a list of converted [[SRT]] strings
   * */
  def write(source: List[SRT]): List[String] = source.map(e => e.toCSV.dropRight(1) + "\n")

  /**
   * Convert [[SRT]] files to `CSV`
   *
   * @param source:         a list of converted [[SRT]] strings
   * @param outputFileName: output file path
   * @return                `CSV` file
   * */
  def write(source: List[SRT], outputFileName: String): Unit = {
    val writeToFile = Try{ source.foreach(e => writer.writeSRT(outputFileName, e.toCSV.dropRight(1) + "\n")) }
    writeToFile match {
      case Success(_) => println("File converted and written to CSV.")
      case Failure(e) => e.printStackTrace()
    }
  }

  /**
   * Convert [[SRT]] files to `CSV`
   *
   * @param sourceFile:     source file name
   * @param outputFileName: output file path
   * @return                `CSV` file
   * */
  def write(sourceFile: String, outputFileName: String): Unit = {
    val reader = SRTReader.open(sourceFile)
    write(reader, outputFileName)
  }

  /**
   * Convert [[SRT]] files to `CSV`
   *
   * @param sourceFile:     source file name
   * @param outputFileName: output file path
   * @param header:         file header list
   * @return                `CSV` file
   * */
  def write(sourceFile: String, outputFileName: String, header: List[String]): Unit = {
    // read from file
    val reader = SRTReader.open(sourceFile)
    // write file header
    writer.writeSRT(outputFileName, fileHeader(header) + "\n")
    // write all
    write(reader, outputFileName)
  }

}
