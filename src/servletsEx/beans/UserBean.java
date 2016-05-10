package servletsEx.beans;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
@Entity
@Table(name="user",uniqueConstraints={@UniqueConstraint(columnNames={"userName"}),@UniqueConstraint(columnNames={"email"})})
public class UserBean {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer userID;
	private String userName;
	private String email;
	private String firstName;
	private String lastName;
	
	/*@ElementCollection specifies that the obj is a value obj, ie it dose not have meaning on its own it has to be embedded in an entity
	 * to get a complete meaning. Here address is embedded in user bean entity to make the address obj as a user address*/
	
	@ElementCollection(fetch=FetchType.EAGER)//if it is eager, all the rows from the embedded table(address) are returned and set in the proxy obj.
	@JoinTable(name="address",joinColumns={@JoinColumn(name="usrID")})//Changing the table name of address and column user id
	@GenericGenerator(name="increment",strategy="increment")
	@CollectionId(columns = { @Column(name="addressID") }, generator = "increment", type = @Type(type="long"))
	//if we want to have an id for the join table we have to have a collection that supports indexes(lists)
	private Collection<AddressBean> address = new ArrayList<>();
	
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public Collection<AddressBean> getAddress() {
		return address;
	}
	public void setAddress(Collection<AddressBean> address) {
		this.address = address;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserBean other = (UserBean) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
	


}
