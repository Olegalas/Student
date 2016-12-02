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
    // example creating ArrayBuffer from Array
    var newStudents = new ArrayBuffer[Student]
    val o = JsonSerializer.toObject(json, new Array[Student](1).getClass)
    o.getOrElse(new Array[Student](0)).foreach(s => newStudents += s)
    newStudents
  }


  def saveStudents(students: Seq[Student]) {
    val writer = new FileWriter(fileName)
    writer.write(JsonSerializer.toJason(students.toArray))
    writer.close()
  }

}
