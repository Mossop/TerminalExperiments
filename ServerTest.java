import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.Selector;
import java.nio.channels.SelectionKey;
import java.nio.ByteBuffer;
import java.net.InetSocketAddress;
import java.util.Iterator;

public class ServerTest extends Thread
{
	/**
   * Starts the thread.
   */
	public ServerTest()
	{
		start();
	}
	
	public void run()
	{
		try
		{
			// Open a ServerSocketChannel on the right port.
			Selector selector = Selector.open();
			ServerSocketChannel listener = ServerSocketChannel.open();
			listener.configureBlocking(false);
			listener.socket().bind(new InetSocketAddress(2222));
			
			// Register it with the selector.
			listener.register(selector,SelectionKey.OP_ACCEPT);

			// Wait for up to 5 minutes for something to happen
			while (selector.select(300000)>0)
			{
				// Loop over the events
				Iterator loop = selector.selectedKeys().iterator();
				while (loop.hasNext())
				{
					SelectionKey key = (SelectionKey)loop.next();
					loop.remove();
					if (key.isAcceptable())
					{
						// This must have been the ServerSocketChannel
						assert listener==key.channel();
						
						// Get the SocketChannel connection
						SocketChannel socket = listener.accept();
						socket.configureBlocking(false);
						
						// Register it with the selector
						socket.register(selector,SelectionKey.OP_READ);
						System.out.println("Connect from "+socket.socket().getInetAddress());
					}
					else if (key.isReadable())
					{
						// This is data from a socket
						SocketChannel socket = (SocketChannel)key.channel();
						ByteBuffer buffer = ByteBuffer.allocate(100);
						System.out.println(socket.read(buffer));
						buffer.flip();
						
						// Check the buffer size
						if (buffer.remaining()>0)
						{
							System.out.println("Read");
							socket.write(buffer);
						}
						else
						{
							System.out.println("Socket closed");
						}
					}
					else
					{
						assert false;
					}
				}
			}
			System.out.println("Exiting due to lack of activity");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
   * Creates a new instance of the class.
   */
	public static void main(String[] args)
	{
		new ServerTest();
	}	
}
