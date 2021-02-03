package com.desgreen.gov.database.model;

import java.io.Serializable;
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
@Table(name = "tb_opd_menu")
public class TbOpdMenu implements Serializable{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id = 0;

    @ManyToOne
    @JoinColumn(name = "opd_bean", referencedColumnName = "id")
	private TbOpd opdBean;

    @ManyToOne
    @JoinColumn(name = "jenis_sarana_bean", referencedColumnName = "id")
	private TbJenisSarana jenisSaranaBean;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((jenisSaranaBean == null) ? 0 : jenisSaranaBean.hashCode());
		result = prime * result + ((opdBean == null) ? 0 : opdBean.hashCode());
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
		TbOpdMenu other = (TbOpdMenu) obj;
		if (id != other.id)
			return false;
		if (jenisSaranaBean == null) {
			if (other.jenisSaranaBean != null)
				return false;
		} else if (!jenisSaranaBean.equals(other.jenisSaranaBean))
			return false;
		if (opdBean == null) {
			if (other.opdBean != null)
				return false;
		} else if (!opdBean.equals(other.opdBean))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "" + id + "" + jenisSaranaBean + "" + opdBean + "";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TbOpd getOpdBean() {
		return opdBean;
	}

	public void setOpdBean(TbOpd opdBean) {
		this.opdBean = opdBean;
	}

	public TbJenisSarana getJenisSaranaBean() {
		return jenisSaranaBean;
	}

	public void setJenisSaranaBean(TbJenisSarana jenisSaranaBean) {
		this.jenisSaranaBean = jenisSaranaBean;
	}

	
}