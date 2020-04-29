package es.upm.ejemplo;

import jade.core.*;
import jade.lang.acl.*;
import jade.domain.FIPAException;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.util.leap.Iterator;

import javax.swing.JOptionPane;;

public class AgenteBuscarRegistro extends Agent {
	
	protected void setup() {
		System.out.println("Agente " + getLocalName() + "\nBuscando servicios registrados");
		doWait(1000);
		buscaServicios();
		
		System.out.println("");
		System.out.println("Buscamos agentes que ofrezcan servicios del tipo Imprimir");
		DFAgentDescription d = buscarAgente("Imprimir");
		System.out.println("Búsqueda de agentes que ofrezcan servicios del tipo Imprimir finalizada");
	}
	
	private void buscaServicios() {
		DFAgentDescription dfd = new DFAgentDescription();
		
		try {
			DFAgentDescription[] result = DFService.search(this,dfd);
			System.out.println("Total servicios Encontrados: " + result.length);
			
			for(int i=0;i<result.length;i++) {
				String out = result[i].getName()+ " proporciona ";
				Iterator iter = result[i].getAllServices();
				
				while (iter.hasNext()) {
					ServiceDescription sd = (ServiceDescription) iter.next();
					System.out.println(getLocalName() + out + ": " + sd.getName());
				}
			}
		} catch(Exception ex) {
			System.err.println("El Agente :" + getLocalName()+ " no ha podido encontrar servicios:" + ex.getMessage());
					doDelete();
		}
	}
	
	protected DFAgentDescription buscarAgente(String tipo) {
		//Indico las características el tipo de servicio que quiero encontrar
		DFAgentDescription template=new DFAgentDescription();
		ServiceDescription templateSd=new ServiceDescription();
		//Como define el tipo el agente coordinador también podríamos buscar por nombre
		templateSd.setType(tipo);
		template.addServices(templateSd);
		//Es posible establecer restricciones en la búsqueda, por ejemplo que el máximo de resultados sea 1
		SearchConstraints sc = new SearchConstraints();
		sc.setMaxResults(new Long(1));
		try {
			DFAgentDescription [] results = DFService.search(this, template, sc);
			if (results.length > 0) {
				System.out.println("Agente "+getLocalName()+" encontro los siguientes agentes");
				for (int i = 0; i < results.length; ++i) {
					DFAgentDescription dfd = results[i];
					AID provider = dfd.getName();
					//un mismo agente puede proporcionar varios servicios, solo estamos interesados en "tipo"
					Iterator it = dfd.getAllServices();
					while (it.hasNext()) {
						ServiceDescription sd = (ServiceDescription) it.next();
						if (sd.getType().equals(tipo)) {
							System.out.println("- Servicio \""+sd.getName()+"\" proporcionado por el agente "+provider.getName());
							return dfd;
						}
					}
				}
			}
			else {
				//JOptionPane.showMessageDialog(null, "Agente "+getLocalName()+" no encontro ningun servicio buscador", "Error",
				//JOptionPane.INFORMATION_MESSAGE;
			}
		}
		catch(FIPAException e) {
			//JOptionPane.showMessageDialog(null, "Agente "+getLocalName()+": "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return null;
	}
}
