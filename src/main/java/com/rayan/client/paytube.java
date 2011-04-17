package com.rayan.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
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

import eu.maydu.gwt.validation.client.DefaultValidationProcessor;
import eu.maydu.gwt.validation.client.ValidationAction;
import eu.maydu.gwt.validation.client.ValidationException;
import eu.maydu.gwt.validation.client.ValidationProcessor;
import eu.maydu.gwt.validation.client.Validator;
import eu.maydu.gwt.validation.client.actions.LabelTextAction;
import eu.maydu.gwt.validation.client.validators.ListBoxValidator;
import eu.maydu.gwt.validation.client.validators.numeric.DoubleValidator;
import eu.maydu.gwt.validation.client.validators.numeric.IntegerValidator;
import eu.maydu.gwt.validation.client.validators.standard.NotEmptyValidator;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class paytube implements EntryPoint {
	private static final String ERROR_MSG_STYLE = "errorMsg";
	private static final String PLACE_VALIDATION = "placeValidation";
	private final Messages messages = (Messages) GWT.create(Messages.class);
	protected static final String ADD_TRANSACTION = "Add transaction";
	private static final String PAYER_VALIDATION = "payerValidation";
	private static final String AMOUNT_VALIDATION = "amountValidation";
	protected static final String SPLIT_AMOUNT_VALIDATION = "splitAmountValidation";
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

	private ArrayList<String> getNameList() {
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

		final Map<String, String> errorMessages = new HashMap<String, String>();
		errorMessages.put(PLACE_VALIDATION, "place cannot be empty");
		errorMessages.put(PAYER_VALIDATION, "payer cannot be empty");
		errorMessages.put(AMOUNT_VALIDATION, "amount must be a decimal number");
		errorMessages
				.put(SPLIT_AMOUNT_VALIDATION, "should be a decimal number");

		eu.maydu.gwt.validation.client.i18n.ValidationMessages validationMessages = new eu.maydu.gwt.validation.client.i18n.ValidationMessages() {
			@Override
			public String getCustomMessage(String key, Object... parameters) {
				return errorMessages.get(key);
			}
		};

		final ValidationProcessor validationProcessor = new DefaultValidationProcessor(
				validationMessages);

		grid.resize(7, 3);
		grid.setWidget(0, 0, new Label(messages.place()));
		final TextBox amountText = createNewTextField();
		Label amountTextError = new Label();
		TextBox placeText = createNewTextField();
		grid.setWidget(0, 1, placeText);
		Label placeTextError = new Label();
		grid.setWidget(0, 2, placeTextError);
		grid.setWidget(1, 0, new Label(messages.payer()));
		TextBox payerText = createNewTextField();
		ListBox payerNameListBox = new ListBox();
		payerNameListBox.addItem("");
		for (String payerName : getNameList()) {
			payerNameListBox.addItem(payerName);
		}
		Label payerTextError = new Label();
		grid.setWidget(1, 1, payerNameListBox);
		grid.setWidget(1, 2, payerTextError);
		grid.setWidget(2, 0, new Label(messages.amount()));
		grid.setWidget(2, 1, amountText);
		grid.setWidget(2, 2, amountTextError);
		grid.setWidget(3, 0, new Label(messages.payees()));
		final SuggestBox payeeText = createNameSuggestionBox(createNewTextField());
		final FlexTable payeeTable = new FlexTable();
		final ArrayList<String> payeeList = new ArrayList<String>();
		final List<Validator> splitAmountValidators = new ArrayList<Validator>();
		payeeText.addSelectionHandler(new SelectionHandler<Suggestion>() {

			public void onSelection(SelectionEvent<Suggestion> event) {
				// add to flexi table containing list of names.
				String selectedName = event.getSelectedItem()
						.getReplacementString();
				if (!payeeList.contains(selectedName)) {
					payeeTable.setWidget(payeeList.size(), 0, new Label(
							selectedName));
					TextBox splitAmount = new TextBox();
					splitAmount.setVisibleLength(10);
					payeeTable.setWidget(payeeList.size(), 1, splitAmount);
					Label splitAmountError = new Label();
					payeeTable.setWidget(payeeList.size(), 2, splitAmountError);

					splitAmountValidators.add(new DoubleValidator(splitAmount,
							SPLIT_AMOUNT_VALIDATION)
							.addActionForFailure(new LabelTextAction(
									splitAmountError, false)));
					splitAmountError.addStyleName(ERROR_MSG_STYLE);
					validationProcessor.addValidators(
							"split amount validation", splitAmountValidators
									.toArray(new Validator[0]));
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
		Button submitButton = new Button(messages.submitTransaction());
		submitButton.addClickListener(new ClickListener() {

			public void onClick(Widget sender) {
				boolean success = validationProcessor.validate();
				Window.alert("Success:" + success);
			}
		});
		grid.setWidget(6, 1, submitButton);

		ArrayList<Validator> basicFieldValidators = new ArrayList<Validator>();
		basicFieldValidators.add(new NotEmptyValidator(placeText,
				PLACE_VALIDATION).addActionForFailure(new LabelTextAction(
				placeTextError, false)));
		basicFieldValidators.add(new ListBoxValidator(payerNameListBox, "",
				PAYER_VALIDATION).addActionForFailure(new LabelTextAction(
				payerTextError, false)));
		basicFieldValidators.add(new DoubleValidator(amountText,
				AMOUNT_VALIDATION).addActionForFailure(new LabelTextAction(
				amountTextError, false)));
		validationProcessor.addValidators("basic fields validation",
				basicFieldValidators.toArray(new Validator[0]));

		placeTextError.addStyleName(ERROR_MSG_STYLE);
		payerTextError.addStyleName(ERROR_MSG_STYLE);
		amountTextError.addStyleName(ERROR_MSG_STYLE);
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
