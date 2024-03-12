package controller;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import view.MainFrame;
import view.OpenFile;
import view.SaveAsFile;
import view.SaveFile;

public class MenuListener implements ActionListener{
	private MainFrame m;
	private String copiedContent=new String("");
	public MenuListener(MainFrame m) {
		this.m=m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Java File")){
			System.out.println("New file process");
			//m.dispose();
		}else if(e.getActionCommand().equals("New file")){
			String name = (String)JOptionPane.showInputDialog("Name of the new file :");
			System.out.println(name);
			this.m.getSc().setSourceCodeName(name);
			this.m.setTitle("Ninn - "+name);
		}else if(e.getActionCommand().equals("Open")){
			System.out.println("Open process");
			 try {
				new OpenFile(this.m);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if(e.getActionCommand().equals("Save")){
			new SaveFile(this.m);
		}else if(e.getActionCommand().equals("Copy")){
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Clipboard clipboard = toolkit.getSystemClipboard();
			try {
				copiedContent = (String) clipboard.getData(DataFlavor.stringFlavor);
			} catch (UnsupportedFlavorException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("[DEBUG] From Clipboard:" + copiedContent);
		}else if(e.getActionCommand().equals("Paste")){
			this.m.getCodearea().append("\n"+copiedContent);
		}else if(e.getActionCommand().equals("Save as ...")){
			new SaveAsFile(this.m);
		}else if(e.getActionCommand().equals("Quit")){
			if(m.getTitle().endsWith("*")){
				int i=JOptionPane.showConfirmDialog(null, "Do you really want to close Ninn ?\nAny unsaved work will be lost", "Closing the application", JOptionPane.YES_OPTION, JOptionPane.CANCEL_OPTION);
                if(i==JOptionPane.YES_NO_OPTION){
                	m.dispose();
                }else{
                	new SaveFile(this.m);
                	//JOptionPane.showMessageDialog(null, "File > Save or File > Save as...", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
			}
			//m.dispose();
		}else if(e.getActionCommand().equals("Informations")){
			JOptionPane.showMessageDialog(null, "File name : "+m.getSc().getSourceCodeName()+"\nPath : "+m.getSc().getSourceCodePath()+"\nNumber of lines : "+m.getSc().getNbOfLines()+"\nNumber of words : "+m.getSc().getNbOfWords()+"\nNumber of characters : "+m.getSc().getTextLgth(), "Informations", JOptionPane.INFORMATION_MESSAGE);
		}else if(e.getActionCommand().equals("Shortcuts")){
			JOptionPane.showMessageDialog(null, "New file : Ctrl/Cmd + N\nOpen file : Ctrl/Cmd + O\nSave : Ctrl/Cmd + S\nSave as : Alt + Ctrl/Cmd + S\nInformations (number of lines and characters) : Ctrl/Cmd + I\n", "Useful shortcuts", JOptionPane.INFORMATION_MESSAGE);
		}else if(e.getActionCommand().equals("Undo")){
			JOptionPane.showMessageDialog(null, "Unvailable for the moment", "Undo", JOptionPane.INFORMATION_MESSAGE);
		}else if(e.getActionCommand().equals("Redo")){
			JOptionPane.showMessageDialog(null, "Unvailable for the moment", "Redo", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

}
