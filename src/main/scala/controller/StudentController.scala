package controller

import model.Student
import serialize.StudentSerializer

import scala.collection.mutable.ArrayBuffer

class StudentController {

  private var students: Seq[Student] = init()


  private def init() : ArrayBuffer[Student] = {
    StudentSerializer.downloadStudents()
  }

  def createStudent(name: String, age: Int, averageMark: Double){
    // scala cast example
    students match { case a: ArrayBuffer[Student] => a += Student(name, age, averageMark) }
    StudentSerializer.saveStudents(students)
  }
  
  def deleteStudent(name: String):Unit = {
    // another example of work with scala collections
    students = students.filterNot ( x => x.name == name )
    StudentSerializer.saveStudents(students)
  }
  
  // do not throw native reference on students because field doesn't immutable
  def allStudents():Seq[Student] = new ArrayBuffer[Student] ++= students

  def findStudent(name: String):Option[Student] = {
    students.find(e => e.name == name)
  }

  def editStudent(i: Student, newName: String, newAge: Int, newAverageMark: Double) = {
    val newStudent = Student(newName, newAge, newAverageMark)
    students = students.filterNot ( x => x.name == i.name) :+ newStudent
    StudentSerializer.saveStudents(students)
  }

  
}