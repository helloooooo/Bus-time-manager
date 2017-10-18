package controllers

import play.api._
import play.api.mvc._
import play.api.i18n._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.libs.json._
import play.api.Logger
import models.Form.CreatePostForm
import models._
import dal._

import scala.concurrent.{ExecutionContext, Future}
import javax.inject._

import play.api.libs.json

class UserController @Inject() (repo: UserRepository, val messagesApi: MessagesApi)
                                 (implicit ec: ExecutionContext) extends Controller with I18nSupport {

  /**
    * The mapping for the person form.
    */
  val userForm: Form[CreateUserForm] = Form {
    mapping(
      "name" -> nonEmptyText
    )(CreateUserForm.apply)(CreateUserForm.unapply)
  }
  val detailForm: Form[CreateDetailForm] = Form {
    mapping(
      "user_id" -> number,
      "hs_value" -> number,
      "temp" -> number,
      "status" -> text
    )(CreateDetailForm.apply)(CreateDetailForm.unapply)
  }

  /**
    * The index action.
    */
  def index = Action {
    Ok(views.html.index(userForm))
  }

  def addUser = Action.async { implicit request =>
    userForm.bindFromRequest.fold(
      errorForm => {
        Future.successful(Ok(views.html.index(errorForm)))
      },
      person => {
        repo.create(person.name).map { _ =>
          // If successful, we simply redirect to the index page.
          Redirect(routes.UserController.index)
        }
      }
    )
  }

  /**
    * A REST endpoint that gets all the people as JSON.
    */
  def getUsers = Action.async {
    repo.list().map { people =>
      Ok(Json.toJson(people))
    }
  }

  def list = Action.async {
    repo.list().map {
      user => Ok(views.html.list(user))
    }
  }

  def innerlist(id:Int) = Action.async {
    repo.innerlist(id).map {
      user => Ok(views.html.inner(user))
    }
  }

  def addDetail = Action.async { implicit request =>
    val json_body: String = request.body.asJson.get.toString
    val data: JsValue = Json.parse(json_body)
    val user_id = (data \ "user_id").asOpt[Int]
    val hs_value = (data \ "hs_value").asOpt[Int]
    val hs_value_bus = (data \ "hs_value_bus").asOpt[Int]
    val temp = (data \ "temp").asOpt[Int]
    val temp_bus = (data \ "temp_bus").asOpt[Int]
    val status = (data \ "status").asOpt[String]
    println(json_body)
    repo.update_status(user_id.get,status.get)
    repo.ins(user_id.get,hs_value.get,hs_value_bus.get,temp.get,temp_bus.get,status.get).map(_ =>
      Ok("success")
    )
  }
}

/**
 * The create person form.
 *
 * Generally for forms, you should define separate objects to your models, since forms very often need to present data
 * in a different way to your models.  In this case, it doesn't make sense to have an id parameter in the form, since
 * that is generated once it's created.
 */
case class CreateUserForm( name: String)
