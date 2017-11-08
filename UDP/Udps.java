import java.io.*;
import java.net.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.event.*;
import javax.swing.JLabel;

class Udps extends JFrame implements ActionListener
{
	public static int port;

	public static JPanel panel;
	public static JButton b1;
	public static JTextArea ta,ta1;
	public static byte[] receiveData = new byte[1024];
	 public static byte[] sendData = new byte[1024];
	public  static DatagramPacket receivePacket;
	public static InetAddress IPAddress;
	public static String capitalizedSentence;
	public static DatagramPacket sendPacket;
	public static DatagramSocket serverSocket;
	public static String sentence;
	public Udps()
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
		b1.addActionListener(this);
		ta.setBounds(20, 20, 450, 360);
		panel.add(ta);
		ta1.setBounds(20, 400, 340, 30);
		panel.add(ta1);

	}  
	public void actionPerformed(ActionEvent e) {

		try{
			sentence = ta1.getText();
			sendData = sentence.getBytes();
			sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			serverSocket.send(sendPacket);
			ta1.setText("");
		}catch(IOException e1){
			e1.printStackTrace();
		}

	}

	public static void main(String args[]) throws Exception
	{
		Udps o = new Udps();
		//Socket-------------
		serverSocket = new DatagramSocket(9876);
		//Server waiting for message
		while(true){
			try
			{

				receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
				sentence = new String( receivePacket.getData());
				//Set received message to the TextArea
				ta.setText("\nClient:"+sentence);
			}catch(SocketException e){
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
			IPAddress = receivePacket.getAddress();
			port = receivePacket.getPort();
		}
	}
}
