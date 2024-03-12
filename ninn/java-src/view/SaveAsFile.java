package view;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SaveAsFile {
	private JFileChooser fileDialog;
	private File selectedFile;
	public SaveAsFile(MainFrame m){
		if(fileDialog==null){
			fileDialog = new JFileChooser();
		} 
		if(m.getTitle()=="Ninn"){
			selectedFile = new File(m.getSc().getSourceCodeName()+".txt");
		}else if(m.getTitle().contains(".")){
			selectedFile = new File(m.getSc().getSourceCodeName().substring(0,m.getSc().getSourceCodeName().indexOf('.'))+".txt");
		}else{
			selectedFile = new File(m.getSc().getSourceCodeName());
		}
		System.out.println("[Debug] "+selectedFile);
		fileDialog.setSelectedFile(selectedFile); 
	    fileDialog.setDialogTitle("Save the file");
	    int option = fileDialog.showSaveDialog(fileDialog);
		if (option != JFileChooser.APPROVE_OPTION) return;  // Annuler ou fermeture de la fenetre.
		selectedFile = fileDialog.getSelectedFile();
		String path=""+selectedFile;
		if (selectedFile.exists()) {  // Le fichier existe déjà, devons nous ecraser le fichier existant ?
			int response = JOptionPane.showConfirmDialog( fileDialog,
					"Le fichier \"" + selectedFile.getName()
					+ "\" existe déjà.\nVoulez-vous le remplacez ?", 
					"Remplacer",
					JOptionPane.YES_NO_OPTION, 
					JOptionPane.WARNING_MESSAGE );
			if (response != JOptionPane.YES_OPTION) return;  // Annuler l'enregistrement
		}
		System.out.println(selectedFile.getName());
	    m.getSc().setSourceCodeName(selectedFile.getName());
	    System.out.println(m.getSc().getSourceCodeName());
	    m.getSc().setSourceCodePath(path);
	    m.getSc().setNbOfLines(m.getCodearea().getLineCount());
	    try{
	    	new SaveFile(m);
	    	m.setTitle("Ninn - "+m.getSc().getSourceCodeName());
	    	m.getSc().setSaved(true);
	    }catch(Exception e){
	    	JOptionPane.showMessageDialog(fileDialog, this,"An error occured while saving", option);
	    	m.getSc().setSaved(false);
	    }
	    
	}

}
