package serialize

import java.io.{FileNotFoundException, FileWriter}

import model.Student

import scala.collection.mutable.ArrayBuffer

/**
  * Created by dexter on 27.11.16.
  */
object StudentSerializer {

  private val fileName = "data.json"

  def downloadStudents():ArrayBuffer[Student] = {
    try {
        val source =  scala.io.Source.fromFile(fileName)
        val json = try source.mkString finally source.close()
        parseJson(json)
    }  catch {
      case e: FileNotFoundException => new ArrayBuffer[Student]
    }
  }

  def parseJson(json: String): ArrayBuffer[Student] = {
    // TODO mutable ArrayBuffer
    val o = JsonSerializer.toObject(json, new Array[Student](1).getClass)
    var newStudents = new ArrayBuffer[Student]
    o.getOrElse(new Array[Student](0)).foreach(s => newStudents = newStudents :+ s)
    newStudents
  }


  def saveStudents(students: Seq[Student]) {
    // TODO do not create FileWriter every time you need
    val writer = new FileWriter(fileName)
    writer.write(JsonSerializer.toJason(students.toArray))
    writer.close()
  }

}
