package jp.sf.amateras.poi4s

import org.apache.poi.ss.usermodel._

class RichCell(cell: Cell){

  lazy val row: Row = cell.getRow

  lazy val rowNum: Int = cell.getRowIndex

  lazy val colNum: Int = cell.getColumnIndex

  def text: String =  {
    if(cell == null){
      ""
    } else {
      cell.getCellType match {
        case Cell.CELL_TYPE_BLANK   => ""
        case Cell.CELL_TYPE_BOOLEAN => cell.getBooleanCellValue.toString
        case Cell.CELL_TYPE_ERROR   => String.valueOf(cell.getCellFormula())
        case Cell.CELL_TYPE_FORMULA => getCellFormulaContents(cell)
        case Cell.CELL_TYPE_NUMERIC => getNumericContents(cell)
        case Cell.CELL_TYPE_STRING  => String.valueOf(cell.getStringCellValue())
        case _                      => ""
      }
    }
  }

  private def convertDoubleValue(d: Double): String = {
    try {
      new java.math.BigDecimal(d).intValueExact.toString
    } catch {
      case e: ArithmeticException => d.toString
    }
  }

  private def getNumericContents(cell: Cell): String = {
    if (DateUtil.isCellDateFormatted(cell)) {
      // FIXME format string...in JExcel API standard.
      new java.text.SimpleDateFormat("yy/MM/dd").format(cell.getDateCellValue)
    } else {
      convertDoubleValue(cell.getNumericCellValue())
    }
  }

	private def getCellFormulaContents(cell: Cell): String = {
    cell.getCachedFormulaResultType match {
      case Cell.CELL_TYPE_NUMERIC => getNumericContents(cell)
      case Cell.CELL_TYPE_STRING  => cell.getStringCellValue
      case Cell.CELL_TYPE_BOOLEAN => cell.getBooleanCellValue.toString
      case Cell.CELL_TYPE_ERROR   => cell.getCellFormula
      case _                      => ""
    }
	}

}