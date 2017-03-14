package br.com.crypto.commons.security.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Assert;
import org.junit.Test;

import br.com.crypto.commons.security.HttpSQLInjectionTest;

public class HttpSQLInjectionTestImpl implements HttpSQLInjectionTest{
	
	@Test
	public void test(){
		
		Assert.assertEquals(false, injectPOSTFields("http://localhost/Meta.Web.Qualicorp.GED/", new String[]{"login", "senha"}));
		
	}
	
	private final String USER_AGENT = "Mozilla/5.0";
	private final int ROUND_TRIP = 1; 
	
	public boolean injectPOSTFields(String url, String[] fields){
		
		try {
			
			HttpClient client = new DefaultHttpClient();
			HttpPost request = new HttpPost(url);
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			for(String field : fields){
			
				params.add(new BasicNameValuePair(field, "if(now()=sysdate(),sleep("+ROUND_TRIP+"),0)/*'XOR(if(now()=sysdate(),sleep("+ROUND_TRIP+"),0))OR'\"XOR(if(now()=sysdate(),sleep("+ROUND_TRIP+"),0))OR\"*/"));
				
			}

			// add request header
			request.addHeader("User-Agent", USER_AGENT);
			request.setEntity(new UrlEncodedFormEntity(params));
			

			long start = System.currentTimeMillis();
			HttpResponse response = client.execute(request);
			long end = System.currentTimeMillis();
			
			long roundtrip = (end-start);
			
			System.out.println("Round trip response time = " + (end-start) + " millis");
			System.out.println("Sending 'POST' request to URL : " + request.getURI().getPath());
			System.out.println("Response Code : " +  response.getStatusLine().getStatusCode());
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			
			String line = null;
			while ((line = rd.readLine()) != null) {
				System.out.println(line);
			}

			if(roundtrip >= (ROUND_TRIP*1000)){
				return true;
			}else{
				return false;
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
}
