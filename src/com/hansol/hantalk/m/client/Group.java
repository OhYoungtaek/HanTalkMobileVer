package com.hansol.hantalk.m.client;

import com.google.gwt.core.client.JavaScriptObject;

public class Group extends JavaScriptObject {
	
	protected Group(){}
	
	
	public final native String getUserGroupName() /*-{ return this.ugName ; }-*/;
	public final native String getUserGroupId() /*-{ return this.ugId ; }-*/;
	public final native String getUserGroupType() /*-{ return this.ugType ; }-*/;
	public final native String getUserGroupRole() /*-{ return this.ugUserRole == null? "null": "not null" ; }-*/;
	public final native String getImgPath() 
	/*-{ return this.imgPath == ""?"http://hantalk.hansol.net/default-imgs/usergroup-default.png"
		:"http://hantalk.hansol.net/data-imgs/usergroup/" + this.imgPath; }-*/;
	//http://hantalk.hansol.net/default-imgs/usergroup-default.png
	public final native String getLastPost() 
	/*-{ return (this.dtLastPost.year + 1900)+"/"+ (this.dtLastPost.month + 1) +
		"/" + this.dtLastPost.date; }-*/;
	
	public final native int getMemberCount()/*-{ return this.members ; }-*/;
	public final native int getPostCount()/*-{ return this.posts ; }-*/;
	public final native String getUserGroupDescription()/*-{ return this.ugDesc ; }-*/;
	
}
