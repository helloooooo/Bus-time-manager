package dal

import javax.inject.{Singleton,Inject}

import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile

import models.Detail
import models.User

import scala.concurrent.{Future,ExecutionContext}
/**
  * Created by Yomi on 2017/10/09.
  */
@Singleton
class DetailRepository @Inject() (dbConfigProvider:DatabaseConfigProvider)(implicit ec :ExecutionContext){
  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  import dbConfig._
  import driver.api._

  private class DetailTable(tag:Tag) extends Table[Detail](tag,"detail"){

    def id = column[Long]("id",O.PrimaryKey, O.AutoInc)

    def user_id = column[Int]("user_id")

    def hs_value = column[Int]("hs_value")

    def hs_value_bus = column[Int]("hs_value_bus")


    def temp = column[Int]("temp")
    def temp_bus = column[Int]("temp_bus")


    def * = (id,user_id,hs_value,hs_value_bus,temp,temp_bus) <> ((Detail.apply _).tupled,Detail.unapply)

  }

  private val detail = TableQuery[DetailTable]



  def list(): Future[Seq[Detail]] = db.run {
    detail.result
  }
}
