poi4s
========
Elegant interface to Excel for Scala

Setup
--------

```scala
resolvers += "amateras-repo" at "http://amateras.sourceforge.jp/mvn/"

libraryDependencies += "jp.sf.amateras" %% "poi4s" % "0.0.1"
```

Example
--------

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

Release Notes
--------
### 0.0.1 - 04 Mar 2014

* Initial public release
