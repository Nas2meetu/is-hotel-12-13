package is.hotelzargo.presentacion.room.gui;

import is.hotelzargo.negocio.client.transfer.ClientTransfer;
import is.hotelzargo.negocio.client.transfer.ClientTransferCompany;
import is.hotelzargo.negocio.client.transfer.ClientTransferIndividual;
import is.hotelzargo.negocio.room.transfer.RoomTransfer;
import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;
import is.hotelzargo.presentacion.maingui.RenderList;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class RoomFormList extends JDialog {
	
	private JTextArea listTextArea;
	private JScrollPane scrollPane;
	
	private JPanel renderPanel;
	
	private JButton exitButton;
	
	private RenderList renderList;
	
	public RoomFormList(JFrame owner, boolean mod){
		super(owner,mod);
		this.setTitle("Listar Clientes");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(owner);
		
		renderList = new RenderList();
		
		renderPanel = new JPanel();
		
		scrollPane = new JScrollPane(renderPanel,
									ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
									ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		exitButton = new JButton("Salir");
		
		addListener();
		
		this.setLayout(new GridLayout(1, 1));
		this.add(scrollPane);
		//this.add(exitButton);
		
		this.setSize(500,300);
		
		//this.pack();
		
	}
	
	private void setText(){
		Vector<RoomTransfer> roomList = //new Vector<ClientTransfer>(); 
				(Vector<RoomTransfer>) Controller.getInstance().event(Event.LIST_ROOM,null,null);
				/*
				for(int j = 0; j < 10 ; j++){
					clientList.add(createT());
				}*/
				
				String text[] = new String[roomList.size()];
				if(roomList != null){
					for(int i = 0; i < roomList.size(); i++){
						RoomTransfer t = roomList.elementAt(i);
						
						text[i] = t.getId()+System.getProperty("line.separator")+
								  t.getnumRoom()+System.getProperty("line.separator")+
								  t.getnumBeds()+System.getProperty("line.separator")+
								  t.getPrice();
					}
				}
				else{
					text[0] = "No hay habitaciones";
				}
				
				renderPanel.setLayout(new BorderLayout());
				
				JList list = new JList(text);
		        list.setCellRenderer(renderList);
		        renderPanel.add(list, BorderLayout.CENTER);
	}
	
	private void exit(){
		this.setVisible(false);
		//listTextArea.setText("");
	}
	
	private void addListener(){
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				setText();
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				exit();
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				
			}
		});
				
		exitButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				exit();
			}
		});
	}
}
