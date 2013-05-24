package is.hotelzargo.presentacion.book.gui;

import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class BookFormFind extends JDialog {

	private JLabel checkinLabel;
	private JLabel checkoutLabel;
	
	private JTextField checkinText;
	private JTextField checkoutText;
	
	private JButton acceptButton;
	private JButton cancelButton;
	
	public BookFormFind(JFrame owner,boolean mod) {
		super(owner,mod);
		
		this.setTitle("Buscar disponibilidad");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(owner);
		
		checkinLabel =  new JLabel("Fecha de entrada: ");
		checkoutLabel = new JLabel("Fecha de salida:  ");
		
		checkinText = new JTextField(10);
		checkoutText = new JTextField(10);
		
		acceptButton = new JButton("Aceptar");
		cancelButton = new JButton("Cancelar");
		
		addListener();
		
		JPanel panelIn = new JPanel();
		panelIn.setLayout(new GridLayout(1,2));
		panelIn.add(checkinLabel);
		panelIn.add(checkinText);
		
		JPanel panelOut = new JPanel();
		panelOut.setLayout(new GridLayout(1,2));
		panelOut.add(checkoutLabel);
		panelOut.add(checkoutText);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new GridLayout(1,2));
		panelButtons.add(acceptButton);
		panelButtons.add(cancelButton);
		
		JPanel checkinInfoPanel = new JPanel();
		checkinInfoPanel.setLayout(new GridLayout(1,2));
		checkinInfoPanel.add(Box.createVerticalGlue());
		//checkinInfoPanel.add(new JLabel("Formato dd/mm/yy HH:mm:ss"));
		checkinInfoPanel.add(new JLabel("Ejemplo: 01/06/12 12:00:00"));
		
		JPanel checkoutInfoPanel = new JPanel();
		checkoutInfoPanel.setLayout(new GridLayout(1,2));
		checkoutInfoPanel.add(Box.createVerticalGlue());
		//checkinInfoPanel.add(new JLabel("Formato dd/mm/yy HH:mm:ss"));
		checkoutInfoPanel.add(new JLabel("Ejemplo: 01/06/12 12:00:00"));
		
		this.setLayout(new GridLayout(5,1));
		this.add(panelIn);
		this.add(checkinInfoPanel);
		this.add(panelOut);
		this.add(checkoutInfoPanel);
		this.add(panelButtons);
		
		this.pack();
	}
	
	private void exit(){
		this.setVisible(false);
		checkinText.setText("");
		checkoutText.setText("");
	}
	
	private void accept(){
		Vector<String> dates = new Vector<String>();
		dates.add(checkinText.getText());
		dates.add(checkoutText.getText());
		Controller.getInstance().event(Event.FIND_BOOK,dates,null);
		//TODO recoger los datos y mostrar la lista de habitaciones
	}
	
	private void addListener(){
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
		
		acceptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				accept();
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		});
	}
}
