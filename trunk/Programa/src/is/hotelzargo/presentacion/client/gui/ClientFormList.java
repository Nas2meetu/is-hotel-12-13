package is.hotelzargo.presentacion.client.gui;

import is.hotelzargo.negocio.client.transfer.ClientTransfer;
import is.hotelzargo.negocio.client.transfer.ClientTransferCompany;
import is.hotelzargo.negocio.client.transfer.ClientTransferIndividual;
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
public class ClientFormList extends JDialog {
	
	private JTextArea listTextArea;
	private JScrollPane scrollPane;
	
	private JPanel renderPanel;
	
	private JButton exitButton;
	
	private RenderList renderList;
	
	public ClientFormList(JFrame owner, boolean mod){
		super(owner,mod);
		this.setTitle("Listar Clientes");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(owner);
		
		renderList = new RenderList();
		
		renderPanel = new JPanel();
		
		//initTextArea();
		
		exitButton = new JButton("Salir");
		
		addListener();
		
		this.setLayout(new GridLayout(2, 1));
		this.add(renderPanel);
		this.add(exitButton);
		
		this.pack();
		
	}
	
	
	private void exit(){
		this.setVisible(false);
		listTextArea.setText("");
	}
	
	private void setText(){
		Vector<ClientTransfer> clientList = 
				(Vector<ClientTransfer>) Controller.getInstance().event(Event.LIST_CLIENT,null,null);
				String text[] = {"0","0","0"};
				if(clientList != null){
					for(int i = 0; i < clientList.size(); i++){
						ClientTransfer t = clientList.elementAt(i);
						if (t instanceof ClientTransferIndividual)
							text[i] = ((ClientTransferIndividual) t).getName();
						else
							text[i] =((ClientTransferCompany) t).getCompany();
					}
				}
				else{
					text[0] = "No hay clientes";
				}
				
				JList list = new JList(text);
		        list.setCellRenderer(renderList);
		        renderPanel.add( list, BorderLayout.CENTER );
				
				//setTextArea(text);
	}
	
	private void addListener(){
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				/*Controller.getInstance().event(Event.LIST_CLIENT,null,null);
				setTextArea("holas" + "\n" + "holas 2");*/
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
	
	private void initTextArea(){
		listTextArea = new JTextArea();
		listTextArea.setEditable(false);
		//listTextArea.setText("hola");
		listTextArea.setSize(500, 500);
		scrollPane = new JScrollPane(listTextArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);	
		//scrollPaneVertical.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
	}
	
	private void setTextArea(String text){
		listTextArea.setText(text);
	}
	

}
