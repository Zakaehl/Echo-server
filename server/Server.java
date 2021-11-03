package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	private ServerSocket server;
	private Socket client;
	private int port = 2400;
	private Scanner in;
	private PrintWriter out;
	
	private void start() {
		try {
			server = new ServerSocket(port);
			System.out.println("Server collegato alla porta " + port);
			client = server.accept();
			System.out.println("Connessione con il client stabilita");
			
			in = new Scanner(client.getInputStream());
			out = new PrintWriter(client.getOutputStream());
			System.out.println("Gestiti gli stream");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while (true) {
			String msg_in = in.nextLine();
			System.out.println("Ricevuto messaggio " + msg_in);
			if (msg_in.equals("quit")) {
				System.out.println("Connessione interrotta dal client");
				break;
			}
			out.println(msg_in.toUpperCase());
			out.flush();
			System.out.println("Inviato messaggio di risposta");
		}
		
	    in.close();
	    out.close();
		try {
			client.close();
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Disconnessione");
	}
	
	public static void main(String[] args) {
		Server s = new Server();
		s.start();
	}
	
}
