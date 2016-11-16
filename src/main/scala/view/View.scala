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
  
  def getCommand(): Menu = {
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
    case 1 => createSudent()
    case 2 => deleteStudent()
    case 3 => showAllStudents()
    case 4 => showAllByName()
    case 5 => editeStudent()
    case _ => Utils.output(incorrectInput)
  }
  
  private def showAllByName(){
    val students = controller.getAllStudents()
    Utils.output("All : %s sudent(s)".format(students.size))
    implicit var personNameComparator = new Ordering[(Int, Student)] {
        def compare(s1: (Int, Student), s2: (Int, Student)): Int = s1._2.name.compareTo(s2._2.name)
    }
    students.sorted.foreach { x => println(x._1 + " - " + x._2) }
  }
  
  private def showAllStudents(){
    val students = controller.getAllStudents()
    Utils.output("All : %s sudent(s)".format(students.size))
    students.foreach { x => println(x._1 + " - " + x._2) }
  }
  
  private def createSudent(){
    val name = Utils.inputString("Enter sudent's name - ")
    val age = Utils.inputInt("Enter sudent's age - ")
    val averageMark = Utils.inputDouble("Enter sudent's average mark - ")
    controller.createStudent(name, age, averageMark)
    Utils.output("Student was added")
  }
  
  private def deleteStudent(){
    val name = Utils.inputString("Enter sudent's name for delete - ")
    controller.deleteStudent(name)
    Utils.output("Student was deleted")
  }
  
  def startMenu() = {
     Utils.output("Enter your act")
     Utils.output("1) Create sudent")
     Utils.output("2) Delete student")
     Utils.output("3) Show all students ordered by ID")
     Utils.output("4) Show all students ordered by name")
     Utils.output("5) Edit student")
  }
  
  private def editeStudent(){
    val student = controller.findStudent()
    checkStudent(student)
  }
  
  private def checkStudent(opt: Option[(Int, Student)]) = { opt match {
      case Some(i: (Int, Student)) => Utils.output("Was found student - " + i._2); controller.editStudent(i)
      case _ => Utils.output("Was not found...")
    }
  }
  
}