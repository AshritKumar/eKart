package servletsEx.beans;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="items")
public class ItemBean {
	@Id
	//@TableGenerator(name="incrementID",initialValue=182)
	@GenericGenerator(name="increment",strategy="increment")
	@GeneratedValue(generator="increment")
	private Integer itemID;
	
	private String itemName;
	
	@Lob
	private String itemDesc;
	
	@OneToOne
	@JoinColumn(name="catagoryID")
	private CatagoryBean catagory;
	
	private Integer subCatID;
	private Integer stock;
	private Integer supID;
	private Double price;
	
	@Transient
	//@Temporal(TemporalType.DATE)
	private static Date date;
	
	
	public Integer getItemID() {
		return itemID;
	}
	public void setItemID(Integer itemID) {
		this.itemID = itemID;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public Integer getSubCatID() {
		return subCatID;
	}
	public void setSubCatID(Integer subCatID) {
		this.subCatID = subCatID;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getSupID() {
		return supID;
	}
	public void setSupID(Integer supID) {
		this.supID = supID;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
	public CatagoryBean getCatagory() {
		return catagory;
	}
	public void setCatagory(CatagoryBean catagory) {
		this.catagory = catagory;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemID == null) ? 0 : itemID.hashCode());
		result = prime * result
				+ ((itemName == null) ? 0 : itemName.hashCode());
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
		ItemBean other = (ItemBean) obj;
		if (itemID == null) {
			if (other.itemID != null)
				return false;
		} else if (!itemID.equals(other.itemID))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return  itemID+" : "+itemName;
	}
	
	
	
	
	
}
