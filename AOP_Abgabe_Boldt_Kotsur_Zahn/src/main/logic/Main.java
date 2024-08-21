package main.logic;

import main.logic.combos.Combos;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Wuerfel.wurfeln();
		for(int i = 0;i < Wuerfel.getWuerfelWerte().length;i++) {
			System.out.println(Wuerfel.getWuerfelWerte()[i]);
			
		}
		
		System.out.println(Combos.getMoeglicheComboPunkte());
		System.out.println(Wuerfel.getAnzahlWerte());
		System.out.println(Combos.umgewandeltZuListe());
	}
}
