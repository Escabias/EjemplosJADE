package es.upm.ejemplo;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;

public class AgComportamientoCiclico2 extends Agent {
	
	ComportamientoCiclico2 cs2;
	protected void setup() {
		System.out.println("Primer Agente JADE con 2 Comportamientos");
		ComportamientoCiclico1 cs= new ComportamientoCiclico1();
		cs2 = new ComportamientoCiclico2();
		addBehaviour(cs);
		addBehaviour(cs2);
		System.out.println("Despues de añadir los comportamientos");
	}
	
	class ComportamientoCiclico1 extends CyclicBehaviour {

		int limite = 0;
		@Override
		public void action() {
			limite++;
			System.out.println("Ejecuto tarea C1Lim" + limite);
			if (limite>5000) {
				removeBehaviour(cs2);
			}
		}
		
	}
	
	class ComportamientoCiclico2 extends CyclicBehaviour {

		int limite = 0;
		@Override
		public void action() {
			limite++;
			removeBehaviour(cs2);
			System.out.println("Ejecuto tarea C2Lim" + limite);			
		}
		
	}

}
