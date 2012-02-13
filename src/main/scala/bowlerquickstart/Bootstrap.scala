package bowlerquickstart

import org.bowlerframework.view.scalate._
import org.bowlerframework.Request
import org.squeryl.SessionFactory
import com.mchange.v2.c3p0.ComboPooledDataSource
import org.squeryl.adapters.H2Adapter
import org.squeryl.Session

/**
 * This class acts as the starting point and bootstrap point for our application
 */
class Bootstrap{
  // parent layout
  val parentLayout = DefaultLayout("default", "doLayout", None, None)

  def resolver(request: Request): Option[DefaultLayout] = Option(parentLayout)
  TemplateRegistry.defaultLayout = resolver(_)


  // I think we're ready to start and instantiate our Controller.
  val controller = new MyController
	
	
  // allow template reload during development - remove these lines in production for better performance
  org.bowlerframework.view.scalate.RenderEngine.getEngine.allowCaching = false
  org.bowlerframework.view.scalate.RenderEngine.getEngine.allowReload = true
  
  val cpds = setupC3p0("jdbc:h2:mem:test_mem")
  SessionFactory.concreteFactory = Some(() => connection)

  def connection = {
    Session.create(cpds.getConnection, new H2Adapter())
  }

  makeTables

  def makeTables = {
    val session = SessionFactory.newSession
    session.bindToCurrentThread
    try {
      Tables.create
      println("Database created")
    } catch {
      case e: Exception => {
        println("Database exists!")
      }
    } finally {
      session.close
      session.unbindFromCurrentThread
    }
  }

  def setupC3p0(url:String) = {
    val cpds = new ComboPooledDataSource
    cpds.setDriverClass("org.h2.Driver");
    
    cpds.setJdbcUrl(url)

    cpds.setMinPoolSize(5)
    cpds.setAcquireIncrement(1)
    cpds.setMaxPoolSize(10)
    cpds
  }
}