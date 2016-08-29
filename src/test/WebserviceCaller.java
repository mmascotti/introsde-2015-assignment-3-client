package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import introsde.assignment.soap.Measure;
import introsde.assignment.soap.People;
import introsde.assignment.soap.PeopleWS;
import introsde.assignment.soap.Person;

/**
 * The methods in this class are for testing the webservice methods and printing the results and log information 
 * either to {@link System#out} or to a log file. <p>
 * Before executing the test {@link #resetDatabase()} can be used to delete all {@link Person} from the database. <br>
 * Then the methods named <code>test_xx_methodname(...)</code> can be called to test the methods of the webservice and 
 * print the results/log messages. The parameters and the returned results are the same as for the corresponding method in 
 * the webservice. 
 * For example {@link #test_01_readPersonList()} calls {@link People#readPersonList()} (method #1 in the assignment description)
 * and returns a list of all person in the database.<br>
 * At the end of test a call to {@link #testFinished()} closes the PrintStream of the log file. 
 */
public class WebserviceCaller {

	PeopleWS pws = new PeopleWS();
	People people = pws.getPeopleWSImplementationPort();
		
	Logger logger;
	
	/**
	 * Creates a new {@link WebserviceCaller} that prints log information to {@link System#out}.
	 */
	public WebserviceCaller(){
		logger = new Logger(System.out);
		logger.printString(pws.getWSDLDocumentLocation().toString());
	}
	
	/**
	 * Creates a {@link WebserviceCaller} that prints log information to the file with path 'filename'.
	 * @param filename path of the log file
	 */
	public WebserviceCaller(String filename){
		File f = new File(filename);
		PrintStream ps = null;
		try {
			ps = new PrintStream(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		logger = new Logger(ps);
		logger.printString(pws.getWSDLDocumentLocation().toString());
	}

	/**
	 * Executes {@link People#readPersonList()} and prints the result to the log file.
	 * @return
	 */
	public List<Person> test_01_readPersonList() {
		logger.printMethod(1, "readPersonList()");
		List<Person> list = people.readPersonList();
		logger.printPersonList(list);
		return list;
	}

	/**
	 * Executes {@link People#readPerson(Long)} and prints the result to the log file.
	 * @param id
	 * @return
	 */
	public Person test_02_readPerson(Long id) {
		String methodcall = String.format("readPerson(%d)", id);
		logger.printMethod(2, methodcall);
		Person p = people.readPerson(id);
		logger.printPerson(p, false);
		return p;
	}

	/**
	 * Executes {@link People#updatePerson(Person)} and prints the result to the log file.
	 * @param p
	 * @return
	 */
	public Person test_03_updatePerson(Person p) {
		String methodcall = "updatePerson(p)";		
		logger.printMethod(3, methodcall);
		logger.printParameter("p",	p);
		
		Person person = people.readPerson(p.getPersonId());
		logger.printString("\nBefore update (value in database): ");
		logger.printPerson(person, false);
		
		person = people.updatePerson(p);
		
		logger.printString("\nAfter update/result: ");
		logger.printPerson(person, false);
		return person;
	}

	/**
	 * Executes {@link People#createPerson(Person)} and prints the result to the log file.
	 * @param p
	 * @return
	 */
	public Person test_04_createPerson(Person p) {
		String methodcall = "createPerson(p)";		
		logger.printMethod(4, methodcall);
		logger.printParameter("p",	p);
		
		Person person = people.createPerson(p);
		
		logger.printString("\nresult:");
		logger.printPerson(person, false);
		return person;
	}

	/**
	 * Executes {@link People#deletePerson(Long)} and prints the result to the log file.
	 * @param id
	 */
	public void test_05_deletePerson(Long id) {
		String methodcall = String.format("deletePerson(%d)", id);
		logger.printMethod(5, methodcall);
		
		logger.printString("\nperson list before deleting: ");
		logger.printPersonList(people.readPersonList());
		
		people.deletePerson(id);
		
		logger.printString("\nperson list after deleting: ");
		logger.printPersonList(people.readPersonList());	
	}

	/**
	 * Executes {@link People#readPersonHistory(Long, String)} and prints the result to the log file.
	 * @param id
	 * @param measureType
	 * @return
	 */
	public List<Measure> test_06_readPersonHistory(Long id, String measureType) {
		String methodcall = String.format("readPersonHistory(%d, %s)", id, measureType);
		logger.printMethod(6, methodcall);
		
		List<Measure> list = people.readPersonHistory(id, measureType);
		logger.printMeasureList(list, false);
		return list;
	}

	/**
	 * Executes {@link People#readMeasureTypes()} and prints the result to the log file.
	 * @return
	 */
	public List<String> test_07_readMeasureTypes() {
		String methodcall = String.format("readMeasureTypes()");
		logger.printMethod(7, methodcall);
		
		List<String> list = people.readMeasureTypes();
		logger.printString(list.toString());
		return list;
	}

	/**
	 * Executes {@link People#readPersonMeasure(Long, String, Long)} and prints the result to the log file.
	 * @param id
	 * @param measureType
	 * @param mid
	 * @return
	 */
	public Measure test_08_readPersonMeasure(Long id, String measureType, Long mid) {
		String methodcall = String.format("readPersonMeasure(%d, %s, %d)", id, measureType, mid);
		logger.printMethod(8, methodcall);
		
		Measure m = people.readPersonMeasure(id, measureType, mid);
		logger.printMeasure(m);
		return m;
	}

	/**
	 * Executes {@link People#savePersonMeasure(Long, Measure)} and prints the result to the log file.
	 * @param id
	 * @param m
	 * @return
	 */
	public Measure test_09_savePersonMeasure(Long id, Measure m) {
		String methodcall = String.format("savePersonMeasure(%d, %s)", id, "m");		
		logger.printMethod(9, methodcall);
		logger.printParameter("m",	m);

		logger.printString("\nBefore saving measure: ");
		Person p = people.readPerson(id);
		logger.printPerson(p, true);

		
		m = people.savePersonMeasure(id, m);
		
		logger.printString("\nresult: ");
		p = people.readPerson(id);
		logger.printPerson(p, true);
		
		return m;
	}

	/**
	 * Executes {@link People#updatePersonMeasure(Long, Measure)} and prints the result to the log file.
	 * @param id
	 * @param m
	 * @return
	 */
	public Measure test_10_updatePersonMeasure(Long id, Measure m) {
		String methodcall = String.format("updatePersonMeasure(%d, %s)", id, "m");		
		logger.printMethod(10, methodcall);
		logger.printParameter("m",	m);
		
		Person p = people.readPerson(id);
		logger.printString("\nBefore updating measure: ");
		logger.printPerson(p, true);
		
		Measure ret = people.updatePersonMeasure(id, m);
		
		p = people.readPerson(id);
		logger.printString("\nresult: ");
		logger.printPerson(p, true);
		return ret;
	}
	
	/**
	 * Deletes all {@link Person} from the database.
	 */
	public void resetDatabase(){
		List<Person> list = people.readPersonList();
		for (int i = 0; i < list.size(); i++)
			people.deletePerson(list.get(i).getPersonId());		
	}
	
	/**
	 * Closes the PrintStream of the logfile.
	 */
	public void testFinished(){
		logger.close();
	}
}
