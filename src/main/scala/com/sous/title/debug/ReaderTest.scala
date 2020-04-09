package com.sous.title.debug

import com.sous.title.core.SRTReader
import com.sous.title.core.SRTWriter.SRT2CSV

object ReaderTest {

  def main(args: Array[String]): Unit = {

    val reader = new SRTReader().open("resources/data.srt")

    println(reader)

    val srt2SCV = SRT2CSV("resources/data.srt", "resources/data.csv")
    println(srt2SCV)

    val srt =
      """1
        |00:00:33,599 --> 00:00:35,270
        |(NARRA) "Soy Amelia Folch.
        |
        |2
        |00:00:36,199 --> 00:00:39,870
        |Tengo 23 años y sin embargo
        |he salvado la vida del Empecinado.
        |
        |3
        |00:00:45,160 --> 00:00:46,550
        |(Disparo)""".stripMargin

    val inlineReader = new SRTReader().reader(srt)
    println(inlineReader)

  }

}
