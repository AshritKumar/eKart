package servletsEx.beans;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="catagories")
public class CatagoryBean {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer catagoryID;
	
	private String catagoryName;
	
	@OneToMany(mappedBy="catagory")
	private Collection<SubCatagoryBean> subCatagory = new ArrayList<>();

	public Integer getCatagoryID() {
		return catagoryID;
	}

	public void setCatagoryID(Integer catagoryID) {
		this.catagoryID = catagoryID;
	}

	public String getCatagoryName() {
		return catagoryName;
	}

	public void setCatagoryName(String catagoryName) {
		this.catagoryName = catagoryName;
	}
}
