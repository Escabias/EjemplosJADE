package es.upm.ejemplo;

import jade.core.Agent;
import jade.core.behaviours.*;

public class AgComportamientoCiclico3 extends Agent {
	
	ComportamientoCiclico2 cs2; 
	ComportamientoCiclico3 cs3;

	
	protected void setup() {
		System.out.println("Primer Agente JADE con 2 Comportamientos"); 
		ComportamientoCiclico1 cs= new ComportamientoCiclico1();
		cs2= new ComportamientoCiclico2();
		cs3= new ComportamientoCiclico3();
		addBehaviour(cs);
		addBehaviour(cs2);
		addBehaviour(cs3);
		cs3.block();
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
				cs3.restart();
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
	
	class ComportamientoCiclico3 extends CyclicBehaviour {
		
		int limite = 0;
		@Override
		public void action() {
			limite++;
			System.out.println("Ejecuto tarea C3Lim" + limite);
		}
		
	}
}
