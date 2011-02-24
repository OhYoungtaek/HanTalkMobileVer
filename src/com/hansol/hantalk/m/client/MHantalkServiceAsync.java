package com.hansol.hantalk.m.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MHantalkServiceAsync {
	void login(String id, String pwd, AsyncCallback<String> callback) throws Exception;
	void getAccountInfo(String session, String id, AsyncCallback<String> callback)  throws Exception;
	void getTimeline(String session, String offset, String length, AsyncCallback<String> callback) throws Exception;
	void getTimeline(String session, String offset, String length, String group, AsyncCallback<String> callback) throws Exception;
	void getUserTimeline(String session, String offset, String length, AsyncCallback<String> callback) throws Exception;
	void getSession(String id, String pwd, AsyncCallback<String> asyncCallback)throws Exception;
	void getReplyAll(String session, String postId, int maxReply, AsyncCallback<String> callback);
	void post(String session, String group, String text, String parent,	String via, AsyncCallback<String> callback);
	void getUserGroupList(String session, String offset, String length,
			String own, String sort, String sortkey, String char_index,
			AsyncCallback<String> callback);
	
}
