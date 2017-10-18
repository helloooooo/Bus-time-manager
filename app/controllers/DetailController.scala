package controllers

import play.api._
import play.api.mvc._
import play.api.i18n._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.libs.json.Json
import models._
import dal._

import scala.concurrent.{ ExecutionContext, Future }

import javax.inject._
/**
  * Created by Yomi on 2017/10/10.
  */
class DetailController @Inject() (drepo: DetailRepository,urepo:UserRepository,val messagesApi:MessagesApi)(implicit ec :ExecutionContext) extends Controller with I18nSupport{


}

case class CreateDetailForm(user_id:Int,hs_value:Int,temp:Int,status:String)
