package view;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import controller.MenuListener;

public class MenuBar extends JMenuBar{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String os_name = System.getProperty("os.name").toLowerCase();
	private ActionListener menuListener;

	public MenuBar(MainFrame m) {
		
		/* Events, keyboard shortcuts and tool tips */
        menuListener = new MenuListener(m);

		JMenu file = new JMenu("File");
        add(file);
                
        JMenuItem newFile = new JMenuItem ("New file");
        file.add(newFile);
          
        /*JMenuItem JavaFile = new JMenuItem ("Java File");
        newFile.add(JavaFile);
        
        JMenuItem TextFile = new JMenuItem ("Text File");
        newFile.add(TextFile);*/
        
        JMenuItem open = new JMenuItem ("Open");
        file.add(open); 
        
        JMenuItem save = new JMenuItem ("Save");
        file.add(save);  
        
        JMenuItem saveas = new JMenuItem ("Save as ...");
        file.add(saveas);
        
        JMenuItem quit = new JMenuItem ("Quit");
        file.add(quit);
        
        JMenu edit = new JMenu("Edition");
        add(edit);
        
        JMenuItem undo = new JMenuItem ("Undo");
        edit.add(undo);
        
        JMenuItem redo = new JMenuItem ("Redo");
        edit.add(redo);
        
        JMenuItem copy = new JMenuItem ("Copy");
        edit.add(copy);
        
        JMenuItem paste = new JMenuItem ("Paste");
        edit.add(paste);
        
        JMenuItem informations = new JMenuItem ("Informations");
        edit.add(informations);
        
        JMenu help = new JMenu("Help");
        add(help);
                
        JMenuItem shortcuts = new JMenuItem ("Shortcuts");
        help.add(shortcuts);
        
        if (os_name.startsWith("mac")) {
        	newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,  KeyEvent.META_MASK));
        	open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,  KeyEvent.META_MASK));
        	save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,  KeyEvent.META_MASK));
        	saveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,  KeyEvent.ALT_MASK + KeyEvent.META_MASK));
        	quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.META_MASK));
        	undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.META_MASK));
        	redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.META_MASK));
        	copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,  KeyEvent.META_MASK));
        	paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,  KeyEvent.META_MASK));
        	informations.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.META_MASK));
        }else{
        	newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,  KeyEvent.CTRL_MASK));
        	open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
        	save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,  KeyEvent.CTRL_MASK));
        	saveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,  KeyEvent.ALT_MASK + KeyEvent.CTRL_MASK));
        	quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));
        	undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_MASK));
        	redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_MASK));
        	copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,  KeyEvent.CTRL_MASK));
        	paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,  KeyEvent.CTRL_MASK));
            informations.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_MASK));
        	
        }
        
        newFile.setMnemonic('N');
        open.setMnemonic('O');
        save.setMnemonic('S');
        copy.setMnemonic('C');
        quit.setMnemonic('Q');
        informations.setMnemonic('I');
        
        newFile.addActionListener(menuListener);
        open.addActionListener(menuListener);
        save.addActionListener(menuListener);
        saveas.addActionListener(menuListener);
        copy.addActionListener(menuListener);
        paste.addActionListener(menuListener);
        quit.addActionListener(menuListener);
        informations.addActionListener(menuListener);
        shortcuts.addActionListener(menuListener);
        undo.addActionListener(menuListener);
        redo.addActionListener(menuListener);
        
	}

}
