package main.gui;

import javax.swing.*;
import java.util.Arrays;

import java.awt.*;
import main.data.*;
import main.logic.*;

/*die Klasse übernimmt das komplette Hauptfenster des Spiels, die Würfelanzeige, Punkte-Tabelle, Spielernamen 
 *und Input für "Würfeln", "Würfel Halten", "Punkte eintragen", "Bestätigen", "nächster Spieler"
*/
public class MainScreen extends JFrame {

	private static final long serialVersionUID = 1L; // ???

	private int  windowSizeWidth = 1400;
	private int  windowSizeHeight = 1000;
	
	public static int[] diceResult = new int[5];
	private static boolean[] diceSaved = { false, false, false, false, false }; 
	public static int rollCounter = 0;
	public static int numberOfPlayers = 6;
	
	public int currentPlayer = 0; 
	
	
	
	//setSpielerNamenDefault neue methode
	public static String[] playerNames = new String[6];
	
	
	
	// pointsToShow Mischung aus *Berechnet und *Real -> von Dani (und Niclas)
	public static int[][] pointsToShow = Points.getPointsToShow();
	
	public static int[] calculatedPoints = Points.getCalculatedPoints();


	// pointsReal[6][21] und pointsToShow[6][21] (länge der arrays in den brackets)
	public static int[][] pointsReal = Points.getPointsReal();


	
	
	// GUI-Komponenten
	private JPanel leftPanel;
	private JButton rollButton;
	private JButton confirm;
	JButton nextPlayer;
	private JPanel comboToShow;
	
	//DebugMode 
	boolean debugged = false;

	private JPanel dicePanel;
	private static GUIDice[] diceButtons = new GUIDice[5]; 

	
	int choosenPoints;
	String stringCombo;
	int choosenComboIndex;
	boolean zeroPointsWarning = false;

	public MainScreen() {
		playerNames = GameController.getPlayerNames();
		numberOfPlayers = playerNames.length;
		
		System.out.println(Arrays.toString(playerNames));
		System.out.println("Current Player = "+playerNames[currentPlayer]+" (Nr. "+currentPlayer+")");

		setWindowOptions();
 
		addDiceMenu();

		// Tabelle rechts
		addScoreTable();

		// Bestätigen Knopf
		addTableUI();
		// Elemente in den JFrame packen
		addToJFrame();

		setVisible(true);

	}


	private void addToJFrame() {
		add(leftPanel, BorderLayout.WEST);
		add(comboToShow, BorderLayout.CENTER);
		add(rollButton, BorderLayout.SOUTH);
	}

	private void addDiceMenu() {
		
		// Linkes Panel für Spielername/Debug und Würfel
		leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout());

		addPlayerName();

		// Panel für die Würfel unter Spielernamen
		dicePanel = new JPanel();
		dicePanel.setPreferredSize(new Dimension(300, 300)); 
		dicePanel.setLayout(new GridLayout(5, 2)); // 3 Reihen(+2 als Puffer), 2 Spalten
		
		// Hinweis, wie oft gewürfelt wurde
		JLabel roundHint = new JLabel("");
		
		// Würfeln Knopf
		rollButton = new JButton("Würfeln");
		rollButton.setPreferredSize(new Dimension(300, 50));
		
		rollButton.addActionListener(e -> {   
			System.out.println("GUI Würfeln Knopf gedrückt");
			JLabel hint1 = new JLabel("    Klicke Würfel");
			JLabel hint2 = new JLabel("zum Halten!");
			//eigentliche WuerfelMethode
			roll(false);

			//Zusatz GUI infos 
			if (rollCounter == 0) {
				dicePanel.add(hint1);
				dicePanel.add(hint2);
			}
			if (rollCounter == 1) {				
				dicePanel.add(roundHint);
				roundHint.setText("         >>>   "+rollCounter+". Wurf");	
			} else if (rollCounter > 1) {
				roundHint.setText("         >>>   "+rollCounter+". Wurf");
			}
			if (rollCounter == 3) {
				rollButton.setBackground(Color.GRAY);
				rollButton.setEnabled(false);
				roundHint.setText(" >>>   Wähle Kombo");
				roundHint.setHorizontalAlignment(SwingConstants.CENTER); 
			}
			dicePanel.repaint();
			dicePanel.revalidate();
			leftPanel.revalidate(); 
			leftPanel.repaint(); 
		});		
		leftPanel.add(dicePanel, BorderLayout.CENTER);
	    
		//Leeres Component um würfel von links einzurücken
		  JPanel empty1 = new JPanel();
		    empty1.setPreferredSize(new Dimension(100, 100)); 
		    leftPanel.add(empty1, BorderLayout.WEST);

		  JPanel empty2 = new JPanel();
		  empty2.setPreferredSize(new Dimension(100, 100)); 
		  leftPanel.add(empty2, BorderLayout.EAST);
	
	}


	private void addPlayerName() {
		// Spielername oben links
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new BorderLayout());
		JLabel playerName = new JLabel("   >> "+playerNames[currentPlayer]+" <<      ");
		playerName.setHorizontalAlignment(SwingConstants.CENTER);
		JButton debug = new JButton("");
		debug.addActionListener(e -> {
			debugged = true;
		});
		JButton debugConfirm = new JButton(":)");
		debugConfirm.addActionListener(e -> {
			
			if(debugged) {
				 boolean[] saveAll = {true, true, true, true, true};
				 int[] userInput = {1,1,1,1,1};
	            System.out.println("GUI [mainscreen] wuerfelknopf: starte DebugMode");  
	            //öffne DialogFenster mit user input für Würfel
	            DebugMode dialog = new DebugMode(this); // Create and show dialog
	            userInput = dialog.getUserInput(); // Get the result
	           //setze in würfelklasse die userinput werte und setze alle = gehalten
	            Dice.setDiceValues(userInput);
	            Dice.setSavedDiceValues(saveAll);
	            //wurfel mit debug = true damit logik angepasst wird
	            roll(true);
			}
		});
		namePanel.add(playerName, BorderLayout.WEST);
		namePanel.add(debug, BorderLayout.CENTER);
		namePanel.add(debugConfirm, BorderLayout.EAST);
		leftPanel.add(namePanel, BorderLayout.NORTH);
	}

	public void roll(boolean debug) {
		
		System.out.println("GUI [mainscreen] würfeln() ausgeführt - Debug = "+debug);
		//gehaltene Würfel and Wuefel-Klasse übergeben damit diese nicht geändert werden
		if(rollCounter > 0 && !debug) transmitSavedDice();

		//gamecontroller aktiviert würfel funktion der würf.Klasse und übergibt GUI das würfelergebnis
		diceResult = GameController.roll();

		
		if (rollCounter == 1 || rollCounter == 2) {

			for (int i = 0; i < 5; i++) {
				//diceResult von Dice Klasse
				diceButtons[i].roll(diceResult[i]);
			}
		}
		if (rollCounter == 0) {
			if(diceButtons != null) {
				dicePanel.removeAll();
			}
			addDice();

			System.out.println("rollCounter=" + rollCounter);
		}
		if(!debug) rollCounter++;
		if(rollCounter == 3) rollButton.setEnabled(false);
		//entfernt comboToShow JPanel und aktualisiert dann die ScoreTable und fügt dann die comboToShow wieder ein 
		refreshCombosToShow();
	}


	private void addDice() {
		for (int i = 0; i < 5; i++) {
			Font diceFont = new Font("Arial", Font.BOLD, 60); 
			diceButtons[i] = new GUIDice(diceResult[i]); 
			diceButtons[i].setPreferredSize(new Dimension(100,100));
	        diceButtons[i].setMinimumSize(new Dimension(100,100));
	        diceButtons[i].setFont(diceFont);
			dicePanel.add(diceButtons[i]);

		}
	}

	
	public static void transmitSavedDice() {
		for (int i = 0; i < 5; i++) {
			MainScreen.diceSaved[i] = diceButtons[i].isSaved();
		}
		Dice.setSavedDiceValues(diceSaved);
	}
	
	
	
	private void setWindowOptions() {
		//basic fenster einstellungen
		setTitle("Kniffel Spiel");
		setSize(windowSizeWidth, windowSizeHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
	}

	
	private void addTableUI() {

		confirm = new JButton("Zeile in Tabelle auswählen...");
		confirm.setEnabled(false);
		confirm.addActionListener(e -> {
			System.out.println("Auswahl Bestätigt: " + getChoosenComboIndex() + " mit " + getChoosenPoints()
					+ " Punkten.");
			
			if (choosenPoints != 0 && !zeroPointsWarning) {
				System.out.println(
						"Gespeichert: Kombi: " + getChoosenComboIndex() + " mit Wert: " + getChoosenPoints());
				
				nextPlayer.setEnabled(true);
			} else if (zeroPointsWarning) {
				confirm.setText("" + getChoosenComboIndex() + " gestrichen :(");
				nextPlayer.setEnabled(true);
			} else if (choosenPoints == 0) {
				zeroPointsWarning = true;
				confirm.setText("NULL PUNKTE EINTRAGEN?");
				confirm.setOpaque(true); 
				confirm.setBackground(Color.RED);
				confirm.revalidate();
				confirm.repaint();		
			}
	        

		});
		nextPlayer = new JButton("Fertig");
		nextPlayer.addActionListener(e -> {
			//übergibt combo an gamecontroller als index in combo array der gamecontroller klasse
			GameController.chooseCombination(choosenComboIndex, choosenPoints);
			
			if(GameController.getGameOver()) {
				openGameOverDialog();
			} else {
				//nextplayer im gamecontroller ausführen und mit return wert currentplayer aktualisieren
				currentPlayer = GameController.nextPlayer();
	
				//resette werte
				rollCounter = 0;
				nextPlayer.setEnabled(false);
				confirm.setText("Zeile in Tabelle auswählen...");
				rollButton.setEnabled(true);
				pointsReal = Points.getPointsReal();
				zeroPointsWarning = false;
				
			
				// entferne die Würfel
				dicePanel.removeAll();
	
				// wüerfelButtons[] frei machen
				for (int i = 0; i < diceButtons.length; i++) {
					diceButtons[i] = null;
				}
	
				 // alles vom jframe löschen
			    getContentPane().removeAll();
	
			    // neu aufbauen
			    setWindowOptions();
			    addDiceMenu();    
			    addScoreTable();     
			    addTableUI();        
			    addToJFrame();
			    
			    //das hier muss halt immer
			    revalidate();
			    repaint();	        
	
				System.out.println("GUI: Current Player = " + currentPlayer);
			}
		});
		nextPlayer.setEnabled(false);
		JPanel tableButtons = new JPanel(new GridLayout(1, 2));
		tableButtons.add(confirm);
		tableButtons.add(nextPlayer);
		comboToShow.add(tableButtons, BorderLayout.SOUTH);
	}
	
	
	private void addScoreTable() {

		pointsToShow = Arrays.copyOf(Points.getPointsToShow(),numberOfPlayers);
		
		
		// array für 1. zeile labels
		String[] firstColumn = { "Name", "Einser", "Zweier", "Dreier", "Vierer", "Fünfer", "Sechser", "Summe",
				"Bonus (ab 63)", "Oben Gesamt", "Dreierpasch", "Viererpasch", "Full House", "Kleine Straße",
				"Große Straße", "Kniffel", "Chance", "Unten Gesamt", "Oben Gesamt", "Gesamtpunkte" };

		// neues JPanel für Tabelle erstellen
		comboToShow = new JPanel(new BorderLayout());
		JPanel table = new JPanel(new GridBagLayout());
		// GridBagLAyout Setup
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(2, 2, 2, 2);
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;		
		//gleiche Größe für alle Elemente
		Dimension sizeFeld = new Dimension((windowSizeWidth/15), (windowSizeHeight/30));
		
		fillScoreTable(firstColumn, table, c, sizeFeld);		
		table.revalidate();
		table.repaint();
		comboToShow.add(table, BorderLayout.CENTER);
	}


	private void fillScoreTable(String[] firstColumn, JPanel table, GridBagConstraints c, Dimension sizeFeld) {
		//combonamen einfügen
		for (int i = 0; i < firstColumn.length; i++) {		
			c.gridy = i;
			c.gridx = 0;
			JLabel row = new JLabel(firstColumn[i]);
			row.setMinimumSize(sizeFeld);
			row.setPreferredSize(sizeFeld);
			table.add(row, c);
		}		
		// restliche zellen füllen	
		
		//name einfügen (erste Zeile)
		for(int i = 0; i < numberOfPlayers; i++){
			c.gridy = 0;
			c.gridx = i+1;
			JLabel name = new JLabel(playerNames[i]);
			name.setMinimumSize(sizeFeld);
			name.setPreferredSize(sizeFeld);
			name.setBackground(new Color(200, 200, 200));
			name.setHorizontalAlignment(SwingConstants.CENTER);
			table.add(name, c);
		}
		//restliche zellen einfügen (außer spieler-name und combo-bezeichnungen)
		for (int column = 1; column < numberOfPlayers+1; column++) {
			c.gridx = column;
			for(int row = 1; row < 20; row++){
				c.gridy = row;
				//feld ist eintragbar -> button
				if (pointsReal[column-1][row-1] < 0 && (column-1) == currentPlayer && pointsToShow[column-1][row-1] >= 0) { 	
					JButton newPoints = new JButton("");
					//minus 1 zu "-"
					if(pointsToShow[column-1][row-1] < 0){
						newPoints.setText("-");
					}else{
						newPoints.setText("" + pointsToShow[column-1][row-1]);
					}
					String stringCombo = firstColumn[row];
					int rowCombo = row;
					int points = Integer.parseInt("" + pointsToShow[column-1][row-1]);
					newPoints.setMinimumSize(sizeFeld);
					newPoints.setPreferredSize(sizeFeld);
					newPoints.setMargin(new Insets(0, 0, 0, 0));
					newPoints.setOpaque(true);
					if(points==0) {
						newPoints.setBackground(new Color(255, 200, 200));
					} else {
						newPoints.setBackground(new Color(200, 255, 200));
					}
					// punkte int wir vor der lamda expr gespeichert und wenn lambda ausgeführt wird
					// das passende value genutzt
					// das wird auch variable capture genannt
					newPoints.addActionListener(e -> {					

						confirm.setText("Als '" + stringCombo + "' eintragen (" + points + " Punkte)");
						//aktualisiert die SpielerAuswahl Variablen mit den für den Knopf zwischengespeicherten Variablen
						choosenPoints = points;
						//0-5 = {einser,zweier,...,sechser}
						choosenComboIndex = rowCombo-1;		
						confirm.setEnabled(true);
					});
					table.add(newPoints, c);
				//Jbutton
				} else {
					JLabel pointsLabel = new JLabel("");
					pointsLabel.setMinimumSize(sizeFeld);
					pointsLabel.setPreferredSize(sizeFeld);
					if(pointsToShow[column-1][row-1] < 0){
						pointsLabel.setText("-");
					}else{
						pointsLabel.setText("" + pointsToShow[column-1][row-1]);
					}	
					pointsLabel.setMinimumSize(sizeFeld);
			        pointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
					table.add(pointsLabel, c);			
				}			
			}
		}
	}


	private void removeCombosToShow() {
    //entfernt alles von Jpanel comboanzeige
    if (comboToShow != null && comboToShow.getParent() != null) {
        getContentPane().remove(comboToShow); 
        revalidate(); 
        repaint();    
    }
}
	private void refreshCombosToShow() {
    //hard reset damit die Tabelle richtige Werte hat
	removeCombosToShow(); 
    addScoreTable(); 
	addTableUI();
    add(comboToShow, BorderLayout.CENTER);
    revalidate(); 
    repaint();    
    

}

	private void openGameOverDialog() {
	//JDIalog = kleines Fenster starten
    JDialog dialog = new JDialog(this, "Spielende", true);
    dialog.setSize(300, 100);
    dialog.setLocationRelativeTo(this);
    JPanel endPanel = new JPanel();
    endPanel.setLayout(new BorderLayout());
    
    JLabel winner = new JLabel();
    winner.setText("Der Gewinner ist: "+GameController.getWinnerName()+" mit "+GameController.getWinnerPoints()+" Punkten :)" );
    endPanel.add(winner, BorderLayout.NORTH);
    JButton closeButton = new JButton("Spiel Beenden");
    closeButton.addActionListener(e -> {
    		//alles schließen
            dispose();
            dialog.dispose();     
    });
    
    endPanel.add(closeButton, BorderLayout.SOUTH);
    dialog.add(endPanel);
    dialog.setVisible(true);
}

	private int getChoosenPoints() {
	
		return choosenPoints;
	}



	public int getChoosenComboIndex() {
		return choosenComboIndex;
	}
}