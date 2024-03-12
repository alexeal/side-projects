package model;

public class SourceCode {
	private String sourceCodeName;
	private String sourceCodePath;
	private boolean isSaved;
	private int nbOfLines;
	private int textLgth;
	private int nbOfWords;
	
	public SourceCode(){
		this.sourceCodeName = "Untitled";
		this.sourceCodePath = "Unknown path";
		this.isSaved = false;
		this.nbOfLines=1;
		this.textLgth=0;
		this.nbOfWords=0;
	}
	
	public SourceCode(String name, String path, int nb, int lgth){
		this.sourceCodeName = name;
		this.sourceCodePath = path;
		this.isSaved = false;
		this.nbOfLines=nb;
		this.textLgth=lgth;
	}

	/**
	 * @return the fileName
	 */
	public String getSourceCodeName() {
		return sourceCodeName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setSourceCodeName(String sourceCodeName) {
		this.sourceCodeName = sourceCodeName;
	}

	/**
	 * @return the filePath
	 */
	public String getSourceCodePath() {
		return sourceCodePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setSourceCodePath(String sourceCodePath) {
		this.sourceCodePath = sourceCodePath;
	}

	/**
	 * @return the isSaved
	 */
	public boolean isSaved() {
		return isSaved;
	}

	/**
	 * @param isSaved the isSaved to set
	 */
	public void setSaved(boolean isSaved) {
		this.isSaved = isSaved;
	}

	/**
	 * @return the nbOfLines
	 */
	public int getNbOfLines() {
		return nbOfLines;
	}

	/**
	 * @param nbOfLines the nbOfLines to set
	 */
	public void setNbOfLines(int nbOfLines) {
		this.nbOfLines = nbOfLines;
	}

	/**
	 * @return the textLength
	 */
	public int getTextLgth() {
		return textLgth;
	}

	/**
	 * @param textLength the textLength to set
	 */
	public void setTextLgth(int textLength) {
		this.textLgth = textLength;
	}

	/**
	 * @return the nbOfWords
	 */
	public int getNbOfWords() {
		return nbOfWords;
	}

	/**
	 * @param nbOfWords the nbOfWords to set
	 */
	public void setNbOfWords(int nbOfWords) {
		this.nbOfWords = nbOfWords;
	}
	

}
