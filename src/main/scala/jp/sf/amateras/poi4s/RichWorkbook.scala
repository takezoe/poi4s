package jp.sf.amateras.poi4s

import org.apache.poi.ss.usermodel._

class RichWorkbook(workbook: Workbook) {

  def sheets: Seq[Sheet] = (0 to workbook.getNumberOfSheets - 1).map(workbook.getSheetAt)

  def sheet(name: String): Option[Sheet] = Option(workbook.getSheet(name))

}
