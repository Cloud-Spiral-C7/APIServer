package moe.lisp.jaxrswebapp.rest;
public class NazoObj {
	private int count;

	public NazoObj() {

	}
	public NazoObj(int c){
		count= c;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}