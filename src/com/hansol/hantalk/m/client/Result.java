package com.hansol.hantalk.m.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class Result extends JavaScriptObject {
	protected Result(){}
	
	public final native String getAccount(int i ) /*-{ return i == 0 ?this.account.profileVoList[0].userName:this.account.profileVoList[1].userName; }-*/;
	public final native String getImgPath(int i) /*-{ return i == 0 ?
		"http://hantalk.hansol.net/data-imgs/avatar/default/" + this.account.profileVoList[0].imgPath:
		"http://hantalk.hansol.net/data-imgs/avatar/user/" + this.account.profileVoList[1].imgPath; }-*/;
	public final native String getPostText() /*-{ return this.postVo.postText ; }-*/;
	public final native int getPostId() /*-{ return this.postVo.postId ; }-*/;
	public final native String getUserGroupId() /*-{ return this.postVo.ugId ; }-*/;
	public final native String getUserGroupName() /*-{ return this.userGroupVo == null?"null":this.userGroupVo.ugName ; }-*/;
	public final native int getReplyCount() /*-{ return this.replyCount ; }-*/;
	
	public final native JsArray<Result> getLikerVo() /*-{ return this.likerVoList ; }-*/;
	public final native String getVia() /*-{ return this.postVo.via ; }-*/;
	public final native String getTime() /*-{ return ""+this.postVo.dtCreate.time; }-*/;

	public final native String getfavProfile() /*-{ return this.postVo.favProfile ; }-*/;
	
	public final native String getChildren(int i) /*-{ return this.children[i] == null ? "null" : "not null" }-*/;
	
	public final native String getChildrenAccount(int i) /*-{ return this.children[i].account.profileVoList[1] == null ?
		this.children[i].account.profileVoList[0].userName:this.children[i].account.profileVoList[1].userName; }-*/;
	public final native String getChildrenImgPath(int i) /*-{ return this.children[i].account.profileVoList[1] == null ?
		"http://hantalk.hansol.net/data-imgs/avatar/default/" + this.children[i].account.profileVoList[0].imgPath:
		"http://hantalk.hansol.net/data-imgs/avatar/user/" + this.children[i].account.profileVoList[1].imgPath; }-*/;
	public final native String getChildrenPostText(int i) /*-{ return this.children[i].postVo.postText; }-*/;
}
