package com.rayan.client;

import java.util.ArrayList;

import com.rayan.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
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
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.SuggestOracle.Callback;
import com.google.gwt.user.client.ui.SuggestOracle.Request;
import com.google.gwt.user.client.ui.SuggestOracle.Suggestion;

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
					initializeAddTransactionGrid();
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
		nameList.add("Maitreyee korgaonkar");
		return nameList;
	}

	private TextBox createNewTextField() {
		TextBox placeText = new TextBox();
		placeText.setVisibleLength(50);
		return placeText;
	}

	private void initializeAddTransactionGrid() {
		grid.resize(7, 2);
		grid.setWidget(0, 0, new Label(messages.place()));
		final TextBox amountText = createNewTextField();
		TextBox placeText = createNewTextField();
		grid.setWidget(0, 1, placeText);
		grid.setWidget(1, 0, new Label(messages.payer()));
		TextBox payerText = createNewTextField();
		SuggestBox payerNameSuggestBox = createNameSuggestionBox(payerText);
		grid.setWidget(1, 1, payerNameSuggestBox);
		grid.setWidget(2, 0, new Label(messages.amount()));
		grid.setWidget(2, 1, amountText);
		grid.setWidget(3, 0, new Label(messages.payees()));
		final SuggestBox payeeText = createNameSuggestionBox(createNewTextField());
		final FlexTable payeeTable = new FlexTable();
		final ArrayList<String> payeeList = new ArrayList<String>();
		payeeText.addSelectionHandler(new SelectionHandler<Suggestion>() {

			public void onSelection(SelectionEvent<Suggestion> event) {
				// add to flexi table containing list of names.
				String selectedName = event.getSelectedItem()
						.getReplacementString();
				if (!payeeList.contains(selectedName)) {
					payeeTable.setWidget(payeeList.size(), 0, new Label(
							selectedName));
					TextBox splitAmount = new TextBox();
					splitAmount.setVisibleLength(20);
					payeeTable.setWidget(payeeList.size(), 1, splitAmount);
					payeeList.add(selectedName);
				}
				payeeText.setText("");
			}
		});
		grid.setWidget(3, 1, payeeText);
		grid.setWidget(4, 1, payeeTable);
		Button splitEvenlyButton = new Button(messages.split());
		grid.setWidget(5, 1, splitEvenlyButton);
		splitEvenlyButton.addClickListener(new ClickListener() {
			public void onClick(Widget sender) {
				String amount = amountText.getText();
				double amountDouble = Double.parseDouble(amount);
				double payBreakup = amountDouble / payeeList.size();
				for (int i = 0; i < payeeList.size(); i++) {
					TextBox splitAmountText = (TextBox) payeeTable.getWidget(i,
							1);
					splitAmountText.setText("" + payBreakup);
				}
			}
		});
		grid.setWidget(6, 1, new Button(messages.submitTransaction()));
		grid.setVisible(true);
	}

	private SuggestBox createNameSuggestionBox(TextBox payerText) {
		MultiWordSuggestOracle suggestionList = new MultiWordSuggestOracle();
		ArrayList nameList = getNameList();
		suggestionList.addAll(nameList);
		SuggestBox payerNameSuggestBox = new SuggestBox(suggestionList,
				payerText);
		return payerNameSuggestBox;
	}

}
