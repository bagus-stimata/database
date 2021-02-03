package com.desgreen.gov.database.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_opd")
public class TbOpd implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id = 0;
	@Column(name = "kode1")
	private String kode1 = "";
	@Column(name = "kode2")
	private String kode2 = "";
	@Column(name = "description")
	private String description = "";
	@Column(name = "alamat")
	private String alamat = "";
	@Column(name = "contact")
	private String contact = "";
	@Column(name = "telp1")
	private String telp1 = "";
    
	@OneToMany(mappedBy = "opdBean")
    private List<TbOpdBidang> listOpdBidang;
    
	@OneToMany(mappedBy = "opdBean")
	private List<TbKegiatan> listKegiatan;

    @OneToMany(mappedBy = "opdBean")
    private List<TbOpdMenu> listOpdMenu;
    
    @Transient
    // private String[] tempOpdMenus;
    private List<String> tempOpdMenus;
  
	@OneToMany(mappedBy = "opdBean")
    private List<FUser> listFUser;

	@Transient
	private Integer tempInt1 = 0;
	
	@Column(name = "created")
	private LocalDateTime created = LocalDateTime.now();
	@Column(name = "lastmodified")
	private LocalDateTime lastModified = LocalDateTime.now();
	@Column(name = "modified_by")
	private String modifiedBy = "";




    /**
     * @return int return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return String return the kode1
     */
    public String getKode1() {
        return kode1;
    }

    /**
     * @param kode1 the kode1 to set
     */
    public void setKode1(String kode1) {
        this.kode1 = kode1;
    }

    /**
     * @return String return the kode2
     */
    public String getKode2() {
        return kode2;
    }

    /**
     * @param kode2 the kode2 to set
     */
    public void setKode2(String kode2) {
        this.kode2 = kode2;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return String return the alamat
     */
    public String getAlamat() {
        return alamat;
    }

    /**
     * @param alamat the alamat to set
     */
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    /**
     * @return String return the contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * @return String return the telp1
     */
    public String getTelp1() {
        return telp1;
    }

    /**
     * @param telp1 the telp1 to set
     */
    public void setTelp1(String telp1) {
        this.telp1 = telp1;
    }

    /**
     * @return List<TbOpdBidang> return the listOpdBidang
     */
    public List<TbOpdBidang> getListOpdBidang() {
        return listOpdBidang;
    }

    /**
     * @param listOpdBidang the listOpdBidang to set
     */
    public void setListOpdBidang(List<TbOpdBidang> listOpdBidang) {
        this.listOpdBidang = listOpdBidang;
    }



    /**
     * @return LocalDateTime return the created
     */
    public LocalDateTime getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    /**
     * @return LocalDateTime return the lastModified
     */
    public LocalDateTime getLastModified() {
        return lastModified;
    }

    /**
     * @param lastModified the lastModified to set
     */
    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * @return String return the modifiedBy
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * @param modifiedBy the modifiedBy to set
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		TbOpd other = (TbOpd) obj;
		if (id != other.id)
			return false;
		return true;
	}


    /**
     * @return Integer return the tempInt1
     */
    public Integer getTempInt1() {
        return tempInt1;
    }

    /**
     * @param tempInt1 the tempInt1 to set
     */
    public void setTempInt1(Integer tempInt1) {
        this.tempInt1 = tempInt1;
    }


    /**
     * @return List<TbKegiatan> return the listKegiatan
     */
    public List<TbKegiatan> getListKegiatan() {
        return listKegiatan;
    }

    /**
     * @param listKegiatan the listKegiatan to set
     */
    public void setListKegiatan(List<TbKegiatan> listKegiatan) {
        this.listKegiatan = listKegiatan;
    }


    /**
     * @return List<TbOpdMenu> return the listOpdMenu
     */
    public List<TbOpdMenu> getListOpdMenu() {
        return listOpdMenu;
    }

    /**
     * @param listOpdMenu the listOpdMenu to set
     */
    public void setListOpdMenu(List<TbOpdMenu> listOpdMenu) {
        this.listOpdMenu = listOpdMenu;
    }

    @Override
    public String toString() {
        return "TbOpd [id=" + id + "]";
    }

    

    /**
     * @return List<String> return the tempOpdMenus
     */
    public List<String> getTempOpdMenus() {
        return tempOpdMenus;
    }

    /**
     * @param tempOpdMenus the tempOpdMenus to set
     */
    public void setTempOpdMenus(List<String> tempOpdMenus) {
        this.tempOpdMenus = tempOpdMenus;
    }


    /**
     * @return List<FUser> return the listFUser
     */
    public List<FUser> getListFUser() {
        return listFUser;
    }

    /**
     * @param listFUser the listFUser to set
     */
    public void setListFUser(List<FUser> listFUser) {
        this.listFUser = listFUser;
    }

}