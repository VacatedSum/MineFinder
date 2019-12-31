package edu.sunyulster.cinas54.minefinder;
import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.event.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class Main extends Application implements EventHandler <MouseEvent> {
	private static final int SIZE = 15;
	private static final String BLACK = "-fx-background-color: #000000; ";
	private static final String RED   = "-fx-background-color: #CE5E5E; ";
	private static final String VIOlET= "-fx-background-color: #B45ECE; ";
	private static final String TEAL  = "-fx-background-color: #56F3BF; ";
	private static final String LIME  = "-fx-background-color: #56F38A; ";
	private static final String ORANGE= "-fx-background-color: #F3B456; ";
	private static final String LTBLUE= "-fx-background-color: #56DEF3; ";
	private static final String DKBLUE= "-fx-background-color: #0C73DB; ";
	private static final String GREY  = "-fx-background-color: #888FBA; ";
	private static final String LELLO = "-fx-background-color: #D4FE01; ";
	private static final String WHITE= "-fx-background-color: #FFFFFF; ";
	
	private static final String[] btnLabels = {"0","1","2","3","4","5","6","7","8","X"," "};
	private static Button[][] btns;
	private static VBox[] cols;
	private Board game;
	public static void main(String[] args) {
		launch(args);
	}

	@Override public void start(Stage primaryStage) {
		
		game = new Board(SIZE);
		btns = new Button[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				btns[i][j] = new Button();
				btns[i][j].setMinHeight(25);
				btns[i][j].setMinWidth(btns[i][j].getMinHeight());
				btns[i][j].setText(btnLabels[10]);
				btns[i][j].setStyle(GREY);
				btns[i][j].setOnMouseClicked(this);
			}
		}
		
		cols = new VBox[SIZE];
		for (int i = 0; i < SIZE; i++) {
			cols[i] = new VBox(2);
			cols[i].getChildren().addAll(btns[i]);
		}
		
		HBox pane = new HBox(2);
		pane.getChildren().addAll(cols);
		
		Scene myScene = new Scene(pane);
		primaryStage.setResizable(false);
		primaryStage.setTitle("MineFinder");
		primaryStage.setScene(myScene);
		primaryStage.centerOnScreen();
		primaryStage.sizeToScene();
		primaryStage.show();
	}
	@Override public void handle(MouseEvent t) {
		Button btn = (Button) t.getSource();
		int x = 0;
		int y = 0;
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (btns[i][j]==btn) {
					x = i;
					y = j;
					break;
				}
			}
		}
		if (t.getButton()==MouseButton.PRIMARY) {
			if (game.getIsMine(x, y)) {
				PopUpDialog.MessageBox("You lose!", "BOOM!");
			}
			else {
				btns[x][y].setText(btnLabels[game.getNumber(x, y)]);
				int check = game.getNumber(x, y);
				if (check==0) {
					btns[x][y].setStyle(LELLO);
				} else if (check==1) {
					btns[x][y].setStyle(TEAL);
				} else if (check==2) {
					btns[x][y].setStyle(LTBLUE);
				} else if (check==3) {
					btns[x][y].setStyle(DKBLUE);
				} else if (check==4) {
					btns[x][y].setStyle(VIOlET);
				} else if (check==5) {
					btns[x][y].setStyle(LIME);
				} else if (check==6) {
					btns[x][y].setStyle(ORANGE);
				} else if (check==7) {
					btns[x][y].setStyle(BLACK);
				} else if (check==8) {
					btns[x][y].setStyle(WHITE);
				} else if (check==9) {
					btns[x][y].setStyle(RED);
				} else {
					btns[x][y].setStyle(null);
					
				}
			}
		}
		else if (t.getButton()==MouseButton.SECONDARY) {
			if (btns[x][y].getText().equals(btnLabels[9])) {
				btns[x][y].setText(btnLabels[10]);
				btns[x][y].setStyle(GREY);
			} else if (btns[x][y].getText().equals(btnLabels[10])) {
				btns[x][y].setText(btnLabels[9]);
				btns[x][y].setStyle(RED);
			}
		}
		
		
	}
}
