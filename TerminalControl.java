import java.nio.channels.SocketChannel;

public class TerminalControl
{
	private Session session;
	private SocketChannel socket;
	
	public TerminalControl(SocketChannel socket, Session session)
	{
		this.socket=socket;
		this.session=session;
	}
}
