package chatroomtcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class chat implements Runnable
{
	BufferedReader in;
	static PrintWriter out[] = new PrintWriter[10];
	static Socket s[];
	int n;
	static int noconnected;
	chat(int n,Socket s[],int noconnected,PrintWriter out[])
	{
		this.noconnected =noconnected;
		this.n=n;
		this.s=s;
		this.out=out;
		try {
			in = new BufferedReader(new InputStreamReader(s[n].getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while(true)
		{
			try {
				String str =in.readLine();
				for(int i=0;i<=noconnected;i++)
				{
					if(i!=n)
					{
						out[i].println(str);
						out[i].flush();
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class serversocket{

	public static void main(String[] args) {
		Socket s[] = new Socket[10];
		PrintWriter out[] = new PrintWriter[10];
		int noconnected = 0;
		chat c[] = new chat[10];
		try {
			ServerSocket ss = new ServerSocket(9998);
			while(true)
			{
				s[noconnected]=ss.accept();
				out[noconnected]=new PrintWriter(s[noconnected].getOutputStream(),true);
				new Thread(new chat(noconnected,s,noconnected,out)).start();
				noconnected++;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}