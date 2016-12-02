package view

import scala.collection.mutable.ArrayBuffer


object Main extends App {

    val view = new View()  

    while(true){

      view.startMenu()
      val command = view.doCommand()
      view.process(command)

    }
    
}