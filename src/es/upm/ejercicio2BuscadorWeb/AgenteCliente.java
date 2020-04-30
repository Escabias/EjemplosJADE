package es.upm.ejercicio2BuscadorWeb;

import jade.core.Agent;
import java.util.List;
import java.util.Scanner;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class AgenteCliente extends Agent {

	public void setup()
	{
		System.out.println("Soy el agente Cliente");
		addBehaviour(new CyclicBehaviour(this){
			public void action()
			{
				// Leemos el texto que introduce el usuario por pantalla y lo enviamos al agente Agente Buscador
				Scanner scanner=new Scanner(System.in);
				System.out.print("Introduzca el texto a buscar: ");
				String temp=scanner.nextLine();
				Utils.enviarMensaje(this.myAgent, "buscar", temp);
				ACLMessage
				msg=blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
				//Cuando el agente AgenteBuscador responde, imprimimos su respuesta por pantalla
				try
				{
					List<String> mensajes=(List<String>)msg.getContentObject();
					for(int i=0;i<mensajes.size();i++)
						System.out.println(mensajes.get(i));
				}
				catch (UnreadableException e)
				{
					e.printStackTrace();
				}
			}
		});
	}

}
