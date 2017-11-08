import java.io.*;
import java.net.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.event.*;
import javax.swing.JLabel;
class Tcpc extends JFrame implements ActionListener
{
	
	
	JPanel panel;
	JButton b1;
	public static JTextArea ta,ta1;
	public static byte[] sendData = new byte[1024];
	public static byte[] receiveData = new byte[1024];
	public static String sentence,modifiedSentence;
	public static BufferedReader inFromUser;
	public static DataOutputStream outToServer;
	public static Socket connectionSocket;
	public static InetAddress IPAddress;
	public static BufferedReader inFromServer;
	public static DatagramPacket sendPacket,receivePacket;
	public Tcpc()
	{
		panel=new JPanel();
		b1 = new JButton("Send");
		ta = new JTextArea();                                          
		ta1 = new JTextArea();
		this.setSize(500,500);
		this.setVisible(true);
		this.setTitle("Client");
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
                  try
			{
				outToServer = new DataOutputStream(connectionSocket.getOutputStream());
				sentence = ta1.getText();
				outToServer.writeBytes(sentence + '\n');
				
				ta1.setText("");
			}catch(SocketException e1){
				e1.printStackTrace();
			}catch(IOException e2){
				e2.printStackTrace();
			}
         }
	public static void main(String args[]) throws Exception
	{
		Tcpc o = new Tcpc();
		//Get the ipAddress=-------------
		connectionSocket = new Socket("localhost",45454 );
		//Client waiting for message
		while(true){
			try
			{					
				inFromServer = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				sentence = inFromServer.readLine();
				System.out.println(sentence);
				//Set received message to the TextArea
				ta.setText("\nServer:"+sentence);
			}catch(SocketException e){
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
			}
	}
	}

