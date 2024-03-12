package view;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class OpenFile {
	public OpenFile(MainFrame m) throws IOException{
		FileDialog fd = new FileDialog(m, "Choose a file", FileDialog.LOAD);
		fd.setFilenameFilter(new FilenameFilter() {
		    @Override
		    public boolean accept(File dir, String name) {
		    	return !name.contains(".") || name.endsWith(".txt") || name.endsWith(".java") || name.endsWith(".js") || name.endsWith(".php") || name.endsWith(".py") || name.endsWith(".rb") || name.endsWith(".sh") || name.endsWith(".c") || name.endsWith(".cpp") || name.endsWith(".html");
		    }
		});
		fd.setVisible(true);
		String filename = fd.getFile();
		if (filename == null){
			System.out.println("You cancelled the choice");
		}else{
			System.out.println("You chose " + filename);
			String name = fd.getFile();
			String path= fd.getDirectory();
			System.out.println(path);
			BufferedReader buff = new BufferedReader(new FileReader(fd.getDirectory()+"/"+fd.getFile()));
			try {
				 ArrayList<String> lines=new ArrayList<String>();
				 String line;
				 while ((line = buff.readLine()) != null) {
					 System.out.println("[Debug] Line read : "+line);
					 lines.add(line);
				 }
				 System.out.println("[Debug] Number of lines : "+lines.size());
				 
				 StringBuilder s = new StringBuilder();
				 int nbOfLines=0;
				 for(String temp : lines) {
					 nbOfLines++;
					 if(nbOfLines<lines.size()){
						 s.append(temp).append("\n");
					 }else{
						 s.append(temp);
					 }
			 	 }
				 m.getSc().setNbOfLines(lines.size());
				 m.getCodearea().append(s.toString());
				 if(m.getTitle().contains("*")){
			        	m.setTitle(m.getTitle().substring(0,m.getTitle().indexOf('*')));
			        }
			 }finally {
				 buff.close();
			 }
			 m.getSc().setSourceCodeName(name);
			 m.getSc().setSourceCodePath(path);
			 m.setTitle("Ninn - "+name);			
		}
	}
}