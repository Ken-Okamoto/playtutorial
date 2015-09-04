import org.junit.runner.RunWith
import org.specs2.mutable._
import org.specs2.runner._
import play.api.libs.json.Json
import play.api.test._
import play.api.test.Helpers._

@RunWith(classOf[JUnitRunner])
class PersonApiSpec extends Specification {
  "PersonApi#register" should {
//    "send 404 on a bad request" in new WithApplication{
//      route(FakeRequest(GET, "/boum")) must beSome.which (status(_) == NOT_FOUND)
//    }
//
//    "render the index page" in new WithApplication{
//      val home = route(FakeRequest(GET, "/")).get
//
//      status(home) must equalTo(OK)
//      contentType(home) must beSome.which(_ == "text/html")
//      contentAsString(home) must contain ("Your new application is ready.")
//    }
    "response json validation error" in new WithApplication {
      val Some(result) = route(FakeRequest(POST, "/api/person",
        FakeHeaders(Seq(CONTENT_TYPE -> "application/json")),
        Json.parse( """{"typo1!!!":24, "name":{"first":"FirstName", "typo2!!!":"LastName"}}""")))
      status(result) mustEqual BAD_REQUEST
      contentAsString(result) mustNotEqual
        """ {"person.age":[{"msg":"error.path.missing"}],"person.name.last":[{"msg":"error.path.missing"}]}"""
    }
  }

}
