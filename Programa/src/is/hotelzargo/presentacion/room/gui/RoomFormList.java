package is.hotelzargo.presentacion.room.gui;

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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class RoomFormList extends JDialog {
	
	private JScrollPane scrollPane;
	
	private JPanel renderPanel;
	
	private JButton exitButton;
	
	private RenderList renderList;
	
	private JList list;
	
	public RoomFormList(JFrame owner, boolean mod){
		super(owner,mod);
		this.setTitle("Lista de habitaciones");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(owner);
		
		renderList = new RenderList();
		
		renderPanel = new JPanel();
		
		scrollPane = new JScrollPane(renderPanel,
									ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
									ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		
		exitButton = new JButton("Salir");
		
		list = new JList();
		
		addListener();
		
		this.setLayout(new GridLayout(1, 1));
		this.add(scrollPane);
		//this.add(exitButton);
		
		this.setSize(500,300);
		
		//this.pack();
		
	}
	
	private void setText(){
		@SuppressWarnings("unchecked")
		Vector<RoomTransfer> roomList = //new Vector<ClientTransfer>(); 
				(Vector<RoomTransfer>) Controller.getInstance().event(Event.LIST_ROOM,null,null);
				/*
				for(int j = 0; j < 10 ; j++){
					clientList.add(createT());
				}*/
		DefaultListModel model = new DefaultListModel();
				String text[] = new String[roomList.size()];
				if(!roomList.isEmpty()){
					for(int i = 0; i < roomList.size(); i++){
						RoomTransfer t = roomList.elementAt(i);
						
						text[i] = "Habitacion"+System.getProperty("line.separator")+
								  "ID: "+t.getId()+System.getProperty("line.separator")+
								  "Numero: "+t.getnumRoom()+System.getProperty("line.separator")+
								  "Camas: "+t.getnumBeds()+System.getProperty("line.separator")+
								  "Precio: "+t.getPrice();
						model.addElement(text[i]);
					}
				}
				else{
					text[0] = "No hay habitaciones";
				}
				
				renderPanel.setLayout(new BorderLayout());
				
				list.setModel(model);
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
