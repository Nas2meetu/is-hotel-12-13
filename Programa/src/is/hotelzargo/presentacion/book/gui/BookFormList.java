package is.hotelzargo.presentacion.book.gui;

import is.hotelzargo.negocio.book.transfer.BookTransfer;
import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;
import is.hotelzargo.presentacion.maingui.RenderList;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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
public class BookFormList extends JDialog {
	
	private JScrollPane scrollPane;
	
	private JPanel renderPanel;
	
	private JButton exitButton;
	
	private RenderList renderList;
	
	private JList list;
	
	public BookFormList(JFrame owner, boolean mod){
		super(owner,mod);
		this.setTitle("Lista de reservas");
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
	
	private void exit(){
		this.setVisible(false);
		//listTextArea.setText("");
	}
	
	private void setText(){
		@SuppressWarnings("unchecked")
		Vector<BookTransfer> bookList = //new Vector<BookTransfer>(); 
				(Vector<BookTransfer>) Controller.getInstance().event(Event.LIST_BOOK,null,null);
				
				/*for(int j = 0; j < 10 ; j++){
					bookList.add(createT());
				}*/
		DefaultListModel model = new DefaultListModel();
				String text[] = new String[bookList.size()];
				if(!bookList.isEmpty()){
					for(int i = 0; i < bookList.size(); i++){
						BookTransfer t = bookList.elementAt(i);
					
						text[i] ="Reserva"+System.getProperty("line.separator")+
								 "ID: "+((BookTransfer) t).getIdBook()+System.getProperty("line.separator")+
								 "Cliente: "+((BookTransfer) t).getIdClient()+System.getProperty("line.separator")+
								 "Fianza: "+((BookTransfer) t).getDeposit()+System.getProperty("line.separator")+
								 "Numero de personas: "+((BookTransfer) t).getNumPerson();
						model.addElement(text[i]);
					}
				}
				else{
					model.addElement("No hay reservas");
				}
				
				renderPanel.setLayout(new BorderLayout());

				list.setModel(model);
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
		
		this.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				setText();
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				
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
