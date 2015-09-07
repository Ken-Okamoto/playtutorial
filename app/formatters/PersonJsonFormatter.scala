package formatters

import models.{Name, Person}
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.json.Format

object Formatter {
  implicit val NameJsonFormatter: Format[Name] = (
    (__ \ "first").format[String] and
      (__ \ "middle").formatNullable[String] and
      (__ \ "last").format[String]
    )(Name.apply _, unlift(Name.unapply))

  implicit val PersonJsonFormatter: Format[Person] = (
    (__ \ "age").format[Int] and
      (__ \ "name").format[Name] and
      (__ \ "bloodType").formatNullable[String] and
      (__ \ "favoriteNumber").format[Seq[Int]]
    )(Person.apply _, unlift(Person.unapply))
}
