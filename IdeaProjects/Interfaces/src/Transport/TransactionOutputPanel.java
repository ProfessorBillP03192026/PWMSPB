package Transport;
import javax.swing.*;

/*********** Title: key server protocol project ***********
 * 
 * Description of the Class or method purpose: 
 * TransactionOutputPanel is where the output messages are
 * displayed.
 * 
 * Company:FDU Fall 2018
 * 
 * @author Bill Phillips ( 214-36-930)
 * 
 * @version $ Revision log: 1.0
 * 
 * @see  TransactionOutputPanel
 *********************************************************/

public class TransactionOutputPanel extends JFrame
{

    private int             rows        =               20;
    private int             columns     =               80;
    private boolean         emptystring =             true;
    private String          S           =     new String();

    private Box box;
    private JTextArea textarea;

    public void setText( String s )
    {
        if ( emptystring )   S  =  s;// no initial linefeed.
        else                 S  =  S + '\n' + s;
        emptystring = false;
        textarea.setText( S );
        repaint();
    }

    public TransactionOutputPanel(String S, int r, int c)
    {
        super(S);
        rows    = r;
        columns = c;
        box = Box.createHorizontalBox();
        textarea = new JTextArea( rows, columns );
        textarea.setEditable( false );
        box.add( new JScrollPane( textarea ) );
        add( box );
    }
}
