package controller;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import view.MainFrame;

public class CodeAreaListener implements DocumentListener{
	MainFrame m;
	String newline = "\n";
	
	public CodeAreaListener(MainFrame m) {
		this.m=m;
	}
	 
    public void insertUpdate(DocumentEvent e) {
        updateLog(e, "inserted into ");
    }
    public void removeUpdate(DocumentEvent e) {
        updateLog(e, "removed from ");
    }
    public void changedUpdate(DocumentEvent e) {
        //Plain text components do not fire these events
    }

    public void updateLog(DocumentEvent e, String action) {
        Document doc = (Document)e.getDocument();
        /*int changeLength = e.getLength();
        System.out.println(changeLength + " character" +
            ((changeLength == 1) ? " " : "s ") +
            action + doc.getProperty("name") + "." + newline +
            "  Text length = " + doc.getLength() + newline);
        System.out.println(e.getDocument().getLength());*/
        m.getSc().setSaved(false);
        m.getSc().setNbOfLines(m.getCodearea().getLineCount());
        m.getSc().setNbOfWords(Integer.parseInt(String.valueOf(m.getCodearea().getText().split("\\s").length)));
        m.getSc().setTextLgth(doc.getLength()-1);
        ;
        if(!m.getTitle().contains("*")){
        	m.setTitle(m.getTitle()+"*");
        }
        
    }

}
