package me.limacon.crif.util

import scala.io.Source
// TODO adopt spark dataframe
object CSVParser {
  def readCSV(filePath: String) = {
    val source = Source.fromFile(filePath)
    val lines = source.getLines().toList
    source.close()
    // Extract headers (assuming the first row is headers)
    val headers = lines.head.split(",").map(_.trim)
    val rows = lines.tail.map(_.split(",", -1).map(_.trim).toList)
    // Validate that each row has the same number of columns as headers
    require(rows.forall(_.length == headers.length),
      "Each row must have the same number of columns as headers")
    rows.map(headers.zip(_).toMap)
  }
}
