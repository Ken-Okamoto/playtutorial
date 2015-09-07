package controllers

import models.Person
import play.api.libs.json.{JsError, Json}
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits._

import scala.concurrent.Future

class PersonApi extends Controller {
  import formatters.Formatter.PersonJsonFormatter

  def register = Action.async(parse.json) {
//    request=>{
      _.body.validate[Person].map {
        cleanedData => {
          val futureResponse: Future[Person] = for {
            futureObj <- getFutureObject(cleanedData)
          } yield futureObj

          futureResponse.map { res =>
            Ok(Json.toJson(res))
          }
        }
      }.recoverTotal { e =>
        Future(BadRequest(JsError.toJson(e)))
      }
//      e=>BadRequest(JsError.toFlatJson(e))
//    }
  }

  def getFutureObject(p: Person): Future[Person] = {
    Future.successful(p)
  }
}
