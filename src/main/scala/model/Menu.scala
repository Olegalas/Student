package main.scala.model

sealed trait Menu


case class Command(command: Int) extends Menu
case class None() extends Menu