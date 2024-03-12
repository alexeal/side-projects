package view;

public class Snippet {
	private MainFrame m;
	private int tile_id;
	public Snippet(MainFrame m, int tile_id){
		this.m=m;
		this.tile_id=tile_id;
	}
	public void addSnippet(){
		System.out.println();;
		if(this.tile_id==MainFrame.IF){
			this.m.getCodearea().insert("if (condition) {\n\n}", m.getCodearea().getCaretPosition());
		}else if(this.tile_id==MainFrame.FOR){
			this.m.getCodearea().insert("for (int i=0; i<10; i++) {\n\n}", m.getCodearea().getCaretPosition());
		}else if(this.tile_id==MainFrame.WHILE){
			this.m.getCodearea().insert("while (condition) {\n\n}", m.getCodearea().getCaretPosition());
		}else if(this.tile_id==MainFrame.DOWHILE){
			this.m.getCodearea().insert("do {\n\n}while();", m.getCodearea().getCaretPosition());
		}
	}

}
