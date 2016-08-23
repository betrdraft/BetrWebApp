package com.betr.server.external.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;

import com.betr.server.exception.ThirdPartyAPIException;

public class HttpUtil {
	
	private static JSONParser parser = new JSONParser();

	public static JSONObject peformJSONGetRequest(String url) throws ThirdPartyAPIException {
		CloseableHttpResponse response = null;
	    
		try (CloseableHttpClient httpClient = HttpClients.createDefault()){

			HttpGet httpGet = new HttpGet(url);
			
			response = httpClient.execute(httpGet);
			
			HttpEntity entity = response.getEntity();

			String retSrc = EntityUtils.toString(entity); 
			
			return (JSONObject)parser.parse(retSrc);
			
		} catch (Exception e) {
			throw new ThirdPartyAPIException(e.getMessage(), e);
		}
	}
	
	public static Document performXMLHttpGetRequest(String url) throws ThirdPartyAPIException {
	    Document ret = null;
	    CloseableHttpResponse response = null;
	    
		try (CloseableHttpClient httpClient = HttpClients.createDefault()){
		    DocumentBuilderFactory factory = null;
		    DocumentBuilder builder = null;
		    
		    factory = DocumentBuilderFactory.newInstance();
		    builder = factory.newDocumentBuilder();
		    
			
			HttpGet httpGet = new HttpGet(url);
			
			response = httpClient.execute(httpGet);
			
			HttpEntity entity = response.getEntity();
			
			ret = builder.parse(entity.getContent());
			
			ret.getDocumentElement().normalize();
		} catch (Exception e) {
			throw new ThirdPartyAPIException(e.getMessage(), e);
		} finally {
			try {
				response.close();
			} catch (IOException e) {}
		}
		return ret;
	}
	
	private static String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {

			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();

	}

}
