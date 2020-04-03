package com.sous.title.core

import scala.util.parsing.combinator.RegexParsers


/**
 * Parse `.srt` strings format to list
 * */
object Parser extends RegexParsers {

  private val EOI = """\z""".r // end of input
  private val EOL = sys.props("line.separator")
  private val separator = EOI | EOL
  private val word = """\w+""".r

  override val skipWhitespace = false

  val list: Parser[List[String]] = rep(word <~ separator)

  val lists: Parser[List[List[String]]] = repsep(list, rep1(EOL))

  def parse(s: String): List[List[String]] = parseAll(lists, s) match {
    case Success(res, _) => res
    case _ => List[List[String]]()
  }
}
