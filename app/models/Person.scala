package models

case class Person(age:Int, name:Name)
//case class Name(first:String, last:String)
case class Name(first:String, middle:Option[String], last:String)
