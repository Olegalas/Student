package model

sealed trait Menu

case class Command(command: Int) extends Menu
case class None() extends Menu





sealed trait Choose

case class Yes() extends Choose
case class No() extends Choose
case class Default() extends Choose