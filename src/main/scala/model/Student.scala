package main.scala.model

case class Student(name: String, age: Int, averageMark: Double){
  override def toString() = "Student {name : %s, age : %s , averageMark : %s}".format(name, age, averageMark)
}
