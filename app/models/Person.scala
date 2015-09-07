package models

case class Person(age:Int, name:Name, bloodType:Option[String], favoriteNumber:Seq[Int])
//case class Name(first:String, last:String)
case class Name(first:String, middle:Option[String], last:String)
