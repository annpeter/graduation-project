import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.thread.QueuedThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created on 2017/02/19
 *
 * @author annpeter.it@gmail.com
 */
public class TestWebBootstrap {
    private static final Logger logger = LoggerFactory.getLogger(TestWebBootstrap.class);
    private static final Server server = new Server();

    public static void main(String[] args) throws Exception {
        System.setProperty("log.basedir", "/logs/graduation-project");
        System.setProperty("log.appender", "STDOUT");

        QueuedThreadPool boundedThreadPool = new QueuedThreadPool();
        boundedThreadPool.setMaxThreads(5);
        server.setThreadPool(boundedThreadPool);


        Connector connector = new SelectChannelConnector();
        connector.setPort(8080);
        server.addConnector(connector);

        WebAppContext context = new WebAppContext("src/main/web", "/");
        server.setHandler(context);
        server.setStopAtShutdown(true);
        server.setSendServerVersion(true);

        server.start();
        logger.info("============================ the server is started ===========================");
        server.join();
    }
}