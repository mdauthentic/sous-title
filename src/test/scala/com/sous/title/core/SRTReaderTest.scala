package com.sous.title.core

import org.scalatest.{BeforeAndAfter, FunSpec}
import org.scalatest.Matchers._
import java.nio.file.{Files, Paths}
import org.scalatest.PrivateMethodTester._

class SRTReaderTest extends FunSpec with BeforeAndAfter {

  var reader: SRTReader = _
  val filePath = "resources/data.srt"
  val characterLen = 4

  before {
    reader = new SRTReader
  }

  describe("file reader") {

    it("should accept valid file path") {
      val path = Paths.get("resources/data.srt")
      val file = Files.exists(path)
      file should be (true)
    }

    it("should return non-empty list") {
      val line = PrivateMethod[List[String]](Symbol("lineReader"))
      val r = reader invokePrivate line(filePath)
      r.length should be > 0
    }

    it("should convert list of strings to typed list") {
      val converter = PrivateMethod[List[SRT]](Symbol("convert2Type"))
      val list = List("1***00:00:33.599 --> 00:00:35.270***Soy Amelia Folch.")
      val expected = List(SRT(1, "00:00:33.599", "00:00:35.270", List("Soy Amelia Folch.")))
      val typedSubtitle = reader invokePrivate converter(list)
      typedSubtitle should equal(expected)
    }

  }

  describe("hash delimiter") {
    it("should generate string with specified length") {
      val expected = "####"
      reader.setHashDelim(characterLen) should be (expected)
    }
  }

  describe("star delimiter") {
    it("should generate string with specified length") {
      val expected = "****"
      reader.setStarDelim(characterLen) should be (expected)
    }
  }

}
