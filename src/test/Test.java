package test;

import introsde.assignment.soap.Measure;
import introsde.assignment.soap.Person;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class Test {
	
	static Person p1, p2;
	static Measure m1, m2;

	public static void main(String[] args) {
		
		createTestData();
		
		WebserviceCaller ws_caller =  new WebserviceCaller("test.log");
		
		ws_caller.resetDatabase();
		
		ws_caller.test_01_readPersonList();
		
		p1 = ws_caller.test_04_createPerson(p1);
		
		p1 = ws_caller.test_02_readPerson(p1.getPersonId());
		
		p1.setFirstname("Barbara");
		p1 = ws_caller.test_03_updatePerson(p1);
		
		p2 = ws_caller.test_04_createPerson(p2);
		
		ws_caller.test_01_readPersonList();
		
		ws_caller.test_05_deletePerson(p1.getPersonId());
		
		ws_caller.test_07_readMeasureTypes();
		
		m1 = ws_caller.test_09_savePersonMeasure(p2.getPersonId(), m1);
		
		m1.setMeasureValue("2");
		ws_caller.test_10_updatePersonMeasure(p2.getPersonId(), m1);
		
		ws_caller.test_08_readPersonMeasure(p2.getPersonId(), m1.getMeasureType(), m1.getMid());
				
		m2.setMeasureValue("70");
		ws_caller.test_09_savePersonMeasure(p2.getPersonId(), m2);
		
		m2.setMeasureValue("71");
		ws_caller.test_09_savePersonMeasure(p2.getPersonId(), m2);
		
		m2.setMeasureValue("72");
		ws_caller.test_09_savePersonMeasure(p2.getPersonId(), m2);
		
		m2.setMeasureValue("73");
		ws_caller.test_09_savePersonMeasure(p2.getPersonId(), m2);
		
		ws_caller.test_06_readPersonHistory(p2.getPersonId(), m2.getMeasureType());
		
		ws_caller.testFinished();		
	}
	
	private static void createTestData(){		
		p1 = new Person();
		p1.setFirstname("Alice");
		p1.setLastname("Ecila");
		p1.setBirthdate(asDate(12, 5, 1957));
		
		p2 = new Person();
		p2.setFirstname("Carol");
		p2.setLastname("Lorac");
		p2.setBirthdate(asDate(6, 4, 1954));
		
		Measure m = new Measure();
		m.setMeasureType("height");
		m.setMeasureValue("1.75");
		m.setMeasureValueType("real");		
		p2.getCurrentHealth().add(m);
		
		m = new Measure();
		m.setMeasureType("weight");
		m.setMeasureValue("60");
		m.setMeasureValueType("integer");
		p2.getCurrentHealth().add(m);
		
		m2 = new Measure();
		m2.setMeasureType("weight");
		m2.setMeasureValue("60");
		m2.setMeasureValueType("integer");
		
		m1 = new Measure();
		m1.setMeasureType("height");
		m1.setMeasureValue("1.60");
		m1.setMeasureValueType("real");	
	}

	private static XMLGregorianCalendar asDate(int day, int month, int year){
		XMLGregorianCalendar ret = null;
		
		try {
			ret = DatatypeFactory.newInstance().newXMLGregorianCalendar();
			ret.setDay(day);
			ret.setMonth(month);
			ret.setYear(year);
			ret.setTime(0, 0, 0);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		
		return ret;
		
	}
}
