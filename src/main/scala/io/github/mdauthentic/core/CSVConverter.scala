package io.github.mdauthentic.core

object CSVConverter {

  /**
   * Convert case class to csv
   * */
  implicit class CSVWrapper(val product: Product) extends AnyVal {
    def toCSV: String = product.productIterator.map{
      case value: Product => value.toCSV
      case others => others
    }.mkString(",")
  }

}
