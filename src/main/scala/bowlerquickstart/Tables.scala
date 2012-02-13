package bowlerquickstart
import org.squeryl.Schema

object Tables extends Schema {
  val names = table[Name]
}

case class Name(val name:String){
  def this() = this("") 
}