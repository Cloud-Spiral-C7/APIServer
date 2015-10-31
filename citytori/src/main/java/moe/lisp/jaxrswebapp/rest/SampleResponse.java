package moe.lisp.jaxrswebapp.rest;

import java.util.ArrayList;

public class SampleResponse {
	private String name;
	private int age;
	private ArrayList<String> hobbies;
	private ArrayList<NazoObj> nazo;

	public ArrayList<NazoObj> getNazo() {
		return nazo;
	}
	public void setNazo(ArrayList<NazoObj> nazo) {
		this.nazo = nazo;
	}

	public SampleResponse() {
		name = "test";
		age = 20;
		hobbies = new ArrayList<String>();
		hobbies.add("hr/hm");
		hobbies.add("hr/hdddddddddddddm");

		nazo = new ArrayList<NazoObj>();
		nazo.add(new NazoObj());
		nazo.add(new NazoObj());
		nazo.add(new NazoObj());
		nazo.add(new NazoObj());
		nazo.add(new NazoObj());
		nazo.add(new NazoObj());
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<String> getHobbies() {
		return hobbies;
	}
	public void setHobbies(ArrayList<String> hobbies) {
		this.hobbies = hobbies;
	}

//	public class NazoObj {
//		private int count;
//
//		public NazoObj() {
//
//		}
//		public NazoObj(int c){
//			count= c;
//		}
//
//		public int getCount() {
//			return count;
//		}
//
//		public void setCount(int count) {
//			this.count = count;
//		}
//	}

}
