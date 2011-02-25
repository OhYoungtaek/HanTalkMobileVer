package com.hansol.hantalk.m.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("mHantalk")
public interface MHantalkService extends RemoteService{
	String login(String id, String pwd) throws Exception;
	String getAccountInfo(String session, String id)  throws Exception;
	
	String getTimeline(String session, String offset, String length) throws Exception;
	String getTimeline(String session, String offset, String length, String group) throws Exception;
	String getUserTimeline(String session, String offset, String length) throws Exception;
	String getSession(String id, String pwd) throws Exception;
	String getReplyAll(String session, String postId, int maxReply) throws Exception;
	String getUserGroupList(String session, String offset, String length, String own, String sort, String sortkey, String char_index)  throws Exception;
	String post(String session, String group, String text, String parent, String via) throws Exception;
	String getChartList(String session, String chart_kind, String period) throws Exception;
	String getPost(String session, String postId) throws Exception;
	void logout(String session) throws Exception;
}
