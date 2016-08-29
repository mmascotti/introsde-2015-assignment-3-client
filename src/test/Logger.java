package test;

import introsde.assignment.soap.Measure;
import introsde.assignment.soap.Person;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.datatype.XMLGregorianCalendar;

public class Logger {
	
	PrintStream stream;
	final String SEPARATOR = "\n----------------------------------------------\n";
	
	/**
	 * Creates a new {@link Logger} that prints log information in the {@link PrintStream} 'stream'.
	 * @param stream log information are printed to this {@link PrintStream}
	 */
	public Logger(PrintStream stream){
		this.stream = stream;
	}
	
	/**
	 * Closes the PrintStream of the logfile.
	 */
	public void close(){
		stream.close();
	}
	
	/**
	 * Writes the {@link String} 's' followed by a '\n'.
	 * @param s
	 */
	public void printString(String s){
		stream.println(s);
	}
	
	/**
	 * Logs the method name with the number specified in the 
	 * <a href="https://sites.google.com/a/unitn.it/introsde_2015-16/lab-sessions/assignments/assignment-3" >
	 * assignment description
	 * </a>.
	 * Before printing the number and the methodcall the {@link #SEPARATOR} String is printed for better readablility
	 * of the logfile.
	 * @param number number of the method
	 * @param method_call the method call, for example <code>readPerson(id)</code>
	 */
	public void printMethod(int number, String method_call){
		stream.println(SEPARATOR);
		stream.println(number + ": " + method_call);		
	}
	
	/**
	 * Print the specified list of {@link Person}s without the healthprofile.
	 * @param list list of persons
	 */
	public void printPersonList(List<Person> list){
		for (int i = 0; i < list.size(); i++)
			printPerson(list.get(i), false);
	}
	
	/**
	 * Prints the list of {@link Measure}s one per line. 
	 * The lines can be indented by a '\t' to show that the belongs to a {@link Person} printed before.
	 * @param list list of {@link Measure}s
	 * @param indent indent the lines
	 */
	public void printMeasureList(List<Measure> list, boolean indent){
		String indent_str = "";
		
		if (indent)
			indent_str = "\t";
		
		for (int i = 0; i < list.size(); i++){
			stream.print(indent_str);
			printMeasure(list.get(i));
		}
	}
	
	/**
	 * Prints the specified {@link Person}.
	 * @param p person to print
	 * @param print_measures print the healthprofile measures
	 */
	public void printPerson(Person p, boolean print_measures){
		String birthdate = dateString(p.getBirthdate(), false);
		stream.format("[%d] %s %s, %s\n", p.getPersonId(), p.getFirstname(), p.getLastname(), birthdate);
		if (print_measures)
			printMeasureList(p.getCurrentHealth(), true);
	}
	
	/**
	 * Prints the specified {@link Measure}.
	 * @param m
	 */
	public void printMeasure(Measure m){
		String reg_date = dateString(m.getDateRegistered(), true);
		stream.format("[%d] %s: %s %s (%s)\n", 
				m.getMid(), 
				m.getMeasureType(), 
				m.getMeasureValue(), 
				m.getMeasureValueType(),
				reg_date);
	}
	
	/**
	 * Prints the method parameter in the form <code> name = value </code>. <br>
	 * @param name
	 * @param value
	 */
	public void printParameter(String name, Object value){
		stream.print(name + " = ");
		if (value instanceof Person)
			printPerson((Person) value, false);
		else if (value instanceof Measure)
			printMeasure((Measure) value);
		else
			stream.print(value.toString());
	}
	
	/**
	 * Prints the date and the time (optional) of the {@link XMLGregorianCalendar} 'c'.
	 * @param c {@link XMLGregorianCalendar} to print
	 * @param print_time whether print the time
	 * @return {@link String} representation of the {@link XMLGregorianCalendar}
	 */
	private String dateString(XMLGregorianCalendar c, boolean print_time){
		if (c == null)
			return "[no date]";
		
		String format = "dd. MMM yyyy";
		
		if (print_time)
			format += " HH:mm:ss";
		
		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ITALY);
		Date d = c.toGregorianCalendar().getTime();
		return sdf.format(d);
	}
}
