package by.grsu.dbobovik.phonestat.server;
import java.net.MalformedURLException;

import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * Separate startup class for people that want to run the examples directly. Use
 * parameter -Dcom.sun.management.jmxremote to startup JMX (and e.g. connect
 * with jconsole).
 */
public final class StartJetty {

	private StartJetty() {
	}

	/**
	 * Main function, starts the jetty server.
	 *
	 * @param args
	 * @throws MalformedURLException
	 */

	public static void main(final String[] args) throws MalformedURLException {
		startInstance(8081);
	}

	private static void startInstance(final int port) throws MalformedURLException {
		final Server server = new Server();

		final HttpConfiguration httpConfig = new HttpConfiguration();
		httpConfig.setOutputBufferSize(32768);

		final ServerConnector http = new ServerConnector(server, new HttpConnectionFactory(httpConfig));
		http.setPort(port);
		http.setIdleTimeout(1000 * 60 * 60);

		server.addConnector(http);

		final WebAppContext bb = new WebAppContext();
		bb.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/[^/]*taglibs.*\\.jar$");
		bb.setServer(server);
		bb.setContextPath("/");
		bb.setWar("src/main/webapp");
		server.setHandler(bb);

		try {
			server.start();
		} catch (final Exception e) {
			e.printStackTrace();
			System.exit(100);
		}
	}
}
