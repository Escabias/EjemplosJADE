package es.upm.ejercicio2BuscadorWeb;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import jade.content.lang.sl.SLCodec;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPAAgentManagement.Envelope;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

public class CyclicBehaviourBuscador extends CyclicBehaviour {

	private static final long serialVersionUID = 1L;
	public static final int LIMITES=50;

	public void action() {
		// Creamos la espera de mensajes en modo bloqueante y con un filtro de tipo REQUEST
		ACLMessage msg=this.myAgent.blockingReceive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
		try
		{
			//Imprimimos por pantalla el texto a buscar y creamos una lista de posibles respuestas.
			System.out.println(msg.getSender().getName()+":"+ (String)msg.getContentObject());
			// Llamamos a nuestro método buscarCadena(), que utilizamos para realizar la busqueda
			List<String> respuesta=buscarCadena((String)msg.getContentObject());
			//Cuando la búsqueda ha finalizado, enviamos un mensaje de respuesta
			ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
			aclMessage.addReceiver(msg.getSender());
			aclMessage.setOntology("ontologia");
			aclMessage.setLanguage(new SLCodec().getName());
			aclMessage.setEnvelope(new Envelope());
			aclMessage.getEnvelope().setPayloadEncoding("ISO8859_1");
			aclMessage.setContentObject((Serializable)respuesta);
			this.myAgent.send(aclMessage);
		}
		catch (UnreadableException e)
		{
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> buscarCadena(String cadena)
	{
		//Definimos la lista de sitios web que vamos a utilizar
		String
		sitios[]={"http://www.dia.fi.upm.es/masteria/","http://www.upm.es","http://www.fi.upm.es","https://www.elmundo.es","https://www.elpais.com"};
				List<String> lista=new ArrayList<String>();
		Scanner scanner;
		String texto, temp;
		//Buscamos en cada una de las páginas web si hay coincidencia con el texto que ha enviado el cliente
		for(int i=0;i<sitios.length;i++){
			try {
				URL url=new URL(sitios[i]);
				scanner=new Scanner(url.openStream());
				while(true)
				{
					//a la izquierda tienes > o <\ y a la derecha > o <\
					temp=scanner.findWithinHorizon(Pattern.compile("[^><\"]*"+cadena+"[^><\"]*"), 0);
					if(temp==null)
						break;
					lista.add(temp);
				}
			}
			catch (FileNotFoundException e) {e.printStackTrace();}
			catch (MalformedURLException e) {e.printStackTrace();}
			catch (IOException e) {e.printStackTrace();}
		}
		return lista;
		}

	}
