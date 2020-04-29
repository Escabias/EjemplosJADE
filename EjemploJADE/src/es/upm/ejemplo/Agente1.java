package es.upm.ejemplo;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;

public class Agente1 extends Agent {
	
	protected CyclicBehaviour cyclicBehaviour;
	
	public void setup() {
		System.out.println("Soy el agente 1");
		cyclicBehaviour = new CyclicBehaviour(this) {
			
			@Override
			public void action() {
				block();
			}
		};
		
		addBehaviour(cyclicBehaviour);
	}

}
