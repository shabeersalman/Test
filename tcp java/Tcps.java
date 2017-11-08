import java.io.*;
import java.net.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.event.*;
import javax.swing.JLabel;

class Tcps extends JFrame implements ActionListener
{
	int port;

	JPanel panel;
	JButton b1;
	public static JTextArea ta,ta1;
	byte[] receiveData = new byte[1024];
	byte[] sendData = new byte[1024];
	public static BufferedReader inFromClient;
	public static String capitalizedSentence;
	public static Socket connectionSocket;
	public static DataOutputStream outToClient;
	public static String clientSentence;
	public static ServerSocket serverSocket;
	public static String sentence;
	public Tcps()
	{        
		panel=new JPanel();
		b1 = new JButton("Send");
		ta = new JTextArea();                                          
		ta1 = new JTextArea();
		this.setSize(500,500);
		this.setVisible(true);
		this.setTitle("server");
		panel.setLayout(null);
		this.add(panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		b1.setBounds(375, 400, 95, 30);
		panel.add(b1);
		//Button event
		b1.addActionListener(this);
		ta.setBounds(20, 20, 450, 360);
		panel.add(ta);
		ta1.setBounds(20, 400, 340, 30);
		panel.add(ta1);


	} 

	public void actionPerformed(ActionEvent e) {

		try
		{
			outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			sentence = ta1.getText();
			outToClient.writeBytes(sentence +'\n');
			ta1.setText("");
		}catch(IOException e1){
			e1.printStackTrace();
		}
	}





	public static void main(String args[]) throws Exception
	{
		Tcps o = new Tcps();
		//Socket-------------
		serverSocket = new ServerSocket(45454);
		connectionSocket = serverSocket.accept();
		//Server waiting for message
		while(true){
			try
			{       
				inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				clientSentence = inFromClient.readLine();
				//Set received message to the TextArea
				ta.setText("client:"+clientSentence);
			}catch(SocketException e){
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}
