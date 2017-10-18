import java.io.*;
import java.net.*;


public class EmailSender {
	public static void main(String[] args) throws Exception {
		//Establish a TCP connection with the mail server.
		Socket socket = new Socket("127.0.0.1", 25); 
		
		//Create a BufferedReader to read a line at a time.
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		//Read greeting from the server.
		String response = br.readLine();
		System.out.println(response);
		if (!response.startsWith("220")) {
			throw new Exception("220 reply not recieved from the server.");
		}
		
		//get a reference to the socket's output stream.
		OutputStream os = socket.getOutputStream();
		
		//send HELO command and get server response.
		String command = "helo test@yahoo.com\r\n";
		System.out.println(command);
		os.write(command.getBytes("US-ASCII"));
		response = br.readLine();
		System.out.println(response);
		if (!response.startsWith("250")) {
			throw new Exception("250 reply not received from the server.");	
		}
		
		//send MAIL FROM command.
		command = "mail from: dassowb041@my.uwstout.edu\r\n";
		System.out.println(command);
		os.write(command.getBytes("US-ASCII"));
		response = br.readLine();
		System.out.println(response);
		if (!response.startsWith("250")) {
			throw new Exception("250 reply not received from the server.");	
		}
		
		//Send RCPT TO command.
		command = "rcpt to: briandassow@yahoo.com\r\n";
		System.out.println(command);
		os.write(command.getBytes("US-ASCII"));
		response = br.readLine();
		System.out.println(response);
		if (!response.startsWith("250")) {
			throw new Exception("250 reply not received from the server.");	
		}
		
		//send DATA command.
		command = "data\r\n";
		System.out.println(command);
		os.write(command.getBytes("US-ASCII"));
		response = br.readLine();
		System.out.println(response);
		if (!response.startsWith("354")) {
			throw new Exception("354 reply not received from the server.");	
		}

		
		//Send message data
		command = "Subject: Telnet Subject\nHi,\n\nThis is a test email from Telnet.\n\r\nFrom,\n\nMyself\r\n.\r\n";
		System.out.println(command);
		os.write(command.getBytes("US-ASCII"));
		response = br.readLine();
		System.out.println(response);
		if (!response.startsWith("250")) {
			throw new Exception("250 reply not received from the server.");	
		}
		
		//send QUIT command.
		command = "quit\r\n";
		System.out.println(command);
		os.write(command.getBytes("US-ASCII"));
		response = br.readLine();
		System.out.println(response);
		if (!response.startsWith("221")) {
			throw new Exception("221 reply not received from the server.");	
		}
	}
}