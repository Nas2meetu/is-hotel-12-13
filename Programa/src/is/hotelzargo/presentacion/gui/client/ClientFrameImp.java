package is.hotelzargo.presentacion.gui.client;

import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class ClientFrameImp extends ClientFrame {

	private JButton addClientButton;
	private JButton delClientButton;
	private JButton modClientButton;
	private JButton listClientButton;
	private JButton exit;
	
	private ClientFormAdd addForm;
	private ClientFormDel delForm;
	private ClientFormList listForm;
	
	public ClientFrameImp() {
		
		this.setTitle("Clientes");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		/* formularios */
		
		addForm = new ClientFormAdd(this,true);
		delForm = new ClientFormDel(this,true);
		listForm = new ClientFormList(this,true);
		
		/* botones */
		
		addClientButton = new JButton("Dar de alta");
		delClientButton = new JButton("Dar de baja");
		modClientButton = new JButton("Modificar");
		listClientButton = new JButton("Listar");
		exit = new JButton("Salir");
		
		setListener();
		
		this.setLayout(new GridLayout(5, 1, 5, 5));
		this.add(addClientButton);
		this.add(delClientButton);
		this.add(modClientButton);
		this.add(listClientButton);
		this.add(exit);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(d.width/2 - this.getWidth()/2, d.height/2 - this.getHeight()/2);
		
		this.pack();
	}
	
	private void addClient() {
		addForm.setVisible(true);
	}
	
	private void delClient(){
		delForm.setVisible(true);
	}
	
	private void listClient() {
		listForm.setVisible(true);
	}
	
	private void exit() {
		Controller.getInstance().event(Event.SHOW_CLIENT_FRAME,false);
	}
	
	private void setListener(){
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				exit();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				
			}
		});
		
		this.addClientButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addClient();
			}
		});
		
		this.delClientButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				delClient();
			}
		});
		
		this.listClientButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listClient();
			}
		});
	
		this.exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
	}


}
