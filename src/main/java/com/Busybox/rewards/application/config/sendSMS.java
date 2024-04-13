package com.Busybox.rewards.application.config;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;
 
@Service
public class sendSMS {
	public String sendSmsNow(String messages , String number) {
		try {
			// Construct data
			String apiKey = "apikey=" + "NWE3NjUzNmU2NjU3NjY1NzdhNGY2YTM3MzA1YTc2NDg=";
			String message = "&message=" + messages;
			String sender = "&sender=" + "TJUICN";
			String numbers = "&numbers=" + number;
//			System.out.println(message.toString());
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			System.out.println(data.toString());
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			
			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}
}