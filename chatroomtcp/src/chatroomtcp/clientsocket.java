package chatroomtcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

class clientsocket {
	Socket s ;
	BufferedReader in;
	PrintWriter out;
	
	clientsocket() throws UnknownHostException, IOException
	{
		s=new Socket("172.22.8.85",9999);
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		out  = new PrintWriter(s.getOutputStream());
	}
	void sendtext(String s)
	{
		out.println(s);
		out.flush();
	}
	String  revievetext()
	{
		try {
			return in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
