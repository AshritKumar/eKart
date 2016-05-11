package servletsEx.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="subCatagories")
public class SubCatagoryBean {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer subcatID;
	private String subCatName;
	
	@ManyToOne
	@JoinColumn(name="catagoryID")
	private CatagoryBean catagory;

	public Integer getSubcatID() {
		return subcatID;
	}

	public void setSubcatID(Integer subcatID) {
		this.subcatID = subcatID;
	}

	public String getSubCatName() {
		return subCatName;
	}

	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}

	public CatagoryBean getCatagory() {
		return catagory;
	}

	public void setCatagory(CatagoryBean catagory) {
		this.catagory = catagory;
	}
	
	
	

}
