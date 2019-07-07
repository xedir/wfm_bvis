package com.bvis.wip.delegates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;

import javax.ws.rs.core.MediaType;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class StartMaintenance implements JavaDelegate {
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
	//TODO Auto-generated method stub

	int maintid = (int) execution.getVariable("maintcar");
	execution.setVariable("maintid", maintid);
		
	try {

			URL url = new URL("http://localhost:8080/rest/process-definition/key/maintsub/start");
			String x = "{\"variables\": {\"maintid\":{\"value\":"+maintid+", \"type\":\"Integer\"}}}";
			//System.out.println("XXXXXXX>>>>> "+x);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();
			os.write(x.toString().getBytes("UTF-8"));

			if (conn.getResponseCode() != 200) {
				execution.setVariable("ResponseCode", conn.getResponseCode());
				throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
			} else {
				execution.setVariable("ResponseCode", 200);
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			System.out.println("Success: 200 - Output from Server ....");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			os.close();
			conn.disconnect();
		    } catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	
		
	}

}
