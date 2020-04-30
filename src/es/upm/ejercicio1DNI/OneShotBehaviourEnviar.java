package es.upm.ejercicio1DNI;

import jade.core.behaviours.OneShotBehaviour;
import java.util.Scanner;
import jade.core.behaviours.CyclicBehaviour;

public class OneShotBehaviourEnviar extends OneShotBehaviour {

	public void action() {
		// Definimos la captura de texto por teclado
		Scanner scanner=new Scanner(System.in);
		String mensaje;
		//Leemos por pantalla y enviamos un mensaje al resto de agentes registrados. El envío debe estar implementado en Utils
		while(true) {
			System.out.println("Introduzca su DNI: ");
			mensaje=scanner.nextLine();
			//mensaje=JOptionPane.showInputDialog("Introduzca su DNI");
			Utils.enviarMensaje(this.myAgent, "mensajería", mensaje);
			System.out.println("Soy el agente: " + myAgent.getName() + " Envío mensaje.");
			//JOptionPane.showMessageDialog(null, "Agente "+myAgent.getLocalName()+" Envío mensaje.
			//DNI: " + mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	@Override
	public int onEnd() {
		reset();
		return super.onEnd();
	}

}
