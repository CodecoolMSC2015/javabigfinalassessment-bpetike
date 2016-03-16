package personstorecoketserver;

import java.io.Serializable;

public class Skill implements Serializable {
    
    private static final long serialVersionUID = -2246918207600228812L;
    
    private String name;
    private String description;
    private double rate;
    
    public Skill(String name, String description, double rate) {
	this.name = name;
	this.description = description;
	this.rate = rate;
    }
    
    public String getName() {
	return name;
    }
    public String getDescription() {
	return description;
    }
    public double getRate() {
	return rate;
    }
}
