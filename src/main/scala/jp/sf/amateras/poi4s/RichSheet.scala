package jp.sf.amateras.poi4s

import org.apache.poi.ss.usermodel._
import scala.collection.mutable.ListBuffer

class RichSheet(sheet: Sheet){

  def name: String = sheet.getSheetName

  def row(rowNum: Int): Seq[RichCell] = {
    val row = sheet.getRow(rowNum)
    if(row == null){
      Nil
    } else {
      val list = new ListBuffer[RichCell]
      for(cellNum <- row.getFirstCellNum to row.getLastCellNum){
        val cell = row.getCell(cellNum)
        if(cell != null){
          list :+ cell
        }
      }
      list.toSeq
    }
  }

  def column(colNum: Int): Seq[RichCell] = {
    val list = new ListBuffer[RichCell]
    for(rowNum <- sheet.getFirstRowNum to sheet.getLastRowNum){
      val row = sheet.getRow(rowNum)
      if(row != null){
        val cell = row.getCell(colNum)
        if(cell != null){
          list :+ cell
        }
      }
    }
    list.toSeq
  }

  def foreach[T](f: RichCell => T): Seq[T] = {
    val list = new ListBuffer[T]
    for(rowNum <- sheet.getFirstRowNum to sheet.getLastRowNum){
      val row = sheet.getRow(rowNum)
      if(row != null){
        for(cellNum <- row.getFirstCellNum to row.getLastCellNum){
          val cell = row.getCell(cellNum)
          if(cell != null){
            list :+ f(cell)
          }
        }
      }
    }
    list.toSeq
  }

  def filter(f: RichCell => Boolean): Seq[RichCell] = foreach { cell =>
    if(f(cell)) Some(cell) else None
  }.flatten

  def find(f: RichCell => Boolean): Option[RichCell] = {
    for(rowNum <- sheet.getFirstRowNum to sheet.getLastRowNum){
      val row = sheet.getRow(rowNum)
      if(row != null){
        for(cellNum <- row.getFirstCellNum to row.getLastCellNum){
          val cell = row.getCell(cellNum)
          if(cell != null){
            if(f(cell)){
              return Some(cell)
            }
          }
        }
      }
    }
    None
  }

}
