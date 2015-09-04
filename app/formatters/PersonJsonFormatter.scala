package formatters

import models.{Name, Person}
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Format

object Formatter {
  implicit val NameJsonFormatter: Format[Name] = (
    (__ \ "first").format[String] and
      (__ \ "last").format[String]
    )(Name.apply _, unlift(Name.unapply))

  implicit val PersonJsonFormatter: Format[Person] = (
    (__ \ "age").format[Int] and
      (__ \ "name").format[Name]
    )(Person.apply _, unlift(Person.unapply))
}
