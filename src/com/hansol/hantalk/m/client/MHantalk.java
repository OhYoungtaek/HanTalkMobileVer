package com.hansol.hantalk.m.client;

import com.google.gwt.core.client.EntryPoint;

public class MHantalk implements EntryPoint {
	
	private MHantalkGUI gui;
	private MHantalkServiceDelegate delegate;
	
	public void onModuleLoad() {
		gui = new MHantalkGUI();
		delegate = new MHantalkServiceDelegate();
		gui.mHantalkService = delegate;
		delegate.gui = gui;
		gui.init();
	}
	
}
