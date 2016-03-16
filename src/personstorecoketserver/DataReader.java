package personstorecoketserver;

import java.util.Set;

import person.Person;

public abstract class DataReader {

    protected String searchCriteria;
    protected SearchType searchType;
    
    public void setSearchCriteria(String searchCriteria) {
	this.searchCriteria = searchCriteria;
    }
    
    public void setSearchType(SearchType searchType) {
	this.searchType = searchType;
    }
    
    public abstract Set<Person> getPersons();
}
