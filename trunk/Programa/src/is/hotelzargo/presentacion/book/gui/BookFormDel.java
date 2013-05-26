package is.hotelzargo.presentacion.book.gui;

import is.hotelzargo.presentacion.controller.Controller;
import is.hotelzargo.presentacion.controller.Event;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class BookFormDel extends JDialog {

	private JLabel idLabel;
	
	private JTextField idText;
	
	private JButton acceptButton;
	private JButton cancelButton;
	
	public BookFormDel(JFrame owner, boolean mod) {
		super(owner,mod);
		this.setTitle("Dar de baja reserva");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(owner);
		
		idLabel = new JLabel("ID de la reserva");
		
		idText = new JTextField(10);
		
		acceptButton = new JButton("Aceptar");
		cancelButton = new JButton("Cancelar");
		
		addListener();
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(idLabel);
		panel.add(idText);
		
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new GridLayout(1,2));
		panelButtons.add(acceptButton);
		panelButtons.add(cancelButton);
		
		JPanel idInfoPanel = new JPanel();
		idInfoPanel.setLayout(new GridLayout(1,2));
		idInfoPanel.add(new JLabel("Ejemplo: 321"));
		
		this.setLayout(new GridLayout(3,1));
		this.add(panel);
		this.add(idInfoPanel);
		this.add(panelButtons);
		
		this.pack();
	}
	
	private void exit(){
		this.setVisible(false);
		idText.setText("");
	}
	
	private void accept(){
		//paso un int ID
		int id;
		try{
			id = Integer.valueOf(idText.getText());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "El campo "+idLabel.getText()+" debe ser un numero entero");
			return;
		}
		Controller.getInstance().event(Event.DELETE_BOOK,id,null);
		exit();
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

