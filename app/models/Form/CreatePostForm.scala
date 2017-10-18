package models.Form

import play.api.libs.json.Json

/**
  * Created by Yomi on 2017/10/13.
  */
case class CreatePostForm(user_id:Int,hs_value:Int,temp:Int,status:String)
object CreatePostForm {
  implicit def jsonWrites = Json.writes[CreatePostForm]
  implicit def jsonReads = Json.reads[CreatePostForm]
}