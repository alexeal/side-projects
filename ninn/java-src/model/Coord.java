package model;

public class Coord {
	
	private int x,y;

	public Coord(int x,int y){
		this.x=x;
		this.y=y;
	}

	public Coord(Coord c){
		this.x=c.getX();
		this.y=c.getY();
	}

	public void add(Coord c){
		this.x+=c.getX();
		this.y+=c.getY();
	}

	public void mul(float i){
		this.x*=i;
		this.y*=i;
	}

	public void div(float i){
		this.x/=i;
		this.y/=i;
	}

	public static Coord dif(Coord c,Coord c1){
		return new Coord(c.getX()-c1.getX(),c.getY()-c1.getY());
	}

	public void set(Coord c){
		this.x=c.getX();
		this.y=c.getY();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String toString(){
		return "("+x+","+y+")";
	}

}
