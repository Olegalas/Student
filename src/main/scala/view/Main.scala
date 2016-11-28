package view


object Main extends App {

    val view = new View()  

    while(true){

      view.startMenu()
      val command = view.doCommand()
      view.process(command)

    }
    
}