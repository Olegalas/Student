package main.scala.controller

import main.scala.model.Student
import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ArrayBuffer
import java.io.BufferedReader
import scala.util.Try
import scala.util.Success
import scala.util.Failure
import java.util.Comparator
import java.util.Optional
import main.scala.utils.Utils

class StudentController {
  
  private var students: Seq[(Int, Student)] = ArrayBuffer()
  private var studentId: Int = 1
  
  def createStudent(name: String, age: Int, averageMark: Double):Unit = {
    students = students :+ (studentId, Student(name, age, averageMark))
    studentId += 1
  }
  
  def deleteStudent(name: String):Unit = {
    students = students.filterNot { x => x._2.name == name } 
  }
  
  // do not throw native reference on students because field doesn't immutable
  def getAllStudents():Seq[(Int, Student)] = new ArrayBuffer[(Int, Student)] ++= (students)

  def findStudent():Option[(Int, Student)] = {
    val name = Utils.inputString("Enter sudent's name for edit - ")
    students.find(e => e._2.name == name)
  }

  def editStudent(i: (Int, Student)) = {
    
  }
  
  
}