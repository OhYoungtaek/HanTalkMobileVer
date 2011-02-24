package com.hansol.hantalk.m.client;


import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("deprecation")
public class MHantalkGUI {
	
	/* Constants */
	protected String session;
	private int windowWidth;
	private int timelineIndex;
	private boolean postingFlag = false;
	private VerticalPanel currentPanel = null;
	private String currentGroup = "home";
	private String currentPostId = "-1";
	private String userId;
	private String userImageUrl="읭??";
	/* GUI widgets */
	private DecoratorPanel loginPanel;
	
	private VerticalPanel headPanel;
	private Image homeIcon, userIcon, groupIcon, logoutIcon;
	
	private VerticalPanel bodyPanel;
	private TabPanel homeTabPanel;
	private VerticalPanel inAllPanel, inManyReplyPanel, inLikeReplyPanel;
	private TabPanel userTabPanel;
	private VerticalPanel inUserPanel, inMyPostPanel;
	private TabPanel groupTabPanel;
	private VerticalPanel inAllGroupTabPanel, inMyGroupTabPanel;
	
	private VerticalPanel tailPanel;
	
	/* Data Model */
	protected MHantalkServiceDelegate mHantalkService;
	private int groupListIndex;

	public void init(){
		//생성
		timelineIndex = 0;
		loginPanel = new DecoratorPanel();
		headPanel = new VerticalPanel();
		bodyPanel = new VerticalPanel();
		tailPanel = new VerticalPanel();
		homeTabPanel = new TabPanel();
		userTabPanel = new TabPanel();
		groupTabPanel = new TabPanel();
		inAllPanel = new VerticalPanel();
		inManyReplyPanel = new VerticalPanel();
		inLikeReplyPanel = new VerticalPanel();
		inMyPostPanel = new VerticalPanel();
		inAllGroupTabPanel = new VerticalPanel();
		inMyGroupTabPanel = new VerticalPanel();
		inUserPanel = new VerticalPanel();
		
		placeWidget();
	}


	public void placeWidget(){
		setLoginPanel();
		
		windowWidth = Window.getClientWidth();
		System.out.println(windowWidth);
		
		Window.addResizeHandler(new ResizeHandler() {
			
			public void onResize(ResizeEvent event) {
//				windowWidth = Integer.toString(event.getWidth());
//				windowHeight = Integer.toString(event.getHeight());
				windowWidth = event.getWidth();
//				windowHeight = event.getHeight();
				
				headPanel.setSize(Integer.toString(event.getWidth()-30),"10");
				bodyPanel.setSize(Integer.toString(event.getWidth()-30),"10");
				homeTabPanel.setSize(Integer.toString(event.getWidth()-30),"10");
				userTabPanel.setSize(Integer.toString(event.getWidth()-30),"10");
				groupTabPanel.setSize(Integer.toString(event.getWidth()-30),"10");
				tailPanel.setSize(Integer.toString(event.getWidth()-30),"10");
//				inAllPanel.setSize(Integer.toString(event.getWidth()-30),"10");
//				inManyReplyPanel.setSize(Integer.toString(event.getWidth()-30),"10");
//				inLikeReplyPanel.setSize(Integer.toString(event.getWidth()-30),"10");
//				inMyPostPanel.setSize(Integer.toString(event.getWidth()-30),"10");
//				inAllGroupTabPanel.setSize(Integer.toString(event.getWidth()-30),"10");
//				inMyGroupTabPanel.setSize(Integer.toString(event.getWidth()-30),"10");
//				inUserPanel.setSize(Integer.toString(event.getWidth()-30),"10");
			}
		});
		
		headPanel.setSize("350","10");
		bodyPanel.setSize("350","10");
		homeTabPanel.setSize("350","10");
		userTabPanel.setSize("330","10");
		groupTabPanel.setSize("330","10");
		tailPanel.setSize("350","10");
		inAllPanel.setSize("330","10");
		inManyReplyPanel.setSize("330","10");
		inLikeReplyPanel.setSize("330","10");
		inMyPostPanel.setSize("330","10");
		inAllGroupTabPanel.setSize("330","10");
		inMyGroupTabPanel.setSize("330","10");
		inUserPanel.setSize("330","10");
		
		headPanel.setVisible(false);
		bodyPanel.setVisible(false);
		tailPanel.setVisible(false);
		loginPanel.setVisible(true);
		
		RootPanel.get("login").add(loginPanel);
		RootPanel.get("head").add(headPanel);
		RootPanel.get("body").add(bodyPanel);
		RootPanel.get("tail").add(tailPanel);
		
		
	}
	
	public void setLoginPanel() {
		// TODO Auto-generated method stub
		FlexTable tempTable = new FlexTable();
		Image image = new Image("http://hantalk.hansol.net/static/images/hans_logo.png");
		final TextBox id = new TextBox();
		final PasswordTextBox password = new PasswordTextBox();
		Label loginLabel = new Label("계정 정보를 입력하세요.");
		
		
		FlexTable loginTable = new FlexTable();
		id.setText("karroo");
		password.setText("111111");
		Button login = new Button("LOGIN");
		
		loginTable.setText(0, 0, "ID");
		loginTable.setWidget(0, 1, id);
		loginTable.setText(1, 0, "PW");
		loginTable.setWidget(1, 1, password);
		
		tempTable.setWidget(0,0,image);
		tempTable.setWidget(1,0,loginLabel);
		tempTable.setWidget(2,0,loginTable);
		tempTable.setWidget(3,0,login);
		
		loginPanel.add(tempTable);
		
		tempTable.setStyleName("tempTable");
		loginLabel.setStyleName("loginLabel");
		loginTable.setStyleName("loginTable");
		id.setStyleName("loginField");
		password.setStyleName("loginField");
		login.setStyleName("loginButton");
		loginLabel.setStyleName("loginLabel");
		
		login.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				userId = id.getValue();
				try {
					mHantalkService.login(id.getValue(), password.getValue());
					userId = id.getValue();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				headPanel.setVisible(true);
				bodyPanel.setVisible(true);
				tailPanel.setVisible(true);
				loginPanel.setVisible(false);
			}
		});
		
	}


	public void setHeadPanel(){
		Image headImage = new Image("http://hantalk.hansol.net/static/images/hans_logo.png");
		FlowPanel flowLabel = new FlowPanel();
		homeIcon = new Image("home.gif");
		userIcon = new Image("user.gif");
		groupIcon = new Image("group.gif");
		logoutIcon = new Image("logout.gif");
		homeIcon.setStyleName("homeIcon");
		userIcon.setStyleName("iconImage");
		groupIcon.setStyleName("iconImage");
		logoutIcon.setStyleName("iconImage");
		
		headPanel.add(headImage);
		flowLabel.add(homeIcon);
		flowLabel.add(userIcon);
		flowLabel.add(groupIcon);
		flowLabel.add(logoutIcon);
		headPanel.add(flowLabel);
		
		homeIcon.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				currentGroup = "home";
				homeTabPanel.setVisible(true);
				userTabPanel.setVisible(false);
				groupTabPanel.setVisible(false);
			}
		});
		
		userIcon.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				homeTabPanel.setVisible(false);
				userTabPanel.setVisible(true);
				groupTabPanel.setVisible(false);
			}
		});
		
		groupIcon.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				homeTabPanel.setVisible(false);
				userTabPanel.setVisible(false);
				groupTabPanel.setVisible(true);
			}
		});
		logoutIcon.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				
				headPanel.setVisible(false);
				bodyPanel.setVisible(false);
				tailPanel.setVisible(false);
				loginPanel.setVisible(false);
				init();
			}
		});
	}
	
	public void setBodyPanel(){
		setHomeTabPanel();
		setUserTabPanel();
		setGroupTabPanel();
	}
	
	public void setTailPanel(){
		//final Label tail = new Label("Mobile Hantalk" + Window.getClientWidth() +"/"+Window.getClientHeight());
		final Label tail = new Label("Mobile Hantalk");
		Window.addResizeHandler(new ResizeHandler() {
			
			@Override
			public void onResize(ResizeEvent event) {
				tail.setText("Mobile Hantalk " + event.getWidth() + "/"+ windowWidth);
				
				
			}
		});
		tailPanel.add(tail);
	}
	
	public void setHomeTabPanel(){
		 
		final VerticalPanel timelinePanel = getAllPanel();
		final VerticalPanel inReplyPanel = new VerticalPanel();
	
		inAllPanel.add(timelinePanel);
		inAllPanel.add(inReplyPanel);
		
		homeTabPanel.add(inAllPanel, "전체");
		
		inManyReplyPanel.add(new Label("Insert Panel 2 Here"));
		homeTabPanel.add(inManyReplyPanel, "금주 댓글 많은 글");
		
		inLikeReplyPanel.add(new Label("Insert Panel 3 Here"));
		homeTabPanel.add(inLikeReplyPanel, "금주 좋아요 많은 글");
		
		homeTabPanel.selectTab(0);
		bodyPanel.add(homeTabPanel);
		
		try {
			mHantalkService.getTimeline(session, "0", "10", null, timelinePanel, inReplyPanel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		homeTabPanel.addSelectionHandler(new SelectionHandler<Integer>() {
			public void onSelection(SelectionEvent<Integer> event) {
				timelineIndex = 0;
				if ( event.getSelectedItem() == 0){
					timelinePanel.clear();
					timelinePanel.add(getPostPanel(false));
					timelinePanel.setVisible(true);
					inReplyPanel.setVisible(false);
					try {
						mHantalkService.getTimeline(session, "0", "10", null, timelinePanel, inReplyPanel);
					} catch (Exception e) {
						e.printStackTrace();
						}
//					}
				}
			}
		});	
		
		homeIcon.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				homeTabPanel.selectTab(0);
				timelinePanel.clear();
				timelinePanel.add(getPostPanel(false));
				timelinePanel.setVisible(true);
				inReplyPanel.setVisible(false);
				try {
					mHantalkService.getTimeline(session, "0", "10", null, timelinePanel, inReplyPanel);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setUserTabPanel(){
	
		userTabPanel.add(inUserPanel, "내 정보");
		
		final VerticalPanel timelinePanel = getAllPanel();
		final VerticalPanel inReplyPanel = new VerticalPanel();
	
		inMyPostPanel.add(timelinePanel);
		inMyPostPanel.add(inReplyPanel);
		
		userTabPanel.add(inMyPostPanel, "내가 쓴 글");
		
		userTabPanel.addSelectionHandler(new SelectionHandler<Integer>() {
			
			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				// TODO Auto-generated method stub
				if ( event.getSelectedItem() == 0){
					inUserPanel.setVisible(true);
					inMyPostPanel.setVisible(false);
					try {
						mHantalkService.getUserInfo(session, userId);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					timelineIndex = 0;
					inUserPanel.setVisible(false);
					inMyPostPanel.setVisible(true);
					timelinePanel.clear();
					timelinePanel.add(getPostPanel(false));
					try {
						mHantalkService.getUserTimeLine(session, "0", "10", timelinePanel, inReplyPanel);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		userIcon.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				userTabPanel.selectTab(1);
				timelineIndex = 0;
				inUserPanel.setVisible(false);
				inMyPostPanel.setVisible(true);
				timelinePanel.clear();
				timelinePanel.add(getPostPanel(false));
				try {
					mHantalkService.getUserTimeLine(session, "0", "10", timelinePanel, inReplyPanel);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		userTabPanel.setVisible(false);
		userTabPanel.selectTab(1);
		
		bodyPanel.add(userTabPanel);
	
	}
	public void setGroupTabPanel(){

		final VerticalPanel allGroupListPanel = getGroupListPanel(true);
		final VerticalPanel timelinePanel = new VerticalPanel();
		final VerticalPanel inReplyPanel = new VerticalPanel();
		
		inAllGroupTabPanel.add(allGroupListPanel);
		inAllGroupTabPanel.add(timelinePanel);
		inAllGroupTabPanel.add(inReplyPanel);
		
		groupTabPanel.add(inAllGroupTabPanel, "전체 그룹");
		
		final VerticalPanel myGroupListPanel = getGroupListPanel(true);
		final VerticalPanel myTimelinePanel = new VerticalPanel();
		final VerticalPanel myInReplyPanel = new VerticalPanel();
		
		inMyGroupTabPanel.add(myGroupListPanel);
		inMyGroupTabPanel.add(myTimelinePanel);
		inMyGroupTabPanel.add(myInReplyPanel);
		
		groupTabPanel.add(inMyGroupTabPanel, "내 그룹");
		
		groupTabPanel.selectTab(1);
		
		groupTabPanel.addSelectionHandler(new SelectionHandler<Integer>() {
			
			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				
				groupListIndex = 0;
				
				if ( event.getSelectedItem() == 0){
					allGroupListPanel.setVisible(true);
					timelinePanel.setVisible(true);
					allGroupListPanel.clear();
					timelinePanel.clear();
					allGroupListPanel.add(getGroupListPanel(false));
					try {
						mHantalkService.getUserGroupList(session, "0", "10", "false", "desc", 
								"dt_last_post", null, true, allGroupListPanel, timelinePanel, inReplyPanel);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					myGroupListPanel.setVisible(true);
					myTimelinePanel.setVisible(true);
					myGroupListPanel.clear();
					myTimelinePanel.clear();
					myGroupListPanel.add(getGroupListPanel(true));
					try {
						mHantalkService.getUserGroupList(session, "0", "10", "true", "desc", 
								"dt_last_post", null, false, myGroupListPanel, myTimelinePanel, myInReplyPanel);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		groupIcon.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				groupTabPanel.selectTab(1);
				try {
					mHantalkService.getUserGroupList(session, "0", "10", "true", "desc", 
							"dt_last_post", null, false, myGroupListPanel, myTimelinePanel, myInReplyPanel);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		groupTabPanel.setVisible(false);
		bodyPanel.add(groupTabPanel);
	}
	
	public VerticalPanel getAllPanel(){
		VerticalPanel allPanel = new VerticalPanel();
		final HorizontalPanel inPostPanel = getPostPanel(false);
		allPanel.add(inPostPanel);
		return allPanel;
	}
	
	public HorizontalPanel getPostPanel(final boolean reply){
		HorizontalPanel panel = new HorizontalPanel();
		VerticalPanel postPanel = new VerticalPanel();
		
//		Image userImg = new Image(userImageUrl);
//		System.out.println(">> "+ userImageUrl);
//		userImg.setStyleName("userImage");
//		panel.add(userImg);
		
		final FlowPanel buttonPanel = new FlowPanel();
		
		final InlineLabel numOfString = new InlineLabel("140");
		Button post	= new Button("공유하기");
		Button cancel = new Button("취소");
		final TextBox postTextBox = new TextBox();
		final TextArea postTextArea = new TextArea();
		postTextArea.setVisible(false);
	
		if (reply == true)
			postTextBox.setText("댓글을 기다려요");
		else
			postTextBox.setText("나누고 싶은 Hantalk?");
		
		postPanel.add(postTextBox);
		postPanel.add(postTextArea);
		buttonPanel.add(post);
		buttonPanel.add(cancel);
		buttonPanel.add(numOfString);
		buttonPanel.add(new Label());
		buttonPanel.setVisible(false);
		
		postPanel.add(buttonPanel);
		panel.add(postPanel);
		
		postTextArea.setStyleName("postArea");
		post.setStyleName("postButton");
		cancel.setStyleName("postCancelButton");
		postTextBox.setStyleName("postTextBox");
		postTextBox.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				postingFlag = true;
//				postTextBox.setText("");
//				postTextBox.setStyleName("clickedPostTextBox");
				postTextBox.setVisible(false);
				postTextArea.setVisible(true);
				buttonPanel.setVisible(true);
				numOfString.setText("140");
			}
		});
		
		postTextArea.addKeyboardListener(new KeyboardListener() {
			
			@Override
			public void onKeyUp(Widget sender, char keyCode, int modifiers) {
				// TODO Auto-generated method stub
				numOfString.setText(returnCountString(postTextArea.getText().length()));
			}
			
			@Override
			public void onKeyPress(Widget sender, char keyCode, int modifiers) {
				// TODO Auto-generated method stub
				numOfString.setText(returnCountString(postTextArea.getText().length()));
			}
			
			@Override
			public void onKeyDown(Widget sender, char keyCode, int modifiers) {
				// TODO Auto-generated method stub
				numOfString.setText(returnCountString(postTextArea.getText().length()));
			}
		});
		
		post.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				if ( postTextArea.getText().length() > 140){
					numOfString.setText("140자 초과");
				} else {
					try {
						if (reply == true)
							mHantalkService.post(session, currentGroup, postTextArea.getText(), currentPostId, "WEB",  currentPanel);
						else if (reply == false)
							mHantalkService.post(session, currentGroup, postTextArea.getText(), "-1", "WEB", currentPanel);
					} catch (Exception e) {
						// TODO: handle exception
					}
					postingFlag = false;
					postTextArea.setText("");
					postTextBox.setVisible(true);
					postTextArea.setVisible(false);
					buttonPanel.setVisible(false);
				}
			}
		});
		
		cancel.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				postingFlag = false;
				postTextArea.setText("");
//				if (reply == true)
//					postTextBox.setText("댓글을 기다려요");
//				else
//					postTextBox.setText("나누고 싶은 Hantalk?");
//				postTextBox.setSize(Integer.toString(windowWidth-40), "15");
				postTextBox.setVisible(true);
				postTextArea.setVisible(false);
				buttonPanel.setVisible(false);
			}
		});
		
		return panel;
	}
	
	public VerticalPanel getGroupListPanel(final boolean all){
		VerticalPanel tempPanel = new VerticalPanel();
		FlowPanel topGroupIndex = new FlowPanel();
		InlineLabel index_0 = new InlineLabel("모두");
		InlineLabel index_1 = new InlineLabel("A-G");
		InlineLabel index_2 = new InlineLabel("H-N");
		InlineLabel index_3 = new InlineLabel("O-T");
		InlineLabel index_4 = new InlineLabel("U-Z");
		InlineLabel index_5 = new InlineLabel("가-다");
		InlineLabel index_6 = new InlineLabel("라-바");
		InlineLabel index_7 = new InlineLabel("사-차");
		InlineLabel index_8 = new InlineLabel("카-하");
		topGroupIndex.add(index_0);
		topGroupIndex.add(new InlineLabel(" | "));
		topGroupIndex.add(index_1);
		topGroupIndex.add(new InlineLabel(" | "));
		topGroupIndex.add(index_2);
		topGroupIndex.add(new InlineLabel(" | "));
		topGroupIndex.add(index_3);
		topGroupIndex.add(new InlineLabel(" | "));
		topGroupIndex.add(index_4);
		topGroupIndex.add(new InlineLabel(" | "));
		topGroupIndex.add(index_5);
		topGroupIndex.add(new InlineLabel(" | "));
		topGroupIndex.add(index_6);
		topGroupIndex.add(new InlineLabel(" | "));
		topGroupIndex.add(index_7);
		topGroupIndex.add(new InlineLabel(" | "));
		topGroupIndex.add(index_8);
		tempPanel.add(topGroupIndex);
		
		return tempPanel;
	}
	
	public String checkNoImg(String Url){
		if (Url.equals("http://hantalk.hansol.net/data-imgs/avatar/default/") | Url.equals("http://hantalk.hansol.net/data-imgs/avatar/user/"))
			return "http://hantalk.hansol.net/default-imgs/avatar-default.png";
		else
			return Url;
	}
	
	public void service_GroupList(JsArray<Group> array, final boolean all, 
			final VerticalPanel groupListPanel, final VerticalPanel timelinePanel, final VerticalPanel replyPanel){
		final FlexTable groupListTable = new FlexTable();
		
		for(int i=0; i<array.length();i++){
			final Group result = array.get(i);
			HorizontalPanel inGroupListRowPanel = new HorizontalPanel();
			VerticalPanel groupListRowPanel = new VerticalPanel();
			Image image = new Image(result.getImgPath());
			image.setStyleName("listGroupImage");
			inGroupListRowPanel.add(image);
			VerticalPanel groupNamePanel = new VerticalPanel();
			Label groupName = new Label();
			if ( result.getUserGroupId().equals("0") == false)
				groupName = new Label(result.getUserGroupName());
			Label groupMember = new Label("멤버수 : "+result.getMemberCount());
			Label groupPost = new Label("글수: "+ result.getPostCount());
			Label groupLastPost = new Label("마지막 갱신일: "+result.getLastPost());
			
			groupName.setStyleName("groupNameLabel");
			Image rowBar = new Image("rowBar.gif");
			rowBar.setStyleName("bar");
			
			if ( result.getUserGroupType().equals("CLOSE") == true)
				groupNamePanel.add(new Image("http://hantalk.hansol.net/static/images/ico_key.png"));
			
			groupNamePanel.add(groupName);
			groupNamePanel.add(groupMember);
			groupNamePanel.add(groupPost);
			groupNamePanel.add(groupLastPost);
			inGroupListRowPanel.add(groupNamePanel);
			groupListRowPanel.add(inGroupListRowPanel);
			groupListRowPanel.add(new Label(result.getUserGroupDescription()));
			groupListRowPanel.add(rowBar);	//바 추가
			groupListTable.setWidget(i, 0, groupListRowPanel);
	
			
			groupName.addClickHandler(new ClickHandler() {
				
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					if ( result.getUserGroupRole().equals("not null") == true 
							|| result.getUserGroupType().equals("OPEN") == true ){
						currentGroup = result.getUserGroupId();
						groupListPanel.setVisible(false);
						timelinePanel.setVisible(true);
						try {
							timelinePanel.add(getPostPanel(false));
							mHantalkService.getTimeline(session, "0", "10", result.getUserGroupId(), timelinePanel, replyPanel);

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			
		}
		
		if ( all == true)
			groupListPanel.add(groupListTable);
		else
			groupListPanel.add(groupListTable);
		
		if (array.length() == 10) {
			groupListIndex += array.length(); 
			final Button extend = new Button("더보기");
			extend.setStyleName("extendButton");
			//extend.setSize(Integer.toString(windowWidth-30), "20");
			
			groupListTable.setWidget(array.length(),0,extend);
			
			extend.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					extend.setVisible(false);
					try {
						if (all == true){
							mHantalkService.getUserGroupList(session, Integer.toString(groupListIndex), "10", "false", "desc", "dt_last_post", null, true, groupListPanel, timelinePanel, replyPanel);
						} else {
							mHantalkService.getUserGroupList(session, Integer.toString(groupListIndex), "10", "true", "desc", "dt_last_post", null, false, groupListPanel, timelinePanel, replyPanel);
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
		}
		
	}
	
	public void service_Timeline(JsArray<Result> array, boolean reply, final String group, 
			final VerticalPanel timelinePanel, final VerticalPanel replyPanel){
		
		final FlexTable timelineTable = new FlexTable();
		
		for(int i=0; i<array.length(); i++){
			final Result result = array.get(i);
			
			/*Initialize Variables*/
			HorizontalPanel timelineRowPanel = new HorizontalPanel();
			HorizontalPanel timelineContentPanel = new HorizontalPanel();
			FlowPanel timelineTextPanel = new FlowPanel();
			InlineLabel groupLabel = new InlineLabel();
			if ( result.getUserGroupId().equals("0") == false && group == null)
				groupLabel = new InlineLabel("["+result.getUserGroupName());
			InlineLabel groupLabelText = new InlineLabel(" 그룹에서 작성 됨.]");
			InlineLabel nameLabel;
			
/////////////////////////////http link section/////////////////////////////////////////////////////////////////////////////////////////
			FlowPanel postTextPanel = new FlowPanel();
			FlexTable postTextTable = new FlexTable();
			InlineLabel textLabel;
			InlineLabel hyperlink;
			
			String[] postTextArr = postProcess(result.getPostText());
			String[] hyperlinkArr = getHyperlinkDir(result.getPostText());
			
			for(int j=0; j<10; j=j+2){
				textLabel = new InlineLabel();
				hyperlink = new InlineLabel();
				textLabel.setStyleName("postLabel");
				hyperlink.setStyleName("hyperlink");
				
				final String url = hyperlinkArr[j/2];
				if(url.equals("x")){
					hyperlink.setText("");
				}
				else{
					hyperlink.setText(url);
				}
				textLabel.setText(postTextArr[j/2]);
				
				postTextTable.setWidget(j, 0, textLabel);
				postTextTable.setWidget(j+1, 0, hyperlink);
				
				hyperlink.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						Window.open(url, url, null);
					}
				});
			}
			for(int j=0; j<5; j++){
				postTextPanel.add(postTextTable.getWidget(j, 0));
			}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			InlineLabel timeLabel = new InlineLabel(timeProcess(result.getTime()) + ", ");
			InlineLabel viaLabel = new InlineLabel(result.getVia()+"에서 ");
			InlineLabel likeLabel = new InlineLabel(" " + result.getLikerVo().length() + " 명이 좋아합니다. ");
			
			/*Set Properties*/
			Image likeImg = new Image("http://hantalk.hansol.net/static/images/ico_good.png");
			Image img;
			if ( result.getfavProfile().equals("USER")){
				img = new Image(checkNoImg(result.getImgPath(1)));
				nameLabel = new InlineLabel(result.getAccount(1) + " ");
			} else {
				img = new Image(result.getImgPath(0));
				nameLabel = new InlineLabel(result.getAccount(0) + " ");
			}
			img.setStyleName("userImage");
			
			/*Add To Panel*/
			timelineContentPanel.add(img);
			if ( result.getUserGroupId().equals("0") == false && group == null && reply == false){
				timelineTextPanel.add(groupLabel);
				timelineTextPanel.add(groupLabelText);
				timelineTextPanel.add(new Label());
			}
			
			timelineTextPanel.add(nameLabel);
			timelineTextPanel.add(postTextPanel);
			timelineTextPanel.add(new Label());
			timelineTextPanel.add(timeLabel);
			timelineTextPanel.add(viaLabel);
			
			timeLabel.setStyleName("greenLabel");
			viaLabel.setStyleName("greenLabel");
			nameLabel.setStyleName("blueLabel");
			
			timelineTextPanel.add(likeImg);
			if ( result.getLikerVo().length() > 1)
				timelineTextPanel.add(likeLabel);
			
			if ( reply == false){
				InlineLabel replyLabel = new InlineLabel();
				if ( result.getReplyCount() > 0) {
					replyLabel.setText(" " + result.getReplyCount()+ " 개의 댓글");
					timelineTextPanel.add(replyLabel);
				} else {
					replyLabel.setText(" 댓글 쓰기");
					timelineTextPanel.add(replyLabel);
				}
				
				replyLabel.addClickHandler(new ClickHandler() {
					
					public void onClick(ClickEvent event) {
						currentPostId = Integer.toString(result.getPostId());
						currentPanel = replyPanel;
						replyPanel.clear();
						timelinePanel.setVisible(false);
						replyPanel.setVisible(true);				
						setReplyPanel(result , group, timelinePanel, replyPanel);
					}
				});
			}
			
			VerticalPanel ver_tempPanel = new VerticalPanel();
			Image rowBar = new Image("rowBar.gif");
			rowBar.setStyleName("bar");
			
			timelineRowPanel.add(timelineContentPanel);
			timelineRowPanel.add(timelineTextPanel);
			ver_tempPanel.add(timelineRowPanel);
			ver_tempPanel.add(rowBar); //바 추가
			
			/*Update Table*/
			timelineTable.setWidget(i, 0, ver_tempPanel);
	
		}
		
		System.out.println("check");
		
		timelinePanel.add(timelineTable);
		if ( reply ) {
			timelinePanel.add(getPostPanel(true));
		}
		if (array.length() == 10 && reply == false) {
			timelineIndex += array.length(); 
			final Button extend = new Button("더보기");
			extend.setStyleName("extendButton");
			//extend.setSize(Integer.toString(windowWidth-30), "20");
			timelineTable.setWidget(array.length(),0,extend);
			
			extend.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					extend.setVisible(false);
					try {
						mHantalkService.getTimeline(session, Integer.toString(timelineIndex), "10", group, timelinePanel, replyPanel);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
		}
	}
	
	void setReplyPanel(final Result result, final String group, final VerticalPanel timelinePanel, final VerticalPanel replyPanel){
		
		final FlexTable tempTable = new FlexTable();
		
		HorizontalPanel timelineRowPanel = new HorizontalPanel();
		HorizontalPanel timelineContentPanel = new HorizontalPanel();
		FlowPanel timelineTextPanel = new FlowPanel();
		InlineLabel groupLabel = new InlineLabel();
		InlineLabel groupLabelText = new InlineLabel();
		if ( result.getUserGroupId().equals("0") == false && group == null){
			groupLabel = new InlineLabel("[" +result.getUserGroupName());
			groupLabelText = new InlineLabel(" 그룹에서 작성 됨.]");
		}
		InlineLabel nameLabel;
/////////////////////////////////////////////////////////////////////////////
		FlowPanel postTextPanel = new FlowPanel();
		FlexTable postTextTable = new FlexTable();
		InlineLabel textLabel;
		InlineLabel hyperlink;
		
		String[] postTextArr = postProcess(result.getPostText());
		String[] hyperlinkArr = getHyperlinkDir(result.getPostText());
		
		for(int j=0; j<10; j=j+2){
			textLabel = new InlineLabel();
			hyperlink = new InlineLabel();
			textLabel.setStyleName("postLabel");
			hyperlink.setStyleName("hyperlink");
			
			final String url = hyperlinkArr[j/2];
			if(url.equals("x")){
				hyperlink.setText("");
			}
			else{
				hyperlink.setText(url);
			}
			textLabel.setText(postTextArr[j/2]);
			
			postTextTable.setWidget(j, 0, textLabel);
			postTextTable.setWidget(j+1, 0, hyperlink);
			
			hyperlink.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					Window.open(url, url, null);
				}
			});
		}
		for(int j=0; j<5; j++){
			postTextPanel.add(postTextTable.getWidget(j, 0));
		}
		/////////////////////////////////////////////////////////////////////////////////////
		InlineLabel timeLabel = new InlineLabel(timeProcess(result.getTime())+", ");
		InlineLabel viaLabel = new InlineLabel(result.getVia()+"에서 ");
		InlineLabel likeLabel = new InlineLabel(" " + result.getLikerVo().length() + " 명이 좋아합니다. ");
		
		/*Set Properties*/
		timeLabel.setStyleName("greenLabel");
		viaLabel.setStyleName("greenLabel");
		Image likeImg = new Image("http://hantalk.hansol.net/static/images/ico_good.png");
		Image img;
		if ( result.getfavProfile().equals("USER")){
			img = new Image(checkNoImg(result.getImgPath(1)));
			nameLabel = new InlineLabel(result.getAccount(1) + " ");
		} else {
			img = new Image(result.getImgPath(0));
			nameLabel = new InlineLabel(result.getAccount(0) + " ");
		}
		
		nameLabel.setStyleName("blueLabel");
		img.setStyleName("userImage");
		img.addClickListener(new ClickListener() {
			
			@Override
			public void onClick(Widget sender) {
				// TODO Auto-generated method stub
				timelinePanel.setVisible(true);
				if ( replyPanel != null) {
					replyPanel.setVisible(false);
					replyPanel.clear();
				}
				currentPanel.clear();
			}
		});
		
		/*Add To Panel*/
		timelineContentPanel.add(img);
		if ( groupLabel.getText().equals("[null") == false){
			timelineTextPanel.add(groupLabel);
			timelineTextPanel.add(groupLabelText);
			timelineTextPanel.add(new Label());
		}
		timelineTextPanel.add(nameLabel);
		timelineTextPanel.add(postTextPanel);
		timelineTextPanel.add(new Label());
		timelineTextPanel.add(timeLabel);
		timelineTextPanel.add(viaLabel);
		
		timelineTextPanel.add(likeImg);
		if ( result.getLikerVo().length() > 1)
			timelineTextPanel.add(likeLabel);
		
		VerticalPanel ver_tempPanel = new VerticalPanel();
		Image rowBar = new Image("rowBar.gif");
		rowBar.setStyleName("bar");
		
		timelineRowPanel.add(timelineContentPanel);
		timelineRowPanel.add(timelineTextPanel);
		ver_tempPanel.add(timelineRowPanel);
		ver_tempPanel.add(rowBar);
		tempTable.setWidget(0, 0, ver_tempPanel);
		
//		textLabel.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				// TODO Auto-generated method stub
//				replyPanel.clear();
//				currentPanel = replyPanel;
//				try {
//					mHantalkService.getReplyAll(session, Integer.toString(result.getPostId()), result.getReplyCount(), group, replyPanel, null );
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
		
		try {
			mHantalkService.getReplyAll(session, Integer.toString(result.getPostId()), result.getReplyCount(), group, replyPanel, null );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		replyPanel.add(tempTable);
			
	}


	public void service_UserInfo(Account account) {
		// TODO Auto-generated method stub
		inUserPanel.clear();
		HorizontalPanel tempPanel = new HorizontalPanel();
		Image userImage = new Image();
		String url = "";
		if (account.getfavProfile().equals("USER"))
			url = checkNoImg(account.getUserImageUrl(1));
		else
			url = checkNoImg(account.getUserImageUrl(0));
		userImage.setUrl(url);
		userImage.setStyleName("bigUserImage");
		tempPanel.add(userImage);
		
		FlowPanel userInfoPanel = new FlowPanel();
		userInfoPanel.add(new Label(account.getDeptName()));
		userInfoPanel.add(new InlineLabel(account.getName() + " "));
		userInfoPanel.add(new InlineLabel(account.getPositionName()));
		userInfoPanel.add(new Label(account.getPhone()));
		userInfoPanel.add(new Label(account.getMobile()));
		userInfoPanel.add(new Label(account.getEmail()));
		
		tempPanel.add(userInfoPanel);
		inUserPanel.add(tempPanel);
	}
	
	public String returnCountString(int numOfText){
		if ( numOfText < 10) {
			return Integer.toString(140 - numOfText); 
		} else if ( numOfText >= 10 && numOfText < 100)
			return " " + Integer.toString(140 - numOfText); 
		else if ( numOfText > 140)
			return "140자 초과";
		else
			return "  " + Integer.toString(140 - numOfText); 
			
	}
	public String timeProcess(String time){	//time <- result.getTime();
		
		String result;
		long post_time = Long.parseLong(time)/1000;
		long current_time = System.currentTimeMillis()/1000;
		long sub = current_time - post_time;
		
		if(sub/60 >=0 && sub/60 < 1){
			sub = sub%60;
			result = sub + "초전";
		}
		else{
			sub/=60;
			if(sub < 60){
				result = sub + "분전";
			}
			else{
				sub/=60;
				if(sub<24){
					result=sub + "시간전";
				}
				else{
					sub/=24;
					if(sub<30){
						result=sub + "일전";
					}
					else{
						sub/=30;
						result=sub + "개월전";
					}
				}
			}
		}
		return result;
	}


	public void service_Post(JsArray<Result> jsonTimelineArray, boolean b,
			String group, VerticalPanel panel) {
		// TODO Auto-generated method stub
		
	}
	
	private String[] postProcess(String post){
		String[] arr = {"","","","",""};
		
		String next = "";
		for(int i=0; i<5; i++){
			String hyperlink = getHyperlink(post);
			if(!hyperlink.equals("x")){
				int firstIndex = post.indexOf(hyperlink);
				arr[i] = (String)post.subSequence(0, firstIndex);
				next = (String)post.subSequence(firstIndex+hyperlink.length(), post.length());
				post = next;	
			}
			else{
				arr[i]=post;
				break;
			}
		
		}
		return arr;
	}
	
	private String getHyperlink(String post){
		String str = "";
		int firstIndex;
		int endIndex;
		
		if(post.contains("http://")){
			firstIndex = post.indexOf("http://");
			endIndex = post.indexOf(" ", firstIndex);
			if(endIndex<post.length()&&endIndex>=0){
				str = (String)post.subSequence(firstIndex, endIndex);
				//post = post.substring(endIndex, post.length());
			}
			else
			{
				str = (String)post.subSequence(firstIndex, post.length());
			}
		}
		else{
			str="x";
		}
		return str;
	}
	
	private String[] getHyperlinkDir(String post){
		String str[] = {"","","","",""};
		int firstIndex;
		int endIndex;
		for(int i=0; i<5; i++){
			if(post.contains("http://")){
				firstIndex = post.indexOf("http://");
				endIndex = post.indexOf(" ", firstIndex);
				if(endIndex<post.length()&&endIndex>=0){
					str[i] = (String)post.subSequence(firstIndex, endIndex);
					post = post.substring(endIndex, post.length());
				}
				else
				{
					str[i] = (String)post.subSequence(firstIndex, post.length());
					break;
				}
			}
			else{
				str[0]="x";
				break;
			}
		}
		return str;
	}
}
