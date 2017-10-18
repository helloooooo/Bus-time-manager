package dal

import javax.inject.{Inject, Singleton}
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import models.{Detail, InnerDetail, User}

import scala.concurrent.{ExecutionContext, Future}

/**
 * A repository for people.
 *
 * @param dbConfigProvider The Play db config provider. Play will inject this for you.
 */
@Singleton
class UserRepository @Inject() (dbConfigProvider: DatabaseConfigProvider)(implicit ec: ExecutionContext) {
  // We want the JdbcProfile for this provider
  private val dbConfig = dbConfigProvider.get[JdbcProfile]
  import dbConfig._
  import driver.api._


  private class UserTable(tag: Tag) extends Table[User](tag, "user") {

    /** The ID column, which is the primary key, and auto incremented */
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

    /** The name column */
    def name = column[String]("name")

    def status = column[String]("status")

    def * = (id, name,status) <> ((User.apply _).tupled, User.unapply)
  }

  private class DetailTable(tag:Tag) extends Table[Detail](tag,"detail"){

    def id = column[Long]("id",O.PrimaryKey, O.AutoInc)

    def user_id = column[Int]("user_id")

    def hs_value = column[Int]("hs_value")

    def hs_value_bus = column[Int]("hs_value_bus")


    def temp = column[Int]("temp")
    def temp_bus = column[Int]("temp_bus")


    def * = (id,user_id,hs_value,hs_value_bus,temp,temp_bus) <> ((Detail.apply _).tupled,Detail.unapply)

    }
  /**
   * The starting point for all queries on the people table.
   */
  private val user = TableQuery[UserTable]

  private val detail = TableQuery[DetailTable]
  /**
   * Create a person with the given name and age.
   *
   * This is an asynchronous operation, it will return a future of the created person, which can be used to obtain the
   * id for that person.
   */
  def create(name: String): Future[User] = db.run {
    // We create a projection of just the name and age columns, since we're not inserting a value for the id column
    (user.map(p => (p.name))
      // Now define it to return the id, because we want to know what id was generated for the person
      returning user.map(_.id)
      // And we define a transformation for the returned value, which combines our original parameters with the
      // returned id
      into ((name,id ) => User(id, name,"normal"))
    // And finally, insert the person into the database
    ) += (name)
  }

  /**
   * List all the people in the database.
   */
  def innerlist(id:Int): Future[Seq[User]] = db.run {
//    val data = for {
//      d <- detail if d.user_id === id
//    } yield d
//    data.result
    val data = for {
      u <- user if u.id === id
    } yield u
    data.result
  }

  def ins(user_id:Int,hs_value:Int,hs_value_bus:Int,temp:Int,temp_bus:Int,status:String) = db run {
    detail.map(d => (d.user_id, d.hs_value,d.hs_value_bus, d.temp,d.temp_bus)) += (user_id,hs_value,hs_value_bus,temp,temp_bus)
  }
  def update_status(user_id:Int,status:String) = db.run{
    val get_status = user.filter(_.id === user_id).map(_.status)
    get_status.update(status)
  }



  def list(): Future[Seq[User]] = db.run {
    user.result
  }
//  def masterlist() =  db.run {
//    val user_data = user.result
//    val user_status = List(user_data)
//  }
}


