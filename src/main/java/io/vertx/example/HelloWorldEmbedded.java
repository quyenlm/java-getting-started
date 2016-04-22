package io.vertx.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.regex.Matcher;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.ServerWebSocket;

/**
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class HelloWorldEmbedded {

	public static void main(String[] args) {
		// Create an HTTP server which simply returns "Hello World!" to each
		// request.
//		 Vertx.vertx().createHttpServer().requestHandler(req ->
//		 req.response().end("Hello World!")).listen(8080);

		Vertx.vertx().createHttpServer()
				.websocketHandler(ws -> ws.handler(ws::writeBinaryMessage))
				.requestHandler(req -> {
					if (req.uri().equals("/"))
						req.response().sendFile("ws.html");
				}).listen(8080);
		
//		Vertx.vertx().createHttpServer().websocketHandler(new Handler<ServerWebSocket>() {
//			@Override
//			public void handle(final ServerWebSocket ws) {
////				final Matcher m = chatUrlPattern.matcher(ws.path());
////				if (!m.matches()) {
////					ws.reject();
////					return;
////				}
//				System.out.println(ws);
////				final String chatRoom = m.group(1);
////				final String id = ws.textHandlerID();
////				System.out.println("registering new connection with id: " + id + " for chat-room: " + chatRoom);
////				Vertx.vertx().sharedData().getSet ("chat.room." + chatRoom).add(id);
////		 
////				ws.closeHandler(new Handler<Void>() {
////					@Override
////					public void handle(final Void event) {
////						System.out.println("un-registering connection with id: " + id + " from chat-room: " + chatRoom);
////						Vertx.vertx().sharedData().getSet("chat.room." + chatRoom).remove(id);
////					}
////				});
////		 
////				ws.dataHandler(new Handler<Buffer>() {
////					@Override
////					public void handle(final Buffer data) {
////		 
////						ObjectMapper m = new ObjectMapper();
////						try {
////							JsonNode rootNode = m.readTree(data.toString());
////							((ObjectNode) rootNode).put("received", new Date().toString());
////							String jsonOutput = m.writeValueAsString(rootNode);
////							System.out.println("json generated: " + jsonOutput);
////							for (Object chatter : vertx.sharedData().getSet("chat.room." + chatRoom)) {
////								eventBus.send((String) chatter, jsonOutput);
////							}
////						} catch (IOException e) {
////							ws.reject();
////						}
////					}
////				});
//		 
//			}
//		}).listen(8080);
	}

}
