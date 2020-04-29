package es.upm.ejemplo;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;

public class AgComportamientoCiclico extends Agent {
	
	class ComportamientoCiclico extends CyclicBehaviour {
		
		int limite = 0;
		@Override
		public void action() {
			limite++;
			System.out.println("Ejecuto tarea " + limite);			
		}
	}
	
	protected void setup() {
		System.out.println("Primer Agente JADE con Comportamiento Ciclico"); 
		ComportamientoCiclico cs1 = new ComportamientoCiclico(); 
		addBehaviour(cs1);
		System.out.println("Despues de añadir el comportamiento Ciclico");
	}

}
