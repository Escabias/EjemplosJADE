package es.upm.ejercicio1DNI;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class CyclicBehaviourImprimir extends CyclicBehaviour {

	public void action() {
		// Creamos un mensaje de espera bloqueante que contiene un filtro AND: mensajes de tipo request y ontología “ontología”
		ACLMessage
		msg=this.myAgent.blockingReceive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
				MessageTemplate.MatchOntology("ontologia")));
		//Cuando llega un mensaje al agente que cumple las condiciones del filtro, se despierta e imprime el contenido en pantalla
		try {
			System.out.println("Soy el agente: " + myAgent.getName());
			System.out.println("Recibo un mensaje del agente: " + msg.getSender().getName()+":"+
					(String)msg.getContentObject());
		} catch (UnreadableException e) {
			e.printStackTrace();
		}
	}

}
