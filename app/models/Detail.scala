package models

import play.api.libs.json._

case class Detail(id:Long,user_id:Int,hs_value:Int,hs_value_bus:Int,temp:Int,temp_bus:Int)
case class InnerDetail(user_id:Int,hs_value:Int,hs_value_bus:Int,temp:Int,temp_bus:Int)
/**
  * Created by Yomi on 2017/10/09.
  */
object Detail {
  implicit val detailFormat = Json.format[Detail]
  implicit val innerDetailFormmat = Json.format[InnerDetail]
}
