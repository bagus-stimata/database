package com.desgreen.gov.database.model;

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

import com.desgreen.gov.database.model_enum.EnumSatuanUnit;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "tb_kegiatan")
public class TbKegiatan {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;
	@Column(name = "kode1", length = 20)
	private String kode1 = "";
	@Column(name = "kode2", length = 20)
	private String kode2 = "";
	@Column(name = "description1", length = 200)
	private String description1 = "";
	@Column(name = "description2", length = 200)
    private String description2 = "";

	@Column(name = "panjang_volume")
	private Integer panjangVolume = 0;

	@Column(name = "enum_satuan_unit", length = 20)
    @Enumerated(EnumType.STRING)
    private EnumSatuanUnit enumSatuanUnit;

	@Column(name = "tahun_anggaran")
	private Integer tahunAnggaran = 0;
	@Column(name = "nilai_pagu")
	private Double nilaiPagu = 0.0;

    @ManyToOne
    @JoinColumn(name = "opd_bean", referencedColumnName = "id")
    private TbOpd opdBean;
    @ManyToOne
    @JoinColumn(name = "sumber_dana_bean", referencedColumnName = "id")
	private TbSumberDana sumberDanaBean;
    @ManyToOne
    @JoinColumn(name = "kegiatan_jenis_bean", referencedColumnName = "id")
	private TbKegiatanJenis kegiatanJenisBean;

    @ManyToOne
    @JoinColumn(name = "opd_bidang_bean", referencedColumnName = "id")
    private TbOpdBidang opdBidangBean;
        
    @ManyToOne
    @JoinColumn(name = "jenis_prasarana_bean", referencedColumnName = "id")
	private TbJenisPrasarana jenisPrasaranaBean;
    @ManyToOne
    @JoinColumn(name = "jenis_sarana_bean", referencedColumnName = "id")
	private TbJenisSarana jenisSaranaBean;


    @ManyToOne
    @JoinColumn(name = "jenis_jalan_bean", referencedColumnName = "id")
    private TbJenisJalan jenisJalanBean;
    @ManyToOne
    @JoinColumn(name = "status_jalan_bean", referencedColumnName = "id")
    private TbStatusJalan statusJalanBean;


    /**
     * isi data tabel sarana masih bermasah
     * Kemungkinan besar relasi table nya
     * Sementara di non aktifkan dahulu
     */
    // @ManyToOne
    // @JoinColumn(name = "sarana_bean", referencedColumnName = "id")
    // private TbSarana saranaBean;
    
    @ManyToOne
    @JoinColumn(name = "prasarana_bean", referencedColumnName = "id")
    private TbPrasarana prasaranaBean;

	@OneToMany(mappedBy = "kegiatanBean")
	private List<TbKegiatanPics> listKegiatanPics;
	@OneToMany(mappedBy = "kegiatanBean")
	private List<TbKegiatanLokasi> listKegiatanLokasi;
	
	@Transient
	private Integer tempInt1 = 0;
	@Transient
	private String tempString1 = "";

    
	@Column(name = "created")
	private LocalDateTime created = LocalDateTime.now();
	@Column(name = "lastmodified")
	private LocalDateTime lastModified = LocalDateTime.now();
	@Column(name = "modified_by")
	private String modifiedBy = "";

   

    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
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
        TbKegiatan other = (TbKegiatan) obj;
        if (id != other.id)
            return false;
        return true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKode1() {
        return kode1;
    }

    public void setKode1(String kode1) {
        this.kode1 = kode1;
    }

    public String getKode2() {
        return kode2;
    }

    public void setKode2(String kode2) {
        this.kode2 = kode2;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getDescription2() {
        return description2;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public Integer getPanjangVolume() {
        return panjangVolume;
    }

    public void setPanjangVolume(Integer panjangVolume) {
        this.panjangVolume = panjangVolume;
    }

    public EnumSatuanUnit getEnumSatuanUnit() {
        return enumSatuanUnit;
    }

    public void setEnumSatuanUnit(EnumSatuanUnit enumSatuanUnit) {
        this.enumSatuanUnit = enumSatuanUnit;
    }

    public Integer getTahunAnggaran() {
        return tahunAnggaran;
    }

    public void setTahunAnggaran(Integer tahunAnggaran) {
        this.tahunAnggaran = tahunAnggaran;
    }

    public Double getNilaiPagu() {
        return nilaiPagu;
    }

    public void setNilaiPagu(Double nilaiPagu) {
        this.nilaiPagu = nilaiPagu;
    }

    public TbOpd getOpdBean() {
        return opdBean;
    }

    public void setOpdBean(TbOpd opdBean) {
        this.opdBean = opdBean;
    }

    public TbSumberDana getSumberDanaBean() {
        return sumberDanaBean;
    }

    public void setSumberDanaBean(TbSumberDana sumberDanaBean) {
        this.sumberDanaBean = sumberDanaBean;
    }

    public TbKegiatanJenis getKegiatanJenisBean() {
        return kegiatanJenisBean;
    }

    public void setKegiatanJenisBean(TbKegiatanJenis kegiatanJenisBean) {
        this.kegiatanJenisBean = kegiatanJenisBean;
    }

    public TbOpdBidang getOpdBidangBean() {
        return opdBidangBean;
    }

    public void setOpdBidangBean(TbOpdBidang opdBidangBean) {
        this.opdBidangBean = opdBidangBean;
    }

    public TbJenisPrasarana getJenisPrasaranaBean() {
        return jenisPrasaranaBean;
    }

    public void setJenisPrasaranaBean(TbJenisPrasarana jenisPrasaranaBean) {
        this.jenisPrasaranaBean = jenisPrasaranaBean;
    }

    public TbJenisSarana getJenisSaranaBean() {
        return jenisSaranaBean;
    }

    public void setJenisSaranaBean(TbJenisSarana jenisSaranaBean) {
        this.jenisSaranaBean = jenisSaranaBean;
    }

    public TbPrasarana getPrasaranaBean() {
        return prasaranaBean;
    }

    public void setPrasaranaBean(TbPrasarana prasaranaBean) {
        this.prasaranaBean = prasaranaBean;
    }

    public List<TbKegiatanPics> getListKegiatanPics() {
        return listKegiatanPics;
    }

    public void setListKegiatanPics(List<TbKegiatanPics> listKegiatanPics) {
        this.listKegiatanPics = listKegiatanPics;
    }

    public List<TbKegiatanLokasi> getListKegiatanLokasi() {
        return listKegiatanLokasi;
    }

    public void setListKegiatanLokasi(List<TbKegiatanLokasi> listKegiatanLokasi) {
        this.listKegiatanLokasi = listKegiatanLokasi;
    }

    public Integer getTempInt1() {
        return tempInt1;
    }

    public void setTempInt1(Integer tempInt1) {
        this.tempInt1 = tempInt1;
    }

    public String getTempString1() {
        return tempString1;
    }

    public void setTempString1(String tempString1) {
        this.tempString1 = tempString1;
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

     
  

    /**
     * @return TbJenisJalan return the jenisJalanBean
     */
    public TbJenisJalan getJenisJalanBean() {
        return jenisJalanBean;
    }

    /**
     * @param jenisJalanBean the jenisJalanBean to set
     */
    public void setJenisJalanBean(TbJenisJalan jenisJalanBean) {
        this.jenisJalanBean = jenisJalanBean;
    }

    /**
     * @return TbStatusJalan return the statusJalanBean
     */
    public TbStatusJalan getStatusJalanBean() {
        return statusJalanBean;
    }

    /**
     * @param statusJalanBean the statusJalanBean to set
     */
    public void setStatusJalanBean(TbStatusJalan statusJalanBean) {
        this.statusJalanBean = statusJalanBean;
    }

}