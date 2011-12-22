package bowlerquickstart


import _root_.org.eclipse.jetty.server.Server
import _root_.org.eclipse.jetty.server.nio.SelectChannelConnector
import _root_.org.eclipse.jetty.servlet.ServletContextHandler
import _root_.org.eclipse.jetty.servlet.DefaultServlet
import _root_.org.bowlerframework.http.BowlerFilter

object JettyLauncher {
	def main(args: Array[String]) {
		val port = if(System.getenv("PORT") != null) System.getenv("PORT").toInt else 8080
		println("Jetty starting on port: " + port)
		val server = new Server
		val scc = new SelectChannelConnector
		scc.setPort(port)
		server.setConnectors(Array(scc))

		val context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS)
		val filterHolder = context.addFilter(classOf[BowlerFilter], "/*", 0)
		filterHolder.setInitParameter("bootstrapClass", "bowlerquickstart.Bootstrap")
		context.addServlet(classOf[DefaultServlet], "/*")
		context.setResourceBase("src/main/webapp")
		
		server.start
		server.join
	}
}