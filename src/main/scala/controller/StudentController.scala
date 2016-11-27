package main.scala.controller

import java.io._
import java.util.stream.Collectors

import main.scala.model.Student
import main.scala.serialize.JsonSerializer

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ArrayBuilder}

class StudentController {

  private val fileName = "data.json"
  private var students: Seq[Student] = init()


  private def init() : ArrayBuffer[Student] = {
    val source = scala.io.Source.fromFile(fileName)
    val json = try source.mkString finally source.close()

    val o: Option[Array[Student]] = JsonSerializer.toObject(json, new Array[Student](1).getClass)
    var newStudents = new ArrayBuffer[Student]
    o.getOrElse(new Array[Student](0)).foreach(s => newStudents = newStudents :+ s)
    newStudents
  }

  def createStudent(name: String, age: Int, averageMark: Double):Unit = {
    students = students :+ Student(name, age, averageMark)

    val writer = new FileWriter(fileName)
    writer.write(JsonSerializer.toJason(students.toArray))
    writer.close()

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