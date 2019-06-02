package com.bvis.wip.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

//for Rest
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StartProcessTestDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		//Random rando = new Random();
		//execution.setVariable("variable1", "lol");
		//execution.setVariable("OK?", rando.nextBoolean());
		
		try {

			URL url = new URL("http://localhost:8080/engine-rest/process-definition/key/TestRestProcess/start");
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");

			if (conn.getResponseCode() != 200) {
				execution.setVariable("ResponseCode", conn.getResponseCode());
				throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
			} else {
				System.out.println("Success! 200");
				execution.setVariable("ResponseCode", 200);
			}
			

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }

	}

}
