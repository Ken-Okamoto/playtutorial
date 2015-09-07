import org.junit.runner.RunWith
import org.specs2.mutable._
import org.specs2.runner._
import play.api.libs.json.Json
import play.api.test._
import play.api.test.Helpers._

@RunWith(classOf[JUnitRunner])
class PersonApiSpec extends Specification {
  "PersonApi#register" should {
    "response json validation error" in new WithApplication {
      val Some(result) = route(FakeRequest(POST, "/api/person",

        FakeHeaders(Seq(CONTENT_TYPE -> "application/json")),
        Json.parse( """{"typo1!!!":24, "name":{"first":"FirstName", "typo2!!!":"LastName", "favoriteNumber":[1,2,3,4,5]}}""")))
      status(result) mustEqual BAD_REQUEST
      contentAsString(result) mustEqual
        """{"obj.age":[{"msg":"error.path.missing","args":[]}],"obj.name.last":[{"msg":"error.path.missing","args":[]}],"obj.favoriteNumber":[{"msg":"error.path.missing","args":[]}]}"""
    }

    "response json validation ok" in new WithApplication {
      val Some(result) = route(FakeRequest(POST, "/api/person",
        FakeHeaders(Seq(CONTENT_TYPE -> "application/json")),
        Json.parse( """{"age":24, "name":{"first":"FirstName", "last":"LastName"}, "bloodType":"A", "favoriteNumber":[1,2,3,4,5]}""")))
      status(result) mustEqual OK
      contentAsString(result) mustEqual
        """{"age":24,"name":{"first":"FirstName","last":"LastName"},"bloodType":"A","favoriteNumber":[1,2,3,4,5]}"""
    }

    "response json validation with MiddleName ok" in new WithApplication {
      val Some(result) = route(FakeRequest(POST, "/api/person",
        FakeHeaders(Seq(CONTENT_TYPE -> "application/json")),
        Json.parse( """{"age":24, "name":{"first":"FirstName", "middle":"MiddleName", "last":"LastName"}, "bloodType":"A", "favoriteNumber":[1,2,3,4,5]}""")))
      status(result) mustEqual OK
      contentAsString(result) mustEqual
        """{"age":24,"name":{"first":"FirstName","middle":"MiddleName","last":"LastName"},"bloodType":"A","favoriteNumber":[1,2,3,4,5]}"""    }
  }

}
