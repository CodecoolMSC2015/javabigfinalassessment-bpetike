package personstorecoketserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;


public class PersonStoreServerSocket {
    private DataReader store;
    
    public PersonStoreServerSocket() {
	store = new CSVDataReader("persons.csv");
	start();
    }
    
    public void start() {
	ServerSocket serverSocket = null;
	Socket socket = null;
	InputStream inStream = null;
	OutputStream outStream = null;
	ObjectInputStream objInStream = null;
	ObjectOutputStream objOutStream = null;
	try {
	    serverSocket = new ServerSocket(7555);
	    socket = serverSocket.accept();
	    inStream = socket.getInputStream();
	    outStream = socket.getOutputStream();
	    objInStream = new ObjectInputStream(inStream);
	    objOutStream = new ObjectOutputStream(outStream);
	    
	    while (true) {
		String searchCriteria = (String) objInStream.readObject();
		SearchType searchType = (SearchType) objInStream.readObject();
		store.setSearchCriteria(searchCriteria);
		store.setSearchType(searchType);
		Set<person.Person> personsToSend = store.getPersons();
		objOutStream.writeObject(personsToSend);
		if (socket.isClosed()) {
		    break;
		}
	    }
	    objOutStream.close();
	    objInStream.close();
	    outStream.close();
	    inStream.close();
	    socket.close();
	    serverSocket.close();
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
    }
}
