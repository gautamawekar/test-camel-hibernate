package test.gawekar.beans;

import test.gawekar.model.Person;

public class PersistPerson {
	public Person persistPerson(){
		Person person = new Person();
		person.setFirstName("Gautam ");
		person.setLastName("Awekar");
		System.out.println("returning...." + person);
		return person;
	}
}
