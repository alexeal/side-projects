package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Paint;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import static java.awt.GraphicsDevice.WindowTranslucency.*;
import controller.CodeAreaListener;
import model.Coord;
import model.SourceCode;

public class MainFrame extends JFrame implements DropTargetListener, ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int IF=0,FOR=1,WHILE=2,DOWHILE=3;
	private String os_name = System.getProperty("os.name").toLowerCase();
	private MenuBar menuBar;
	private JTextArea codearea;
	private Font fontCode;
	private SourceCode sc;
	private GraphicsDevice gd;
	private JSplitPane jsp;
	private JPanel mainPanel;
	private JPanel leftPanel;
    private JPanel tiles;
    private JButton toggleButton;
    private boolean panelHidden=true;
    private Dimension preferredSize_LeftPanel = new Dimension(150, 100);
    private Dimension minimumSize_LeftPanel = new Dimension(150, 100);
    
    /* Working area */
    JScrollPane rightPanel = new JScrollPane (codearea);
	public MainFrame(){
		super("GradientTranslucentWindow");
		/* Title with version by default (we add the name of the file opened later) */
		setTitle("Ninn");
		/* Icon of the app */
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/images/logo/logo_ninn.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		/* Size of the window */
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        /* Put the window in the center of the screen */
        setLocation(dim.width/2-600/2, dim.height/2-600/2);
        setMinimumSize(new Dimension(800,500));

        
        this.gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

    	if (gd.isFullScreenSupported()) {
    		System.out.println("[SUCCESS] Full screen supported");
    	}else {
    		System.err.println("[ERROR] Full screen not supported");
    	}
    	
    	if (gd.isWindowTranslucencySupported(TRANSLUCENT)) {
    		System.out.println("[SUCCESS] Opacity supported");
    		//this.setBackground(new Color(163, 174, 186,100));
    	}else {
    		System.err.println("[ERROR] Opacity not supported");
    	}

        DropTarget dt = new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, this, true);
        /* looknfeel */
        try {    
        	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        } catch (UnsupportedLookAndFeelException e) {
        }
        /* If we are on Mac */
        if(os_name.startsWith("mac")){
        	/* Put the menu at the top of your screen */
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            /* Set the name of the application menu item */
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Ninn");
            /* Set the icon */
            setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/images/logo/logo_ninn.png")));
            /* Enable full screen mode */
            enableFullScreenMode(this);
        } 
        
        /* model */
        sc=new SourceCode();
        /* view */
        codearea = new JTextArea("");
        codearea.setDragEnabled(true);
        DropTarget child = new DropTarget(codearea, DnDConstants.ACTION_COPY_OR_MOVE, this, true);
        /* controller */
        codearea.getDocument().addDocumentListener(new CodeAreaListener(this));
        codearea.getDocument().putProperty("name", "codearea");
        
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            	if(getTitle().endsWith("*")){
                    Object[] choices = {"Yes","Cancel"};
					int closeOptions = JOptionPane.showOptionDialog(null,
							"Do you really want to close Ninn ?\nAny unsaved work will be lost",
							" ",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE,
							null,     //do not use a custom Icon
							choices,  //the titles of buttons
							choices[0]); //default button title
					if(closeOptions==0){
						dispose();
						System.exit(0);
					}else{
						JOptionPane.showMessageDialog(null, "File > Save or File > Save as...", " ", JOptionPane.INFORMATION_MESSAGE);
					}
            	}else{
            		dispose();
            		System.exit(0);
            	}
                
            }
        });
        
        /* Menubar */
        menuBar=new MenuBar(this);
        setJMenuBar(menuBar);
        
        mainPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JScrollPane (codearea);
        jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        tiles = new JPanel();
        
        mainPanel.setLayout(new BorderLayout());
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        tiles.setLayout(null);

        /* Font family */
        if (os_name.startsWith("mac")) {
        	fontCode = new Font("Menlo", Font.PLAIN,12 ); // 12
        }else{
        	fontCode = new Font("Consolas", Font.PLAIN,12 ); // 12
        }
        this.codearea.setFont(fontCode);
        //Color c = new Color(84,94,97,100);
        //Color color = new Color(UIManager.getColor("control").getRGB()); // <-- Create a new color
        //codearea.setBackground(color);
        //leftPanel.setSize(220, 400);
        
        toggleButton=new JButton("Show snippets");
        toggleButton.addActionListener(this);
        toggleButton.setBackground(new Color(163, 174, 186,100));
        leftPanel.add(toggleButton);
        
        /* Tiles */
        tiles.add(new Tile(this,IF,new Coord(10,10),new Coord(110,150),new ImageIcon(MainFrame.class.getResource("/images/tiles/if_tile.png")).getImage(),"Tooltip"));
        tiles.add(new Tile(this,FOR,new Coord(130,10),new Coord(110,150),new ImageIcon(MainFrame.class.getResource("/images/tiles/for_tile.png")).getImage(),"Tooltip"));
        tiles.add(new Tile(this,WHILE,new Coord(10,170),new Coord(110,150),new ImageIcon(MainFrame.class.getResource("/images/tiles/while_tile.png")).getImage(),"Tooltip"));
        tiles.add(new Tile(this,DOWHILE,new Coord(130,170),new Coord(110,150),new ImageIcon(MainFrame.class.getResource("/images/tiles/dowhile_tile.png")).getImage(),"Tooltip"));
		leftPanel.add(tiles);
		tiles.setVisible(false);
        
        /* Scrollbars*/
        rightPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        rightPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        /* Dimensions of the panels */
        leftPanel.setPreferredSize(preferredSize_LeftPanel);
        leftPanel.setMinimumSize(minimumSize_LeftPanel);

        jsp.add(rightPanel, JSplitPane.RIGHT);
        jsp.add(leftPanel, JSplitPane.LEFT);
        mainPanel.add(jsp,BorderLayout.CENTER);//add splitpane to mainpanel
        this.add(mainPanel);
       
        pack();
        setLocationRelativeTo(null);
        
        setVisible(true);
        
	}
	public JTextArea getCodearea() {
		return codearea;
	}
	public void setCodearea(JTextArea codearea) {
		this.codearea = codearea;
	}
	public void addText(JTextArea codearea,String line){
		//codearea.
		System.out.println("[TODO] Append String");
	}
	/**
	 * @return the sc
	 */
	public SourceCode getSc() {
		return sc;
	}
	/**
	 * @param sc the sc to set
	 */
	public void setSc(SourceCode sc) {
		this.sc = sc;
	}
	
	public void changeTitle(String title){
		this.setTitle("Ninn - "+title);
		System.out.println("[SUCCESS] Title changed : "+this.getTitle());
	}
	
	public static void enableFullScreenMode(Window window) {
        String className = "com.apple.eawt.FullScreenUtilities";
        String methodName = "setWindowCanFullScreen";
 
        try {
            Class<?> clazz = Class.forName(className);
            Method method = clazz.getMethod(methodName, new Class<?>[] {
                    Window.class, boolean.class });
            method.invoke(null, window, true);
        } catch (Throwable t) {
            System.err.println("Full screen mode is not supported");
            t.printStackTrace();
        }
    }
		
	@Override
	public void dragEnter(DropTargetDragEvent dtde) {}
	@Override
	public void dragOver(DropTargetDragEvent dtde) {}
	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {}
	@Override
	public void dragExit(DropTargetEvent dte) {}
	/**
	 * TODO
	 */
	@Override
	public void drop(DropTargetDropEvent dtde) {
		// TODO Auto-generated method stub
		dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
		Transferable trsf=dtde.getTransferable();
		DataFlavor[] flavors = trsf.getTransferDataFlavors();
		System.out.println(flavors[3]);
		if(flavors[0].isFlavorTextType()){
        	try {
				String text = (String) trsf.getTransferData(flavors[3]);
				System.out.println(text);
				if(this.getCodearea().getLineCount()==0){
					this.getCodearea().setText(text);
				}else{
					this.getCodearea().append(" "+text);
				}
			} catch (UnsupportedFlavorException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }else{
        	for(DataFlavor flavor : flavors){
                if(flavor.isFlavorJavaFileListType()){
                    List myFiles;
                    String ret = "";
    				try {
    					myFiles = (List) trsf.getTransferData(flavor);
    					System.out.println(myFiles);
    					/*
    					 * fetch the path, in fact we can drag and drop several files, 
    					 * for the moment the soft is able to open only one file...
    					 */
    					String absolutePath = myFiles.get(0).toString();
    					/* Classic process to read a file */
    	                BufferedReader br = null;
    	                try {
    	                    br = new BufferedReader(new InputStreamReader(new FileInputStream(absolutePath)));

    	                    String line = null;
    	                    String text = "";

    	                    String nl = System.getProperty("line.separator", "\n");

    	                    while ((line = br.readLine()) != null)
    	                        text += line + nl;

    	                    ret.concat(text.trim());
    	                    //--
    	                    System.out.println(this.getSc().getNbOfWords());
    	                    if(this.getSc().getNbOfWords()==0){
    	                    	this.getCodearea().setText(text);
        	                    this.getSc().setSourceCodeName(new File(absolutePath).getName());
        	                    this.getSc().setSourceCodePath(absolutePath);
        	                    this.changeTitle(new File(absolutePath).getName());
    	    				}else{
    	    					Object[] options = {"Open the file",
    	                        "Paste","Cancel"};
    	    					int n1 = JOptionPane.showOptionDialog(this,
    	    							"You are trying to drag and drop a file into the text area; it will erase the previous text.\n Do you want to open this file or to paste it below the existant text ?",
    	    							" ",
    	    							JOptionPane.YES_NO_OPTION,
    	    							JOptionPane.QUESTION_MESSAGE,
	    								null,     //do not use a custom Icon
	    								options,  //the titles of buttons
	    								options[0]); //default button title
    	    					if(n1==0){
    	    						if(this.getTitle().endsWith("*")){
    	    							Object[] options2 = {"Yes","Cancel"};
    	    	    	    					int n2 = JOptionPane.showOptionDialog(this,
    	    	    	    							"You did not save your text.\n Are you sure to open another file without saving the previous one ?",
    	    	    	    							" ",
    	    	    	    							JOptionPane.YES_NO_OPTION,
    	    	    	    							JOptionPane.QUESTION_MESSAGE,
    	    		    								null,     //do not use a custom Icon
    	    		    								options2,  //the titles of buttons
    	    		    								options2[0]); //default button title
    	    	    	    			if(n2==0){
    	    	    	    				this.getCodearea().setText(text);
    	            	                    this.getSc().setSourceCodeName(new File(absolutePath).getName());
    	            	                    this.getSc().setSourceCodePath(absolutePath);
    	            	                    this.changeTitle(new File(absolutePath).getName());
    	    	    	    			}
    	    						}
    	    						
    	    					}else if(n1==1){
    	    						this.getCodearea().append("\n"+text);
    	    					}
    	    				}

    	                } catch (Exception e) {
    	                	JOptionPane.showMessageDialog(null, "An error occured while trying to open the file ", "Informations", JOptionPane.ERROR_MESSAGE);
    	                	e.printStackTrace();
    	                } finally {
    	                    if (br != null)
    	                        try {
    	                            br.close();
    	                        } catch (Exception e) {
    	                        }
    	                }
    	                
    				} catch (UnsupportedFlavorException | IOException e) {
    					e.printStackTrace();
    				}
            	}
        	}
    	}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(panelHidden){
			toggleButton.setText("Hide snippets");
			this.tiles.setVisible(true);
			panelHidden=false;
			System.out.println(jsp.getDividerLocation());
			jsp.setDividerLocation(250);
		}else{
			toggleButton.setText("Show snippets");
			this.tiles.setVisible(false);
			jsp.setDividerLocation(150);
			panelHidden=true;
		}
		
	}

}
