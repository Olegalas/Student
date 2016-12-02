package serialize

import com.google.gson.Gson
import model.Student
import scala.collection.mutable.ArrayBuffer

object JsonSerializer {

  private val gson = new Gson
  
  def toJason(o: AnyRef) : String = gson.toJson(o)
  
  def getStudents(json: String): Seq[Student] = {
    // Type of array Students
    val typeClass = new Array[Student](1).getClass
    gson.fromJson(json, typeClass).toSeq
  }


}