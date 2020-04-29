package es.upm.ejemplo;

import jade.core.Agent;
import jade.domain.FIPAException;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAAgentManagement.DFAgentDescription;

public class AgRegistrar extends Agent {

	public String servicio_param;
	
	protected void setup() {
		Object [] arg = getArguments();
		servicio_param = (String) arg[0];
		System.out.println("Agente " + getLocalName() + ". Registro el servicio: " + servicio_param + " en el DF");
		registerServiceNuevo(servicio_param);
	}
	
	private void registerServiceNuevo(String servicio) {
		
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(this.getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType(servicio);
		sd.setName(servicio);
		dfd.addServices(sd);
		
		try {
			DFService.register(this,dfd);
		} catch(FIPAException ex) {
			System.err.println("El Agente :" + getLocalName()+ " no ha podido registrar el servicio " + ex.getMessage());
					doDelete();
		}
	}
}
