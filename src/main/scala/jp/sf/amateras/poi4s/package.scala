package jp.sf.amateras

import org.apache.poi.ss.usermodel._

package object poi4s {

  implicit def RichSheet(sheet: Sheet): RichSheet = new RichSheet(sheet)

  implicit def RichCell(cell: Cell): RichCell = new RichCell(cell)

}
