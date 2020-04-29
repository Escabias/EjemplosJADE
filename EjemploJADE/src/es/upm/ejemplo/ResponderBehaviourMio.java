package es.upm.ejemplo;

import jade.lang.acl.*;
import jade.core.Agent;
import jade.core.behaviours.*;

public class ResponderBehaviourMio extends SimpleBehaviour {

	//Establecemos un filtro para leer mensajes de tipo REQUEST
	private static final MessageTemplate mt1 = 
			MessageTemplate.or(MessageTemplate.MatchPerformative(ACLMessage.REQUEST), 
			MessageTemplate.MatchPerformative(ACLMessage.INFORM));
	
	
	public ResponderBehaviourMio(Agent agent) {
		super(agent);
	}
	
	
	@Override
	public void action() {
		while(true) {
			ACLMessage aclMessage = myAgent.receive(mt1);
			if (aclMessage!=null) {
				
				if (aclMessage.getPerformative()== ACLMessage.REQUEST){
					//Imprimimos por pantalla el contenido del mensaje recibido.
					System.out.println();
					System.out.println(myAgent.getLocalName()+": Recibo el mensaje: \n"+aclMessage);
					//Creamos un mensaje de respuesta de tipo INFORM y lo enviamos.
					 ACLMessage mr = aclMessage.createReply();
					 mr.setContent("Respuesta al mensaje");
					 mr.setPerformative(ACLMessage.INFORM);
					 myAgent.send(mr);
				} else {
					if (aclMessage.getPerformative()== ACLMessage.INFORM){
						System.out.println();
						System.out.println(myAgent.getLocalName()+": Recibo el mensaje:\n"+aclMessage);
						ACLMessage mr = aclMessage.createReply();
						mr.setContent("Respuesta al mensaje con REQUEST");
						mr.setPerformative(ACLMessage.REQUEST);
						myAgent.send(mr);
					}
				}

			} else {
				this.block();
			}
		}
	}

	@Override
	public boolean done() {
		return false;
	}

}
