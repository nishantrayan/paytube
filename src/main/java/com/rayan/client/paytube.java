package com.rayan.client;

import java.util.ArrayList;

import com.rayan.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SourcesTabEvents;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.TabListener;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.TextBoxBase;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.SuggestOracle.Callback;
import com.google.gwt.user.client.ui.SuggestOracle.Request;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class paytube implements EntryPoint {
	private final Messages messages = (Messages) GWT.create(Messages.class);
	protected static final String ADD_TRANSACTION = "Add transaction";
	private final Grid grid = new Grid();

	public void onModuleLoad() {
		TabBar tabs = createTabPanel();
		initializeGrid();
		VerticalPanel paytube = new VerticalPanel();
		paytube.add(tabs);
		paytube.add(grid);
		RootPanel.get().add(paytube);
	}

	private void initializeGrid() {
		grid.setVisible(false);
	}

	private TabBar createTabPanel() {
		final TabBar tabs = new TabBar();
		tabs.addTab(ADD_TRANSACTION);
		tabs.addTab("Add person");
		tabs.addTabListener(new TabListener() {

			public void onTabSelected(SourcesTabEvents sender, int tabIndex) {
				if (ADD_TRANSACTION.equals(tabs.getTabHTML(tabIndex))) {
					// populate with add transction grid.
					grid.resize(4, 2);
					grid.setWidget(0, 0, new Label(messages.place()));
					TextBox amountText = createNewTextField();
					TextBox placeText = createNewTextField();
					grid.setWidget(0, 1, placeText);
					grid.setWidget(1, 0, new Label(messages.payer()));
					TextBox payerText = createNewTextField();
					MultiWordSuggestOracle suggestionList = new MultiWordSuggestOracle();
					ArrayList nameList = getNameList();
					suggestionList.addAll(nameList);
					SuggestBox payerNameSuggestBox = new SuggestBox(
							suggestionList, payerText);
					grid.setWidget(1, 1, payerNameSuggestBox);
					grid.setWidget(2, 0, new Label(messages.amount()));
					grid.setWidget(2, 1, amountText);
					grid.setVisible(true);
				}
			}

			public boolean onBeforeTabSelected(SourcesTabEvents sender,
					int tabIndex) {
				return true;
			}
		});
		return tabs;
	}

	private ArrayList getNameList() {
		ArrayList nameList = new ArrayList();
		nameList.add("Nishant Rayan");
		return nameList;
	}

	private TextBox createNewTextField() {
		TextBox placeText = new TextBox();
		placeText.setVisibleLength(50);
		return placeText;
	}

}
