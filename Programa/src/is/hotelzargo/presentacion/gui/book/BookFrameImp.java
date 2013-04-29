package is.hotelzargo.presentacion.gui.book;

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
public class BookFrameImp extends BookFrame {

	private JButton addBookButton;
	private JButton delBookButton;
	private JButton modBookButton;
	private JButton listBookButton;
	private JButton findBookButton;
	private JButton confirmBookButton;
	private JButton exit;
	
	private BookFormAdd addForm;
	private BookFormDel delForm;
	private BookFormMod modForm;
	private BookFormList listForm;
	private BookFormFind findForm;
	private BookFormConfirm confirmForm;
	
	public BookFrameImp() {
		
		this.setTitle("Reservas");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		/* formularios */
		addForm = new BookFormAdd(this,true);
		delForm = new BookFormDel(this,true);
		modForm = new BookFormMod(this,true);
		listForm = new BookFormList(this,true);
		findForm = new BookFormFind(this,true);
		confirmForm = new BookFormConfirm(this,true);
		
		/* botones */
		
		addBookButton = new JButton("Dar de alta");
		delBookButton = new JButton("Dar de baja");
		modBookButton = new JButton("Modificar");
		listBookButton = new JButton("Listar");
		findBookButton = new JButton("Buscar disponibilidad");
		confirmBookButton = new JButton("Confirmar reserva");
		exit = new JButton("Salir");
		
		setListener();
		
		this.setLayout(new GridLayout(7, 1, 5, 5));
		this.add(addBookButton);
		this.add(delBookButton);
		this.add(modBookButton);
		this.add(listBookButton);
		this.add(findBookButton);
		this.add(confirmBookButton);
		this.add(exit);
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(d.width/2 - this.getWidth()/2, d.height/2 - this.getHeight()/2);
		
		this.pack();
	}
	
	private void addBook() {
		addForm.setVisible(true);
	}
	
	private void delBook(){
		delForm.setVisible(true);
	}
	
	private void modBook() {
		modForm.setVisible(true);
	}
	
	private void listBook(){
		listForm.setVisible(true);
	}
	
	private void findBook(){
		findForm.setVisible(true);
	}
	
	private void confirmBook(){
		confirmForm.setVisible(true);
	}
	
	private void exit() {
		Controller.getInstance().event(Event.SHOW_BOOK_FRAME,false);
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
		
		this.addBookButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addBook();
			}
		});
		
		this.delBookButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				delBook();
			}
		});
		
		this.modBookButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modBook();
			}
		});
		
		this.listBookButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				listBook();
			}
		});
		
		this.findBookButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				findBook();
			}
		});
		
		this.confirmBookButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				confirmBook();
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
