package com.hansol.hantalk.m.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MHantalkServiceDelegate {
	private final MHantalkServiceAsync mHantalkService = GWT.create(MHantalkService.class);
	MHantalkGUI gui;
	
	private final native JsArray<Result> jsonTimelineArray(String json) /*-{
	  return eval(json);
	}-*/;
	
	private final native JsArray<Group> jsonGroupListArray(String json) /*-{
	  return eval(json);
	}-*/;
	
	private final native JsArray<Account> jsonAccountArray(String json) /*-{
	  return eval(json);
	}-*/;
	private final native JsArray<ChartList> jsonChartListArray(String json) /*-{
	  return eval(json);
	}-*/;
	
	void login(final String id, String pwd) throws Exception {
		
		mHantalkService.login(id, pwd, new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				gui.session = result;
				gui.setHeadPanel();
				gui.setBodyPanel();
				gui.setTailPanel();
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				caught.printStackTrace();
			}
		});
	}
	
	void getUserInfo(String session, String id) throws Exception{
		
		mHantalkService.getAccountInfo(session, id, new AsyncCallback<String>() {
			
			public void onSuccess(String result) {
				gui.service_UserInfo(jsonAccountArray(result).get(0));
			}
			
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
			}
		});
	}
	
	void getTimeline(String session, String offset, String length, final String group, final VerticalPanel panel, final VerticalPanel reply)throws Exception {
		
		mHantalkService.getTimeline(session, offset, length, group, new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				gui.service_Timeline(jsonTimelineArray(result), false, group, panel, reply);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				caught.printStackTrace();
			}
		});
	}
	
	void getUserTimeLine(String session, String offset, String length, final VerticalPanel panel, final VerticalPanel reply) throws Exception{
		
		mHantalkService.getUserTimeline(session, offset, length, new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				gui.service_Timeline(jsonTimelineArray(result), false, null, panel, reply);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	void getReplyAll(String session, String postId, int maxReply, final String group, final VerticalPanel panel, final VerticalPanel reply) throws Exception {
		mHantalkService.getReplyAll(session, postId, maxReply, new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				gui.service_Timeline(jsonTimelineArray(result), true, group, panel, reply);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				
			}
		});
	}
	
	void getUserGroupList(String session, String offset, String length, 
			String own, String sort, String sortkey, String char_index, final Boolean all,
			final VerticalPanel groupList, final VerticalPanel timeline, final VerticalPanel reply ) throws Exception {
		mHantalkService.getUserGroupList(session, offset, length, 
				own, sort, sortkey, char_index, new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				gui.service_GroupList(jsonGroupListArray(result), all, groupList, timeline, reply);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				caught.printStackTrace();
			}
		});
	}
	
	void post(String session, final String group, String text, final String parent, String via,
			final VerticalPanel panel) throws Exception {
		mHantalkService.post(session, group, text, parent, via, new AsyncCallback<String>() {
			public void onSuccess(String result) {
				if ( parent.equals("-1"))
					gui.service_Post(jsonTimelineArray(result), false, group, panel);
				else 
					gui.service_Post(jsonTimelineArray(result), true, group, panel);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
			}
		});
	}
	
	void getChartList(String session, final String chart_kind, String period,
			final VerticalPanel listPanel, final VerticalPanel replyPanel) throws Exception {
		mHantalkService.getChartList(session, chart_kind, period, new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				gui.service_CharList(jsonChartListArray(result), chart_kind, listPanel,replyPanel);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				caught.printStackTrace();
			}
		});
	}
	
	void getPost(String session, final String post_id, final VerticalPanel listPanel, final VerticalPanel replyPanel) throws Exception {
		mHantalkService.getPost(session, post_id, new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
				System.out.println(result);
				System.out.println("getpostsuccess");
				
				gui.service_GetPost(jsonTimelineArray(result), listPanel, replyPanel);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
				caught.printStackTrace();
				System.out.println("fail");
			}
		});
	}
	
	void logout(String session) throws Exception {
		mHantalkService.logout(session, new AsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				// TODO Auto-generated method stub
				System.out.println("logout ok");
				gui.session = null;
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				System.out.println("logout fail");
				
			}
		});
	}
}
