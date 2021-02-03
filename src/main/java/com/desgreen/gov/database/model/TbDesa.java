package com.desgreen.gov.database.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "tb_desa")
public class TbDesa {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id = 0;
	@Column(name = "kode1")
	private String kode1 = "";
	@Column(name = "description")
	private String description = "";

    @ManyToOne
    @JoinColumn(name = "kecamatan_bean", referencedColumnName = "id")
	private TbKecamatan kecamatanBean;

	@Column(name = "kecamatan_bean_old")
	private String kecamatanBean_old;
    @OneToMany(mappedBy = "desaBean")
	private List<TbKegiatanLokasi> listKegiatanLokasi;

    @OneToMany(mappedBy = "desaBean")
	private List<TbPrasarana> listPrasarana;
    @OneToMany(mappedBy = "desaBean")
	private List<TbSarana> listSarana;

	@Transient
	private Integer tempInt1 = 0;
	
	@Column(name = "created")
	private LocalDateTime created = LocalDateTime.now();
	@Column(name = "lastmodified")
	private LocalDateTime lastModified = LocalDateTime.now();
	@Column(name = "modified_by")
	private String modifiedBy = "";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKode1() {
		return kode1;
	}

	public void setKode1(String kode1) {
		this.kode1 = kode1;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TbKecamatan getKecamatanBean() {
		return kecamatanBean;
	}

	public void setKecamatanBean(TbKecamatan kecamatanBean) {
		this.kecamatanBean = kecamatanBean;
	}

	public List<TbPrasarana> getListPrasarana() {
		return listPrasarana;
	}

	public void setListPrasarana(List<TbPrasarana> listPrasarana) {
		this.listPrasarana = listPrasarana;
	}

	public List<TbSarana> getListSarana() {
		return listSarana;
	}

	public void setListSarana(List<TbSarana> listSarana) {
		this.listSarana = listSarana;
	}

	public List<TbKegiatanLokasi> getListKegiatanLokasi() {
		return listKegiatanLokasi;
	}

	public void setListKegiatanLokasi(List<TbKegiatanLokasi> listKegiatanLokasi) {
		this.listKegiatanLokasi = listKegiatanLokasi;
	}

	

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(LocalDateTime lastModified) {
		this.lastModified = lastModified;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

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
		TbDesa other = (TbDesa) obj;
		if (id != other.id)
			return false;
		return true;
	}




    /**
     * @return String return the kecamatanBean_old
     */
    public String getKecamatanBean_old() {
        return kecamatanBean_old;
    }

    /**
     * @param kecamatanBean_old the kecamatanBean_old to set
     */
    public void setKecamatanBean_old(String kecamatanBean_old) {
        this.kecamatanBean_old = kecamatanBean_old;
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

}