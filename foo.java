
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.border.*;
import javax.swing.colorchooser.*;
import javax.swing.filechooser.*;
import javax.accessibility.*;

import java.lang.reflect.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;
import java.io.*;
import java.applet.*;
import java.net.*;

public class foo extends JPanel {
    /*
     * Data Members
     */

    // used in application mode
    //NOTE: should introduce an xml markup to distinguish application vs. applet code
    private JFrame frame = null;

    //* the main tabbed pane 
    private JTabbedPane tabbedPane = null;

    private fooApplet applet = null;



    /*
     * Constructor
     */
    public foo(fooApplet applet) { this(applet, null); }
    public foo(fooApplet applet, GraphicsConfiguration gc) { 
	this.applet = applet;
	
	if(!isApplet()) {
	    frame = createFrame(gc);
	}

	// set the layout
	setLayout(new BorderLayout());

	
	// Initialize contents of GUI
	initalizefoo();


	// show the content. Must do this on the GUI thread using invokeLater
	SwingUtilities.invokeLater(new Runnable() {
		public void run() {
		    if(!isApplet()) {
			showfoo();
		    }
		}
	    });
    }


    /**
     * foo Main .. called only for applications .. not applets
     */
    public static void main(String[] args) {
	// create foo on the default monitor
	UIManager.put("swing.boldMetal", Boolean.FALSE);
	foo fooinst = new foo(null);
    }


    /*
     * foo utilities
     */

    public void initalizefoo() {
	JPanel top = new JPanel();
	top.setLayout(new BorderLayout());
	add(top, BorderLayout.NORTH);

	tabbedPane = new JTabbedPane();
	add(tabbedPane, BorderLayout.CENTER);

	JPanel testPanel = new JPanel();
	testPanel.setLayout(new BorderLayout());
	testPanel.setBorder(new EtchedBorder());
	tabbedPane.addTab("Hi There!", testPanel);

    }

    public static JFrame createFrame(GraphicsConfiguration gc) {
	JFrame frame = new JFrame(gc);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	return frame;
    }

    public void showfoo() {
	JFrame f = getFrame();
	if( f == null) { return; }

	f.setTitle("Andrew's foo");
	f.getContentPane().add(this, BorderLayout.CENTER);
	f.pack();

	Rectangle screenRect = f.getGraphicsConfiguration().getBounds();
	Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(f.getGraphicsConfiguration());

	// make sure we don't place the ourselves off the screen
	int centerWidth = screenRect.width < f.getSize().width ?
	    screenRect.x :
	    screenRect.x + screenRect.width/2 - f.getSize().width/2;
	int centerHeight = screenRect.height < f.getSize().height ?
	    screenRect.y :
	    screenRect.y + screenRect.height/2 - f.getSize().height/2;

	centerHeight = centerHeight < screenInsets.top ?
	    screenInsets.top : centerHeight;

	f.setLocation(centerWidth, centerHeight);
	f.show();
    }


    /*
     * Accessors
     */

    public JFrame getFrame() { return frame; }

    public boolean isApplet() { return (applet != null); }

    /*
     * Nested Classes
     */

}