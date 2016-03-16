package personstorecoketserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import person.Employee;
import person.Person;
import person.Skill;

public class CSVDataReader extends DataReader {
    private String csvFilePath;

    public CSVDataReader(String csvFilePath) {
	this.csvFilePath = csvFilePath;
    }

    private Employee parseDataFromLine(String line) {
	String[] personData = line.split(",");
	Employee employee = (Employee) new Person(personData[0], personData[1]);
	Skill skill = new Skill(personData[2], personData[3], Double.parseDouble(personData[4]));
	employee.addSkill(skill);
	try {
	    employee.setSalary(Integer.parseInt(personData[5]));
	} catch (NumberFormatException e) {
	    employee.setSalary(0);
	}

	return employee;
    }

    private void addSalary(Employee employee, String line) {
	String[] personData = line.split(",");
	try {
	    employee.setSalary(employee.getSalary() + Integer.parseInt(personData[5]));
	} catch (NumberFormatException e) {
	    employee.setSalary(employee.getSalary());
	}
    }
    
    private boolean checkPerson(Person person, String searchCriteria, SearchType searchType) {
	String[] criterias = searchCriteria.split(",");
	if (searchType == SearchType.OPTIONAL) 
	    for (Skill skill : person.getSkillSet()) {
		for (String criteria : criterias) {
		    if (skill.getName().equals(criteria)) {
			return true;
		}
	    }
	}
	if (searchType == SearchType.MANDATORY) {
	    List<String> criteriaList = Arrays.asList(criterias);
	    if (person.getSkillSet().containsAll(criteriaList)) {
		return true;
	    }
	}
	return false;
    }

    @Override
    public Set<Person> getPersons() {
	File csvFile = new File(csvFilePath);
	FileReader fReader;
	Set<Person> personsFromFile = new HashSet<>();
	try {
	    fReader = new FileReader(csvFile);
	    BufferedReader bReader = new BufferedReader(fReader);
	    String line;
	    while ((line = bReader.readLine()) != null) {
		Person personToAdd = parseDataFromLine(line);
		if (personsFromFile.contains(personToAdd)) {
		    addSalary((Employee) personToAdd, line);
		}
		if (checkPerson(personToAdd, searchCriteria, searchType)) {
		    personsFromFile.add(personToAdd);
		}
	    }

	    bReader.close();
	} catch (FileNotFoundException e) {
	    System.out.println("File not foubd.");
	    return null;
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return personsFromFile;
    }
}
