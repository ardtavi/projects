package ro.utcn.sd.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.omg.CORBA.Request;

@Entity
@Table(name = "CAR")
public class Car {

    @Id
    // This is a small trick to make Hibernate 5 generate ID properly for MySql. Normally just @GeneratedValue is enough
    @GeneratedValue(
            strategy = GenerationType.AUTO)

    @Column(name = "car_id")
   private int carid;

    @Column(name = "Car_name")
    private String carname;
   

    @Column(name = "User")
    private User user;
   
    
    
    //@OneToMany(cascade=CascadeType.ALL,mappedBy= "car")
   // private List<Request> requestlist=new ArrayList<>();
    
  
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return carid;
	}

	public void setId(int carid) {
		this.carid = carid;
	}
	 
	public String getName() {
		return carname;
	}

	public void setName(String carname) {
		this.carname = carname;
	}

//	public List<Request> getRequestList() {
//		return requestlist;
//	}
//
//	public void setRequestList(List<Request> requestlist) {
//		this.requestlist = requestlist;
//	}

}