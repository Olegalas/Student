package main.scala.view


object Main extends App {

    val view = new View()  

    while(true){
      view.startMenu()
      val command = view.getCommand();
      view.process(command)
    }

}