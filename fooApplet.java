
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.border.*;
import javax.swing.colorchooser.*;
import javax.swing.filechooser.*;
import javax.accessibility.*;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;
import java.io.*;
import java.applet.*;
import java.net.*;

public class fooApplet extends JApplet {
    public void init() {
	getContentPane().setLayout(new BorderLayout());
	getContentPane().add(new foo(this), BorderLayout.CENTER);
    }

    public URL getURL(String filename) {
	URL codeBase = this.getCodeBase();
	URL url = null;

	try {
	    url = new URL(codeBase, filename);
	    System.out.println(url);
	} catch (java.net.MalformedURLException e) {
	    System.out.println("Error: badly formed URL");
	    return null;
	}

	return url;	
    }
}