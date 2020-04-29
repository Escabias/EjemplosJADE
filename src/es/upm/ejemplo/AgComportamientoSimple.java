package es.upm.ejemplo;

import jade.core.Agent;
import jade.core.behaviours.*;

public class AgComportamientoSimple extends Agent {
	
	class ComportamientoSimple extends SimpleBehaviour {

		@Override
		public void action() {
			for(int i=0;i<10;i++)
				System.out.println("Ejecuto tarea " + i);			
		}

		@Override
		public boolean done() {
			/*
			 * Pregunta si el comportamiento ha finalizado o no,
			 * luego al decir que si, termina de manera one shot.
			 */
			return true; 
			
			
			/*
			 * Si ponemos el false,
			 *  se convierte en un comportamiento ciclico.
			 */
			//return false;
		}	
	}
	
	protected void setup() {
		System.out.println("Primer Agente JADE con Comportamiento Simple");
		ComportamientoSimple cs = new ComportamientoSimple(); 
		addBehaviour(cs);
	}

}
