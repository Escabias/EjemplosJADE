package es.upm.ejemplo;

import jade.core.Agent;

public class AgBasico extends Agent {
	
	protected void setup() {
		System.out.println("Primer agente JADE");
		System.out.println("AID: " + this.getAID());
	}

}
