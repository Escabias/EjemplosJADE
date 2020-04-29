package es.upm.ejemplo;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class AgenteSender extends Agent {

	
	protected void setup() {
		System.out.println("Agente agenteSender");
		System.out.println("AID: "+getAID());
		System.out.println("Nombre AID: "+getAID().getName());
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		AID r = new AID();
		
		
		r.setName("agenteReceiver@192.168.1.133:1099/JADE");
		// r.addAddresses("http://192.168.11.100:7778/acc");
		System.out.println("Envio mensaje a: "+r.getName());
		msg.addReceiver(r);
		msg.setContent("Mensaje de prueba");
		this.send(msg);
		System.out.println("Mensaje Enviado....");
		
		addBehaviour(new ResponderBehaviourMio(this));

	}
}
