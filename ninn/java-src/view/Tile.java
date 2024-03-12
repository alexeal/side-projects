package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.text.BadLocationException;

import model.Coord;

public class Tile extends JPanel implements MouseListener,Observer{
	
	private MainFrame m;
	private Snippet sn;
	private Coord size;
	private Coord coordTile;
	private Image img;
	private boolean mouseOver=false;
	private int id;
	private String label;
	
	/**
	 * Tile's constructor
	 * @param id 
	 * @param c 
	 * @param sz size of the tile
	 * @param img Image source
	 * @param label tooltip
	 */
	public Tile(MainFrame m, int id,Coord c,Coord sz,Image img,String label){
		/*this.model=model;
		model.addObserver(this);*/
		this.m=m;
		this.setId(id);
		this.coordTile=c;
		this.size=sz;
		this.img=img;
		setBounds(c.getX(), c.getY(), sz.getX(), sz.getY());
		setToolTipText(label);
		this.addMouseListener(this);
		sn=new Snippet(m,id);
	}
	

	/**
	 * Draw the tile
	 */
	public void paintComponent(Graphics g){
		System.out.println("[DEBUG] Mouse over the tile");
		g.setColor(getBackground());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		if(img!=null){
			//System.out.println(img.getClass());
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(),null);
		}else{
			System.out.println("NULL");
		}
		if(mouseOver){
			g.setColor(new Color(0,255,0,100));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		String currentLine=new String();		
		try {
		    int offset=m.getCodearea().getLineOfOffset(m.getCodearea().getCaretPosition());
		    int start=m.getCodearea().getLineStartOffset(offset);
		    int end=m.getCodearea().getLineEndOffset(offset);
		    currentLine=m.getCodearea().getText(start, (end-start));
		    System.out.println("[DEBUG] Text: "+currentLine+"Length:"+currentLine.length());                
		} catch (BadLocationException ex) {
		    System.out.println(ex.getMessage());
		}
		if(currentLine.length()>=1){
			this.m.getCodearea().insert("\n", m.getCodearea().getCaretPosition());
		}
		
		sn.addSnippet();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		mouseOver=true;
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		mouseOver=false;	
		repaint();
		
	}

}
