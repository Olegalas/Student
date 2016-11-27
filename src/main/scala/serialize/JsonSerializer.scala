package serialize

import com.google.gson.Gson

object JsonSerializer {

  private val gson = new Gson

  def toJason(o: AnyRef) : String = gson.toJson(o)
  
  def toObject[T](json: String, typeClass: Class[T]): Option[T] = {
    Option.apply(gson.fromJson(json, typeClass))
  }


}