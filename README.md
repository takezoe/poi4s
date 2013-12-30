poi4s
=====

Elegant interface to Excel for Scala

```scala
import jp.sf.amateras.poi4s._

val sheet = workbook.getSheet(0)

// find header cell
sheet.find(_.text == "Name").map { header =>
  // scan specified column
  sheet.column(header.colNum)
    .filter(_.rowNum > header.rowNum).foreach { cell =>
      println(cell.text)
    }
}
```