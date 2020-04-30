package es.upm.ejercicio1DNI;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.content.lang.sl.SLCodec;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class AgenteCorreo extends Agent {

	public void setup()
	{
		//Crear servicios proporcionados por el agentes y registrarlos en la plataforma
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setName("Correo");
		//Establezco el tipo del servicio para poder localizarlo cuando haga una busqueda
		sd.setType("mensajería");
		// Incorporo una ontología (aunque en realidad no la hemos definido)
		sd.addOntologies("ontologia");
		// Los agentes que quieran utilizar el servicio deberán usar el lenguaje de contenido FIPA-SL
		sd.addLanguages(new SLCodec().getName());
		dfd.addServices(sd);

		//Enviamos el nuevo servicio al agente DF para que se realice el registro
		try {
			//registro los servicios
			DFService.register(this, dfd);
		} catch(FIPAException e) {
			System.err.println("Agente "+getLocalName()+": "+e.getMessage());
		}
		//Añadimos el comportamiento paralelo que indica el enunciado (lo tendremos que crear en otro fichero)
		addBehaviour(new ParallelBehaviourMensajes());
	}

}
