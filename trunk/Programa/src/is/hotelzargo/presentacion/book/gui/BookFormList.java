package is.hotelzargo.presentacion.book.gui;

import is.hotelzargo.negocio.book.transfer.BookTransfer;
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
public class BookFormList extends JDialog {
	
	private JTextArea listTextArea;
	private JScrollPane scrollPane;
	
	private JPanel renderPanel;
	
	private JButton exitButton;
	
	private RenderList renderList;
	
	public BookFormList(JFrame owner, boolean mod){
		super(owner,mod);
		this.setTitle("Listar Reservas");
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
	
	
	private void exit(){
		this.setVisible(false);
		//listTextArea.setText("");
	}
	
	private BookTransfer createT(){
		return new BookTransfer(-1,null,123,null,null,100,2,null,true);
	}
	
	private void setText(){
		Vector<BookTransfer> bookList = new Vector<BookTransfer>(); 
				//(Vector<BookTransfer>) Controller.getInstance().event(Event.LIST_BOOK,null,null);
				
				for(int j = 0; j < 10 ; j++){
					bookList.add(createT());
				}
				
				String text[] = new String[bookList.size()];
				if(bookList != null){
					for(int i = 0; i < bookList.size(); i++){
						BookTransfer t = bookList.elementAt(i);
					
						text[i] =((BookTransfer) t).getIdBook()+System.getProperty("line.separator")+
								 ((BookTransfer) t).getIdClient()+System.getProperty("line.separator")+
								 ((BookTransfer) t).getDeposit()+System.getProperty("line.separator")+
								 ((BookTransfer) t).getNumPerson();
					}
				}
				else{
					text[0] = "No hay clientes";
				}
				
				renderPanel.setLayout(new BorderLayout());
				
				JList list = new JList(text);
		        list.setCellRenderer(renderList);
		        renderPanel.add(list, BorderLayout.CENTER);
		        //renderPanel.add(list);
				
		        //this.pack();
		        
				//setTextArea(text);
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
