package utils

import model.{Choose, Default, No, Yes}

import scala.io.Source
import scala.util.Try
import scala.util.Success
import scala.util.Failure

object Utils {
  
  private val reader = Source.fromInputStream(System.in).bufferedReader()
  
  def output(s: String){
    println(s)
  }
  
  def inputInt(s: String): Int = {
    print(s)
    Try(reader.readLine() toInt) match {
       case Success(n) => n
       case Failure(e) => inputInt(s)
    }    
  }

  def inputYesNo(s: String): Choose = {
    print(s)
    Try(reader.readLine() toInt) match {
      case Success(n) => getChoose(n)
      case Failure(e) => inputYesNo(s)
    }
  }

  private def getChoose(num: Int): Choose ={
    num match {
      case 1 => Yes()
      case 2 => No()
      case _ => Default()
    }
  }
  
  def inputDouble(s: String): Double = {
    print(s)
    Try(reader.readLine() toDouble) match {
       case Success(n) => n
       case Failure(e) => inputDouble(s)
    }   
  }
  
  def inputString(s: String): String = {
    print(s)
    reader.readLine()
  }
  
  def inputString(): String = {
    reader.readLine()
  }
  
}