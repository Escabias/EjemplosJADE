package es.upm.ejercicio1DNI;

import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;

public class ParallelBehaviourMensajes extends ParallelBehaviour {

	private static final long serialVersionUID = 1L;
	protected Behaviour cyclicBehaviourImprimir;
	protected Behaviour oneShotBehaviourEnviar;
	public ParallelBehaviourMensajes() {
		super();
		//El comportamiento paralelo contiene 2 sub-comportamientos, cada uno de ellos se encapsula en un hilo de ejecución
		ThreadedBehaviourFactory threadedBehaviourFactory;
		//Sub-comportamiento 1, del tipo CyclicBehaviour
		threadedBehaviourFactory=new ThreadedBehaviourFactory();
		cyclicBehaviourImprimir=new CyclicBehaviourImprimir();
		addSubBehaviour(threadedBehaviourFactory.wrap(cyclicBehaviourImprimir));
		//Sub-comportamiento 2, del tipo OneShotBehaviour
		threadedBehaviourFactory=new ThreadedBehaviourFactory();
		oneShotBehaviourEnviar=new OneShotBehaviourEnviar();
		addSubBehaviour(threadedBehaviourFactory.wrap(oneShotBehaviourEnviar));
	}
}
