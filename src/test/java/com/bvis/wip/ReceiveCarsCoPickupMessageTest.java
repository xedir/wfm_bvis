package com.bvis.wip;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;
import org.junit.Test;
import static org.asynchttpclient.Dsl.*;

public class ReceiveCarsCoPickupMessageTest {

	private final static Logger LOGGER = Logger.getLogger("ReceiveCarsCoPickupMessageTest");
	
	@Test
	public void getProcessInstanceList() throws InterruptedException, ExecutionException {
		LOGGER.info("hello");

		AsyncHttpClient asyncHttpClient = asyncHttpClient();
		Future<Response> whenResponse = asyncHttpClient.prepareGet("http://localhost:8080/engine-rest/process-instance").execute();
		Response response = whenResponse.get();
		String responseBody = response.getResponseBody();
		LOGGER.info(responseBody);
	}
	
	/*
	 Scenario: a token with claimId 7 hangs on the 'receive message from cars&co' letter symbol.
	 to have this token move further,
	 
	 HTTP POST to
	 localhost:8080/engine-rest/message/
	 
	 with the following request payload:
	 
	 {
       "messageName" : "receive_pickup_message",
       "correlationKeys" : {
         "claimId" : {"value" : "7", "type": "Integer"}
       }
     }
	 */
	
	@Test
	public void testReceiveMessage() throws InterruptedException, ExecutionException {
		LOGGER.info("testReceiveMessage");

		AsyncHttpClient asyncHttpClient = asyncHttpClient();
		Future<Response> whenResponse = asyncHttpClient.preparePost("http://localhost:8080/engine-rest/message").setBody("	 {\n" + 
				"       \"messageName\" : \"receive_pickup_message\",\n" + 
				"       \"correlationKeys\" : {\n" + 
				"         \"claimId\" : {\"value\" : \"6\", \"type\": \"Integer\"}\n" + 
				"       }\n" + 
				"     }").addHeader("Content-Type", "application/json").
				execute();
		Response response = whenResponse.get();
		String responseBody = response.getResponseBody();
		LOGGER.info(responseBody);

	}
	
	
	@Test
	public void testReceiveMessageDelay() throws InterruptedException, ExecutionException {
		LOGGER.info("testReceiveMessageDelay");

		Runnable runnable = () -> {
			LOGGER.info("inside thread");
			try {
				Thread.sleep(1000);
				LOGGER.info("inside thread. after 1 second of sleep");
				AsyncHttpClient asyncHttpClient = asyncHttpClient();
				Future<Response> whenResponse = asyncHttpClient.preparePost("http://localhost:8080/engine-rest/message").setBody("	 {\n" + 
					"       \"messageName\" : \"receive_pickup_message\",\n" + 
					"       \"correlationKeys\" : {\n" + 
					"         \"claimId\" : {\"value\" : \"6\", \"type\": \"Integer\"}\n" + 
					"       }\n" + 
					"     }").addHeader("Content-Type", "application/json").
					execute();
				Response response;
			
				response = whenResponse.get();
				String responseBody = response.getResponseBody();
				LOGGER.info(responseBody);
				LOGGER.info("inside thread. about to exit");
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		};
		
		LOGGER.info("starting thread");
		Thread thread = new Thread(runnable);
		thread.start();
		LOGGER.info("thread started");
		LOGGER.info("main program about to sleep for 2 seconds");
		Thread.sleep(2000);
		LOGGER.info("main program waked up after 2 seconds. Main program about to exit.");
		
		
	}
}
