package es.upm.ejemplo;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.SequentialBehaviour;

public class AgComportamientoParalelo extends Agent {
	
	protected void setup() {
		SequentialBehaviour sequentialBehaviour1 = new SequentialBehaviour(this);
		
		sequentialBehaviour1.addSubBehaviour(new OneShotBehaviour(this) {
			public void action() {
				System.out.println("Subcomportamiento 1_1");
			}
		});
		
		sequentialBehaviour1.addSubBehaviour(new OneShotBehaviour(this) {
			public void action() {
				System.out.println("Subcomportamiento 1_2");
			}
		});
		
		sequentialBehaviour1.addSubBehaviour(new OneShotBehaviour(this) {
			public void action() {
				System.out.println("Subcomportamiento 1_3");
			}
		});
		
		SequentialBehaviour sequentialBehaviour2 = new SequentialBehaviour(this);
		
		sequentialBehaviour2.addSubBehaviour(new OneShotBehaviour(this) {
			public void action() {
				System.out.println("Subcomportamiento 2_1");
			}
		});
		
		sequentialBehaviour2.addSubBehaviour(new OneShotBehaviour(this) {
			public void action() {
				System.out.println("Subcomportamiento 2_2");
			}
		});
		
		sequentialBehaviour2.addSubBehaviour(new OneShotBehaviour(this) {
			public void action() {
				System.out.println("Subcomportamiento 2_3");
			}
		});
		/*
		 * No hay paralelismo real porque se ejecuta en un unico
		 * hilo java. Para tener paralelismo real, habria que crear otro
		 * hilo JADE u otro hilo JAVA
		 */
		ParallelBehaviour parallelBehaviour = new ParallelBehaviour(this,ParallelBehaviour.WHEN_ALL);
		parallelBehaviour.addSubBehaviour(sequentialBehaviour1);
		parallelBehaviour.addSubBehaviour(sequentialBehaviour2);
		
		addBehaviour(parallelBehaviour);	
		}

}
