package com.sous.title.debug

import com.sous.title.core._

object ReaderTest {

  def main(args: Array[String]): Unit = {

    val reader = new SRTReader().open("resources/data.srt")

    println(reader)

    /*val srt2SCV = SRTWriter.SRT2CSV("resources/data.srt", "resources/data.csv")
    println(srt2SCV)*/

  }

}
