package personstorecoketserver;

import java.io.Serializable;
import java.util.List;

public class Person implements Serializable {
    
    private static final long serialVersionUID = -2048729802579787250L;
    
    protected String name;
    protected String email;
    protected List<Skill> skillSet;
   
    public Person() {
	
    }
    
    public Person(String name, String email) {
	super();
	this.name = name;
	this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Skill> getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(List<Skill> skillSet) {
        this.skillSet = skillSet;
    }
    
    public void addSkill(Skill skill) {
	this.skillSet.add(skill);
    }

    @Override
    public int hashCode() {
	return -1;
    }

    @Override
    public boolean equals(Object obj) {
	Person other = null;
	if (obj instanceof Person) {
	    other = (Person) obj;
	} else {
	    return false;
	}
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	return true;
    }
    
    
    
}
