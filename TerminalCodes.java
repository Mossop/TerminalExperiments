import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class TerminalCodes extends Thread
{
	Socket client;
	
	public TerminalCodes(Socket client)
	{
		this.client=client;
		start();
	}
	
	public void start()
	{
		try
		{
			PrintWriter out = new PrintWriter(client.getOutputStream());
			InputStream in = client.getInputStream();
			int data;
			while ((data=in.read())>=0)
			{
				out.println(data);
				out.flush();
			}
		}
		catch (IOException e)
		{
		}
	}
	
	public static void main(String[] args)
	{
		try
		{
			ServerSocket listener = new ServerSocket(6715);
			while (true)
			{
				Socket client = listener.accept();
				new TerminalCodes(client);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}	
}
