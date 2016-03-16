package personstorecoketserver;

import java.io.Serializable;

public class Employee extends Person implements Serializable {

    private static final long serialVersionUID = -958742761311568764L;
    
    private int salary;
    private String jobTitle;
    
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Employee's name: ").append(name).append("\n");
        sb.append("Employee's email : ").append(email).append("\n");
        sb.append("Employee's skill(s): ");
        for (Skill skill : skillSet) {
	    sb.append(skill.getName()).append(", ");
	}
        sb.append("\n");
	sb.append("Employee salary: ").append(salary).append("\n");
        
	return sb.toString();
    }
}
