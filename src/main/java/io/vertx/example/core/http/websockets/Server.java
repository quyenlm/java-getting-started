package io.vertx.example.core.http.websockets;

import java.io.File;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.example.util.Runner;

/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class Server extends AbstractVerticle {

  // Convenience method so you can run it in your IDE
  public static void main(String[] args) {
    Runner.runExample(Server.class);
  }

  @Override
  public void start() throws Exception {
	int port = 8080;
	try {
		Integer.valueOf(System.getenv("PORT"));
	} catch (Exception e) {
		System.out.println("cannot get getenv PORT, use default port: " + port);
	}
	
	System.out.println("start listening port: " + port);
	
	Vertx.vertx().createHttpServer().websocketHandler(ws -> ws.handler(ws::writeBinaryMessage)).requestHandler(req -> {
		System.out.println("request: " + req.uri());
		
		if (req.uri().equals("/"))
			req.response().sendFile("ws.html");
	}).listen(port);
	
//    vertx.createHttpServer().websocketHandler(ws -> ws.handler(ws::writeBinaryMessage)).requestHandler(req -> {
//    	System.out.println(req.uri());
//    	System.out.println(System.getProperties().getProperty("user.dir"));
//    	
//    	if (req.uri().equals("/")) req.response().sendFile(System.getProperties().getProperty("user.dir") + File.separatorChar + "ws.html");
//    }).listen(port);
    
  }
}
