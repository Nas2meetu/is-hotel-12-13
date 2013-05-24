package is.hotelzargo.presentacion.employee.gui;

import is.hotelzargo.negocio.client.transfer.ClientTransfer;
import is.hotelzargo.negocio.client.transfer.ClientTransferCompany;
import is.hotelzargo.negocio.client.transfer.ClientTransferIndividual;
import is.hotelzargo.negocio.employee.transfer.EmployeeTransfer;
import is.hotelzargo.negocio.employee.transfer.EmployeeTransferAdmin;
import is.hotelzargo.negocio.employee.transfer.EmployeeTransferServices;
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
public class EmployeeFormList extends JDialog {
	
	private JTextArea listTextArea;
	private JScrollPane scrollPane;
	
	private JPanel renderPanel;
	
	private JButton exitButton;
	
	private RenderList renderList;
	
	public EmployeeFormList(JFrame owner, boolean mod){
		super(owner,mod);
		this.setTitle("Lista de empleados");
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
		Vector<EmployeeTransfer> clientList = //new Vector<ClientTransfer>(); 
				(Vector<EmployeeTransfer>) Controller.getInstance().event(Event.LIST_EMPLOYEE,null,null);
		
		String text[] = new String[clientList.size()];
		if(clientList != null){
			for(int i = 0; i < clientList.size(); i++){
				EmployeeTransfer t = clientList.elementAt(i);
				if (t instanceof EmployeeTransferAdmin)
					text[i] = ((EmployeeTransferAdmin) t).getName()+System.getProperty("line.separator")+
							  ((EmployeeTransferAdmin) t).getSurname()+System.getProperty("line.separator")+
							  ((EmployeeTransferAdmin) t).getDNI()+System.getProperty("line.separator")+
							  ((EmployeeTransferAdmin) t).getPhone()+System.getProperty("line.separator")+
							  ((EmployeeTransferAdmin) t).getShift()+System.getProperty("line.separator")+
							  ((EmployeeTransferAdmin) t).getPay();
				else
					text[i] =((EmployeeTransferServices) t).getName()+System.getProperty("line.separator")+
							 ((EmployeeTransferServices) t).getSurname()+System.getProperty("line.separator")+
							 ((EmployeeTransferServices) t).getDNI()+System.getProperty("line.separator")+
							 ((EmployeeTransferServices) t).getPhone()+System.getProperty("line.separator")+
							 ((EmployeeTransferServices) t).getShift()+System.getProperty("line.separator")+
							 ((EmployeeTransferServices) t).getPay();
			}
		}
		else{
			text[0] = "No hay empleados";
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
