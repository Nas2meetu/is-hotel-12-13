package is.hotelzargo.presentacion.maingui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class RenderList extends JTextArea implements ListCellRenderer {

        public Component getListCellRendererComponent(
            JList lista,Object valor,int indice,
            boolean seleccionado, boolean conFoco ) {

            setBorder( new BevelBorder( BevelBorder.RAISED ) );
            setText( valor.toString() );
            if ( seleccionado ) {
                setBackground( Color.red );
                setForeground( Color.white );
            }
            else {
                setBackground( Color.lightGray );
                setForeground( Color.black );
            }
            return( this );
        }
}
