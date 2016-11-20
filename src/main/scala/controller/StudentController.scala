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

import scala.collection.GenSeq

class StudentController {
  
  private var students: Seq[Student] = ArrayBuffer()

  def createStudent(name: String, age: Int, averageMark: Double):Unit = {
    students = students :+ Student(name, age, averageMark)
  }
  
  def deleteStudent(name: String):Unit = {
    students = students.filterNot ( x => x.name == name )
  }
  
  // do not throw native reference on students because field doesn't immutable
  def allStudents():Seq[Student] = new ArrayBuffer[Student] ++= students

  def findStudent(name: String):Option[Student] = {
    students.find(e => e.name == name)
  }

  def editStudent(i: Student, newName: String, newAge: Int, newAverageMark: Double) = {
    val newStudent = Student(newName, newAge, newAverageMark)
    students = students.filterNot ( x => x.name == i.name) :+ newStudent
  }

  
}