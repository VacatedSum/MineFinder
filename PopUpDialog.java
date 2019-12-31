package edu.sunyulster.cinas54.minefinder;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.web.*;
import java.io.File;
import javafx.geometry.*;



/**
 * PopUpDialog - A Container class for various quick methods for getting</br>
 * and displaying data.</br>
 * </br>
 * Any application that uses this must extend the JavaFX Application</br>
 * class and override the start method. Failure to do so will result</br>
 * in a bloody console.</br>
 * </br>
 * Use the start method in place of main (put your program instructions</br>
 * in start()) and then call launch(args) from main().</br>
 * </br>
 * i.e.</br>
 * </br>
 * public static void main(String[] args){</br>
 * 			launch(args);</br>
 * }</br>
 * </br>
 * {@literal @}Override public void start(Stage primaryStage){</br>
 * 			//Program instructions here</br>
 * 			//(The stuff that normally goes in main)</br>
 * }
 * 
 * 
 * @author Steve Cina, SUNY Ulster 2019
 *
 */
public final class PopUpDialog {
	static Stage stage; 
	static boolean answer; 
	
	/**
	 * Creates a a simple message box with an OK button to proceed
	 * Program will stop and wait for this event to complete.
	 * 
	 * @param String message 
	 * The message to be displayed.
	 * 
	 * @param String title
	 * The title of the new window.
	 * 
	 */
	public static void MessageBox(String message, String title) {
		stage = new Stage();
		//Modal application mode means that the user will need to stop
		//and acknowledge the box before proceeding with the program function.
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(title);
		stage.setMinWidth(250);
		stage.setResizable(false);
		
		Label lbl = new Label();
		lbl.setText(message);
		
		Button btn = new Button();
		btn.setText("OK");
		btn.setOnAction(e -> stage.close());
		
		VBox pane = new VBox(20);
		pane.getChildren().addAll(lbl, btn);
		pane.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.showAndWait();
		}
	
	/**
	 * Creates a simple confirmation box of the "Are you sure?" family
	 * 
	 * @param message
	 * @param title
	 * @return true if yes is clicked, false if no is clicked.
	 */
	public static boolean ConfirmationBox(String message, String title) {
		stage = new Stage();
		//Modal application mode means that the user will need to stop
		//and acknowledge the box before proceeding with the program function.
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(title);
		stage.setMinWidth(250);
		stage.setResizable(false);
		
		Label lbl = new Label();
		lbl.setText(message);
		
		Button yes = new Button();
		Button no = new Button();
		yes.setOnMouseClicked(e -> yesClicked());
		no.setOnMouseClicked(e -> noClicked());
		yes.setText("Yes");
		no.setText("No");
		
		HBox buttonContainer = new HBox(20);
		buttonContainer.getChildren().addAll(yes, no);
		buttonContainer.setAlignment(Pos.CENTER);
		
		VBox pane = new VBox(20);
		pane.getChildren().addAll(lbl, buttonContainer);
		pane.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.showAndWait();
		
		stage.close();
		return answer;
		
	}
	
	/**
	 * A GUI interface to get input. Returns string value.
	 * No error checking. If you want to check for errors
	 * or use this to pull a different type, it's up to you.
	 * 
	 * @param message
	 * @param title
	 * @param defaultText
	 * @return
	 */
	public static String GetInput(String message, String title, String defaultText) {
		stage = new Stage();
		stage.setTitle(title);
		stage.setMinWidth(250);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		
		Label lbl = new Label();
		lbl.setText(message);
		
		TextField field = new TextField();
		field.setPromptText(defaultText);
		
		Button yesBtn = new Button();
		yesBtn.setText("OK");
		yesBtn.setOnMouseClicked(e -> yesClicked());
		
		Button noBtn = new Button();
		noBtn.setText("Cancel");
		noBtn.setOnMouseClicked(e -> noClicked());
		
		HBox btnContainer = new HBox();
		btnContainer.getChildren().addAll(yesBtn, noBtn);
		btnContainer.setAlignment(Pos.CENTER);
		
		VBox pane = new VBox();
		pane.getChildren().addAll(lbl, field, btnContainer);
		pane.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.showAndWait();
		
		if(answer) {
			return field.getText();
		}
		else {
			return null;
		}
	}
	
	/**
	 * Pass an array of strings. It's not too picky.
	 * 
	 * @return An array of strings (same length) - empty slots are 
	 * returned as NULL references.
	 * </br>If the box is cancelled by the user, the array returned 
	 * will itself be a null reference. Beware.
	 * 
	 * 
	 * @param boxes - String[]
	 * @param message - String
	 * @param title - String
	 * 
	 */
	public static String[] CheckBox(String[] boxes, String message, String title) {
		CheckBox[] checkboxes = new CheckBox[boxes.length];
		String[] output = null;
		for (int i = 0; i < boxes.length; i++) {
			checkboxes[i] = new CheckBox();
			checkboxes[i].setText(boxes[i]);
		}
		
		stage = new Stage();
		stage.setTitle(title);
		stage.setMinWidth(250);
		stage.setResizable(false);
		
		Label lbl = new Label();
		lbl.setText(message);
		
		Button submit = new Button();
		submit.setText("Submit");
		submit.setOnMouseClicked(e -> yesClicked());
		
		Button cancel = new Button();
		cancel.setText("Cancel");
		cancel.setOnMouseClicked(e -> noClicked());
		
		HBox buttonContainer = new HBox();
		buttonContainer.setAlignment(Pos.CENTER);
		buttonContainer.getChildren().addAll(submit, cancel);
		
		VBox checkContainer = new VBox();
		checkContainer.setAlignment(Pos.CENTER_LEFT);
		checkContainer.getChildren().addAll(checkboxes);
		
		VBox pane = new VBox();
		pane.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(lbl, checkContainer, buttonContainer);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene((new Scene(pane)));
		stage.showAndWait();
		
		if (answer) {
			output = new String[checkboxes.length];
			for (int i = 0; i < checkboxes.length; i++) {
				if (checkboxes[i].isSelected()) {
					output[i] = checkboxes[i].getText();
				}
			}
		} 
		return output;
		
		
	}
	
	/**
	 * Creates radio boxes from a String array.
	 * Empty/Null references in list will display as empty spaces.
	 * The resulting list will not be trimmed.
	 * 
	 * @param radiolabels
	 * @param message
	 * @param title
	 * 
	 * @return A string representative of the choice made. If a null
	 * array index is made available to the user, there is a possibility
	 * of the return value being blank or null.
	 * 
	 */
	public static String Radio(String[] radiolabels, String message, String title) {
		RadioButton[] radios = new RadioButton[radiolabels.length];
		ToggleGroup radioGroup = new ToggleGroup();
		for (int i = 0; i < radiolabels.length; i++) {
			 radios[i] = new RadioButton();
			 radios[i].setText(radiolabels[i]);
			 radios[i].setToggleGroup(radioGroup);
		}
		
		Label lbl = new Label();
		lbl.setText(message);
		
		VBox radiobox = new VBox();
		radiobox.setAlignment(Pos.BASELINE_LEFT);
		radiobox.getChildren().addAll(radios);
		
		Button yes = new Button();
		yes.setText("Submit");
		yes.setOnMouseClicked(e -> yesClicked());
		
		Button no = new Button();
		no.setText("Cancel");
		no.setOnMouseClicked(e -> noClicked());
		
		HBox btnContainer = new HBox(20);
		btnContainer.setAlignment(Pos.CENTER);
		btnContainer.getChildren().addAll(yes, no);
		
		VBox pane = new VBox();
		pane.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(lbl, radiobox, btnContainer);
		
		Scene scene = new Scene(pane);
		
		
		stage = new Stage();
		stage.setTitle(title);
		stage.setMinWidth(250);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.showAndWait();
		
		//Not sure if this is the best way to accomplish this, but it seems to be working.
		if (answer) return ((RadioButton) radioGroup.getSelectedToggle()).getText();
				
		return null;
	}
	
	/**
	 * ChoiceBox - pretty much identical to the radio box method,
	 * but a drop-down box instead.
	 * 
	 * Empty/Null references passed within the string array to this
	 * method will show as empty spaces/options.
	 * 
	 * @param choiceList
	 * @param message
	 * @param title
	 * @return
	 */
	public static String ChoiceBox(String[] choiceList, String message, String title) {
		ChoiceBox<String> choices = new ChoiceBox<String>();
		choices.getItems().addAll(choiceList);
		choices.setValue(choiceList[0]);
		Button yes = new Button();
		yes.setText("Submit");
		yes.setOnMouseClicked(e-> yesClicked());
		
		Button no = new Button();
		no.setText("Cancel");
		no.setOnMouseClicked(e-> noClicked());
		
		HBox btnContainer = new HBox(20);
		btnContainer.setAlignment(Pos.CENTER);
		btnContainer.getChildren().addAll(yes, no);
		
		Label lbl = new Label();
		lbl.setText(message);
		
		HBox choiceContainer = new HBox(30);
		choiceContainer.getChildren().addAll(lbl, choices);
		choiceContainer.setAlignment(Pos.CENTER);
		
		VBox pane = new VBox();
		pane.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(choiceContainer, btnContainer);
		
		Scene scene = new Scene(pane);
		stage = new Stage();
		stage.setTitle(title);
		stage.setMinWidth(250);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.showAndWait();
		
		if (answer) return choices.getValue();
		else return null;
	}
	/**
	 * Creates an open dialog and returns the selected file.
	 * 
	 * @param title
	 * @return The selected file. May be null.
	 */
	public static File FileChoice(String title) {
		FileChooser chooser = new FileChooser();
		File answer;
		chooser.setTitle(title);
		answer = chooser.showOpenDialog(null);
		return answer;
	}
	
	/**
	 * ComboBox - Essentially an editable version of CheckBox.</br>
	 * User will be allowed to edit options for customization.</br>
	 * 
	 * @param options
	 * @param message
	 * @param title
	 * @return The String choice. Possibly null.
	 */
	public static String ComboBox(String[] options, String message, String title) {
		ComboBox<String> combo = new ComboBox<String>();
		combo.getItems().addAll(options);
		combo.setEditable(true);
		combo.setValue(options[0]);
		Label lbl = new Label();
		lbl.setText(message);
		
		Button yes = new Button();
		yes.setText("OK");
		yes.setOnMouseClicked(e-> yesClicked());
		
		Button no = new Button();
		no.setText("Cancel");
		no.setOnMouseClicked(e-> noClicked());
		
		HBox comboContainer = new HBox(20);
		comboContainer.getChildren().addAll(lbl, combo);
		comboContainer.setAlignment(Pos.CENTER);
		
		HBox btnContainer = new HBox(20);
		btnContainer.getChildren().addAll(yes, no);
		btnContainer.setAlignment(Pos.CENTER);
		
		VBox pane = new VBox(20);
		pane.getChildren().addAll(comboContainer, btnContainer);
		pane.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(pane);
		
		stage = new Stage();
		stage.setTitle(title);
		stage.setMinWidth(250);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setResizable(false);
		stage.setScene(scene);
		stage.showAndWait();
		
		if (answer) return combo.getValue();
		else return null;
		
		
	}
	
	public static String[] ListSelect(String[] listOptions, String message, String title) {
		String[] answerList = new String[listOptions.length];
		ListView<String> list = new ListView<String>();
		list.getItems().addAll(listOptions);
		list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		
		Label lbl = new Label();
		lbl.setText(message);
		
		Button yes = new Button();
		yes.setText("OK");
		yes.setOnMouseClicked(e-> yesClicked());
		
		Button no = new Button();
		no.setText("Cancel");
		no.setOnMouseClicked(e-> noClicked());
		
		HBox btnBox = new HBox(20);
		btnBox.setAlignment(Pos.CENTER);
		btnBox.getChildren().addAll(yes, no);
		
		VBox pane = new VBox(20);
		pane.getChildren().addAll(lbl, list, btnBox);
		
		
		stage = new Stage();
		stage.setTitle(title);
		stage.setMinWidth(250);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setResizable(false);
		stage.setScene(new Scene(pane));
		stage.showAndWait();
		
		if (answer) {
			answerList = list.getSelectionModel().getSelectedItems().toArray(answerList);
			return answerList;
		}
		return null;
	}
	
	
	public static void WebSite(String URL, String title) {
		
		WebView site = new WebView();
		site.getEngine().load(URL);
		site.setContextMenuEnabled(false);
		
		stage = new Stage();
		stage.setTitle(title);
		stage.setAlwaysOnTop(true);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setFullScreenExitKeyCombination(new KeyCodeCombination(KeyCode.J, KeyCombination.ALT_DOWN));
		stage.setFullScreen(true);
		stage.setResizable(false);
		stage.setScene(new Scene(site));
		stage.showAndWait();
	}
	
	
	//Private methods to support public methods.
	private static void noClicked() {
		answer = false;
		stage.close();
	}
	
	private static void yesClicked() {
		answer = true;
		stage.close();
	}
	
	
}
