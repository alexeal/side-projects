package view;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class SaveFile {
	private String path;
	private  FileWriter fw;
	public SaveFile(MainFrame m){
		path=m.getSc().getSourceCodePath();
		if(path=="Unknown path"){
			new SaveAsFile(m);
		}else{
			try{
			    fw = new FileWriter (path);
			    fw.write(m.getCodearea().getText());
			    fw.close();
			    m.getSc().setSaved(true);
			    if(m.getTitle().contains("*")){
		        	m.setTitle(m.getTitle().substring(0,m.getTitle().indexOf('*')));
		        }
			}catch (IOException exception){
				JOptionPane.showMessageDialog(null, "An error occured while saving", "Erro informations", JOptionPane.ERROR_MESSAGE);
		    	m.getSc().setSaved(false);
			}
			
		}
		
	}
}
