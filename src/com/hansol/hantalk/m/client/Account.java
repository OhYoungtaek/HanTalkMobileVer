package com.hansol.hantalk.m.client;

import com.google.gwt.core.client.JavaScriptObject;

public class Account extends JavaScriptObject {
	protected Account(){}
	
	public final native String getfavProfile() /*-{ return this.favProfile ; }-*/;
	public final native String getUserImageUrl(int i) 
	/*-{ return i==0?"http://hantalk.hansol.net/data-imgs/avatar/default/"+this.profileVoList[0].imgPath:
		"http://hantalk.hansol.net/data-imgs/avatar/user/"+this.profileVoList[1].imgPath ; }-*/; 
	public final native String getDeptName() /*-{ return this.deptName ; }-*/;
	public final native String getMobile() /*-{ return this.mobile ; }-*/;
	public final native String getPhone() /*-{ return this.phone ; }-*/;
	public final native String getPositionName() /*-{ return this.positionName ; }-*/;
	public final native String getEmail() /*-{ return this.email ; }-*/;
	public final native String getName() /*-{ return this.profileVoList[0].userName ; }-*/;
}
