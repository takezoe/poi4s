poi4s
=====

Elegant interface to access to Excel for Scala

```scala
import jp.sf.amateras.poi4s._

// workbook is POI's Workbook object
workbook.sheet(0).foreach { sheet =>
  // find header cell
  sheet.find(_.text == "Name").map { header =>
    // scan specified column
    sheet.column(header.colNum).filter(_.rowNum > header.rowNum).foreach { cell =>
      println(cell.text)
    }
  }
}
```