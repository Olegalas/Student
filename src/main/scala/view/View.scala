package main.scala.view

import main.scala.model._
import scala.util.Success
import scala.util.Try
import scala.util.Failure
import main.scala.utils.Utils
import main.scala.controller.StudentController

class View() {
  
  private val controller = new StudentController
  private val incorrectInput = "Error\nEnter only numbers (1-3) \n"
  
  def doCommand(): Menu = {
     Try(Utils.inputString() toInt) match {
       case Success(number) => Command(number)
       case Failure(e) => None()
     }
  }
  
  def process(menu: Menu) = menu match {
    case c: Command => processCommand(c)
    case n: None => Utils.output(incorrectInput)
  }
  
  private def processCommand(command: Command) = command.command match {
    case 1 => createStudent()
    case 2 => findAndProcessStudent(deleteStudent, "delete")
    case 3 => showAllStudents()
    case 4 => showAllByName()
    case 5 => findAndProcessStudent(createNewStudentAndReplace, "edit")
    case _ => Utils.output(incorrectInput)
  }
  
  private def showAllByName(){
    val students = controller.allStudents()
    Utils.output("All : %s student(s)".format(students.size))
    implicit var personNameComparator = new Ordering[Student] {
        def compare(s1: Student, s2: Student): Int = s1.name.compareTo(s2.name)
    }
    var i = 1
    students.sorted.foreach { x => println(i + " - " + x); i += 1 }
  }
  
  private def showAllStudents(){
    val students = controller.allStudents()
    Utils.output("All : %s student(s)".format(students.size))
    var i = 1
    students.foreach { x => println(i + " - " + x); i += 1 }
  }
  
  private def createStudent(){
    val name = Utils.inputString("Enter student's name - ")
    val age = Utils.inputInt("Enter student's age - ")
    val averageMark = Utils.inputDouble("Enter student's average mark - ")
    controller.createStudent(name, age, averageMark)
    Utils.output("Student was added")
  }
  
  private def findAndProcessStudent(f : (Student => Unit), processName: String){
    val name = Utils.inputString("Enter student's name for %s - ".format(processName))
    checkStudent(controller.findStudent(name), f)
  }

  private def deleteStudent(student: Student): Unit ={
    Utils.output("Was found student - " + student)
    val choose = Utils.inputYesNo("Are you sure? 1. Yes  2. No - ")
    choose match {
      case No() => return
      case Default() => return
      case _ => Utils.output("Delete...")
    }

    controller.deleteStudent(student.name)
    Utils.output("Student was deleted")
  }
  
  def startMenu() = {
     Utils.output("Enter your act")
     Utils.output("1) Create student")
     Utils.output("2) Delete student")
     Utils.output("3) Show all students ordered by ID")
     Utils.output("4) Show all students ordered by name")
     Utils.output("5) Edit student")
  }
  
  private def checkStudent(opt: Option[Student], f : (Student => Unit)) = { opt match {
      case Some (i: Student) => f(i)
      case _ => Utils.output("Was not found...")
    }
  }

  private def createNewStudentAndReplace(student: Student): Unit ={
    Utils.output("Was found student - " + student)
    val choose = Utils.inputYesNo("Are you sure? 1. Yes  2. No - ")
    choose match {
      case No() => return
      case Default() => return
      case _ => Utils.output("Edit...")
    }
    val name = Utils.inputString("Enter new name - ")
    val age = Utils.inputInt("Enter new age - ")
    val mark = Utils.inputDouble("Enter new average mark - ")

    controller.editStudent(student, name, age, mark)
  }
  
}