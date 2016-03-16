package personstoreclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import person.Person;

public class PersonStoreClient {
    static Set<Person> result = null;
    static Socket socket = null;
    static ObjectOutputStream objOutStream = null;
    static ObjectInputStream objectInStream = null;

    public PersonStoreClient(String searchCriteria, SearchType searchType) {
	result = getDataFromServer(searchCriteria, searchType);
    }

    private Set<Person> getDataFromServer(String searchCriteria, SearchType searchType) {
	OutputStream outStream = null;
	InputStream inStream = null;
	Set<Person> searchResult = new HashSet<>();
	try {
	    socket = new Socket("localhost", 7555);
	    outStream = socket.getOutputStream();
	    inStream = socket.getInputStream();
	    objOutStream = new ObjectOutputStream(outStream);
	    objectInStream = new ObjectInputStream(inStream);
	    objOutStream.writeObject(searchCriteria);
	    objOutStream.writeObject(searchType);
	    searchResult = ((Set<Person>) objectInStream.readObject());

	} catch (UnknownHostException e) {
	    System.out.println(e.getMessage());
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}

	return searchResult;
    }

    public void exit() {
	try {
	    objOutStream.close();
	    objectInStream.close();
	    socket.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
