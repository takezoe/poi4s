package jp.sf.amateras

import org.apache.poi.ss.usermodel._
import scala.language.implicitConversions

package object poi4s {

  implicit def RichWorkbook(workbook: Workbook): RichWorkbook = new RichWorkbook(workbook)

  implicit def RichSheet(sheet: Sheet): RichSheet = new RichSheet(sheet)

  implicit def RichCell(cell: Cell): RichCell = new RichCell(cell)

}
