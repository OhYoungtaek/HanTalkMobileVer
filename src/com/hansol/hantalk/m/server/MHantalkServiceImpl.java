package com.hansol.hantalk.m.server;

import java.io.BufferedReader;

import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Properties;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.hansol.hantalk.m.client.MHantalkService;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class MHantalkServiceImpl extends RemoteServiceServlet implements MHantalkService{
	public String login(String id, String pwd) throws Exception{
		System.out.println(("Start.."));
		String session="";
		try{
			session = getSession(id, pwd);
			System.out.println(session);
			if(session != null && session.trim().length() != 0){
				System.out.println("LOGIN OK!!");
			}
		}catch(Exception e){
			System.out.println("Failed...");
		}
		return session;
	}
	
	public String getSession(String id, String pwd) throws Exception{
		String jsessionId = "";
		String urlstr = "http://hantalk.hansol.net/j_spring_security_check";
		URL url = new URL(urlstr);
		Properties param = new Properties();
		param.put("j_username", id);
		param.put("j_password", pwd);

		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setRequestMethod("POST");
		http.setDoOutput(true);
		http.setDoInput(true);
		http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		http.setUseCaches(false);
		http.setInstanceFollowRedirects(false);

		DataOutputStream out = new DataOutputStream(http.getOutputStream());
		
		String content = "";
		for(Enumeration<Object> e = param.keys(); e.hasMoreElements(); ){
			String key = (String)e.nextElement();
			String value = param.getProperty(key);
			content += key+"="+URLEncoder.encode(value, "UTF-8")+"&";
		}
		out.writeBytes(content);
		out.flush();
		out.close();
		System.out.println(http.getHeaderFields());

		String location = http.getHeaderField("Location");
		if(location.length() > 70){
			int idx1 = location.indexOf("=")+1;
			int idx2 = location.indexOf('?');
			if(idx1 != -1 && idx2 != -1){
				jsessionId = "JSESSIONID="+location.substring(idx1,idx2 ) + ";";
				System.out.println(jsessionId);
			}
		} else {
			jsessionId = http.getHeaderField("set-cookie");
			jsessionId = jsessionId.substring(0, 42);
			System.out.println(jsessionId);
		}
		
		return jsessionId;
	}
	
	public String getAccountInfo(String session, String id)  throws Exception{
		String url = new String("http://hantalk.hansol.net/ajax/getAccountInfo.action");
		Properties param = new Properties();
		param.put("user_id", id);
		return request(session, url, param);
	}
	
	public String getTimeline(String session, String offset, String length) throws Exception{
		return getTimeline(session, offset, length, null);
	}
	
	public String getTimeline(String session, String offset, String length,	String group) throws Exception {
		String url = new String("http://hantalk.hansol.net/ajax/getTimeline.action");
		
		Properties param = new Properties();
		param.put("offset",	offset);
		param.put("length", length);
		
		if ( group != null )
			param.put("ug_id", group);
		
		return request(session, url, param);
	}
	
	public String getUserTimeline(String session, String offset, String length) throws Exception {
		String url = new String("http://hantalk.hansol.net/ajax/getTimelineProfile.action");
		
		Properties param = new Properties();
		param.put("offset",	offset);
		param.put("length", length);
		param.put("reply", "3");
		param.put("sort", "desc");
		param.put("sortkey", "time");
		param.put("timeline", "profile");
		param.put("timeopt", "0");
		return request(session, url, param);
	}
	
	public String request(String session, String urlstr, Properties param) throws Exception{		
		return request(session, urlstr, param, false);
	}
	
	public String request(String session, String urlstr, Properties param, boolean post) throws Exception{
		String paramStr = "";
		System.out.println("in request");
		if(param != null){
			for(Enumeration<Object> e = param.keys(); e.hasMoreElements(); ){
				String key = (String)e.nextElement();
				String value = param.getProperty(key);
				paramStr += key+"="+URLEncoder.encode(value, "UTF-8")+"&";
			}
		}
		
		URL url = post ? new URL(urlstr) : new URL(urlstr +"?"+paramStr);

		HttpURLConnection http = (HttpURLConnection) url.openConnection();
		http.setUseCaches(false);
		http.setRequestProperty("Cookie", session);
		http.setDoInput(true);
		
		if(post){
			http.setRequestMethod("POST");
			http.setDoOutput(true);
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			
			
			DataOutputStream out = new DataOutputStream(http.getOutputStream());
			out.writeBytes(paramStr);
			out.flush();
			out.close();

		}else{
			http.setRequestMethod("GET");
			http.connect();			
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream(),"UTF-8"));
		String result = "";
		String line = "";
		while ((line = in.readLine()) != null) {
			result +=line;
		}
//		System.out.println(result.substring(10, result.length()-1));
		return result.substring(10, result.length()-1);	
	}

	public String getReplyAll(String session, String postId, int maxReply) throws Exception {
		// TODO Auto-generated method stub
		String url = new String("http://hantalk.hansol.net/ajax/getReplyAll.action");
		
		Properties param = new Properties();
		param.put("post_id", postId);
		param.put("max", Integer.toString(maxReply));
		
		return request(session, url, param);
	}
	
	public String getUserGroupList(String session, String offset, String length, 
			String own, String sort, String sortkey, String char_index)  throws Exception {
		String url = new String("http://hantalk.hansol.net/ajax/getUserGroupList.action");
		
		Properties param = new Properties();
		param.put("offset",	offset);
		param.put("length", length);
		param.put("own", own);
		param.put("sort", sort);
		param.put("sortkey", sortkey);
//		param.put("char_index", char_index);
		
		return request(session, url, param);
	}
	
	public String post(String session, String group, String text, String parent, String via) throws Exception{
		String url = new String("http://hantalk.hansol.net/ajax/post.action");
		Properties param = new Properties();
		param.put("ug_id", group);
		param.put("parent_post_id", parent == null ? "-1" :parent);
		param.put("via", "mobile");
		param.put("profile", "0");
		param.put("post_text", text);

		URL u = new URL(url);
		HttpURLConnection http = (HttpURLConnection) u.openConnection();
		http.setRequestMethod("POST");
		
		http.setDoInput(true);
		http.setDoOutput(true);
		http.setUseCaches(false);
		http.setInstanceFollowRedirects(false);
		
		String boundary = Long.toHexString(System.currentTimeMillis());
		http.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
		http.setRequestProperty("Cookie", session);
	

		DataOutputStream out = new DataOutputStream(http.getOutputStream());
		
		String content = "";
		for(Enumeration<Object> e = param.keys(); e.hasMoreElements(); ){
			String key = (String)e.nextElement();
			String value = param.getProperty(key);
			content += "--"+boundary+"\r\n";
			content += "Content-Disposition: form-data; name=\""+key+"\"\r\n\r\n";
			content += value+"\r\n";
			
		}
		
		content += "--"+boundary+"\r\n";
		content += "Content-Disposition: form-data; name=\"upload_file\"; filename=\"\"\r\n";
		content += "Content-Type: application/octet-stream\r\n\r\n\r\n";
		content += "--" + boundary+"--\r\n";
		
		out.writeUTF(content);
		out.flush();
		out.close();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
		String line = "";
		String result = "";
		
		while ((line = in.readLine()) != null) {
			result +=line;
		}
		
		return result.substring(33, result.length()-1);	
	}

	@Override
	public String getChartList(String session, String chart_kind, String period)
			throws Exception {
		String url = new String("http://hantalk.hansol.net/ajax/getChartList.action");
		
		Properties param = new Properties();
		param.put("chart_kind", chart_kind);
		param.put("period", period);
		
		return request(session, url, param);
	}

	@Override
	public String getPost(String session, String postId) throws Exception {
		// TODO Auto-generated method stub
		String url = new String("http://hantalk.hansol.net/ajax/getPost.action");
		
		Properties param = new Properties();
		param.put("post_id", postId);
		System.out.println(postId);
		return request(session, url, param);
	}

	@Override
	public void logout(String session) throws Exception {
		// TODO Auto-generated method stub
		String url = new String("http://hantalk.hansol.net/j_spring_security_logout");
		
		Properties param = new Properties();
		request(session, url, param);
	}
}
