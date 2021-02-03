package com.desgreen.gov.database.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
@Table(name = "tb_kegiatan_lokasi")
public class TbKegiatanLokasi {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id = 0;


	@Column(name = "nilai_dana")
	private Double nilaiDana = 0.0;

	@Column(name = "tag_lat1")
	private Double tagLat1 = 0.0;
	@Column(name = "tag_lon1")
	private Double tagLon1 = 0.0;
	@Column(name = "tag_lat2")
	private Double tagLat2 = 0.0;
	@Column(name = "tag_lon2")
	private Double tagLon2 = 0.0;
	@Column(name = "image_name1")
	private String imageName1 = "";
	@Column(name = "image_name2")
	private String imageName2 = "";
	@Column(name = "image_name3")
	private String imageName3 = "";
	@Column(name = "kondisi1")
	private Integer kondisi1 = 0;
	@Column(name = "kondisi2")
	private Integer kondisi2 = 0;
	
    @ManyToOne
    @JoinColumn(name = "kegiatan_bean", referencedColumnName = "id")
	private TbKegiatan kegiatanBean;

    @ManyToOne
    @JoinColumn(name = "kecamatan_bean", referencedColumnName = "id")
	private TbKecamatan kecamatanBean;
    @ManyToOne
    @JoinColumn(name = "desa_bean", referencedColumnName = "id")
	private TbDesa desaBean;

	@OneToMany(mappedBy = "kegiatanLokasiBean")
	private List<TbKegiatanLokasiPics> listKegiatanLokasiPics;


	@Transient
	private int tempInt1 = 0;
	
	@Column(name = "created")
	private LocalDateTime created = LocalDateTime.now();
	@Column(name = "lastmodified")
	private LocalDateTime lastModified = LocalDateTime.now();
	@Column(name = "modified_by")
	private String modifiedBy = "";



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
		TbKegiatanLokasi other = (TbKegiatanLokasi) obj;
		if (id != other.id)
			return false;
		return true;
	}


    /**
     * @return long return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return Double return the nilaiDana
     */
    public Double getNilaiDana() {
        return nilaiDana;
    }

    /**
     * @param nilaiDana the nilaiDana to set
     */
    public void setNilaiDana(Double nilaiDana) {
        this.nilaiDana = nilaiDana;
    }

    /**
     * @return Double return the tagLat1
     */
    public Double getTagLat1() {
        return tagLat1;
    }

    /**
     * @param tagLat1 the tagLat1 to set
     */
    public void setTagLat1(Double tagLat1) {
        this.tagLat1 = tagLat1;
    }

    /**
     * @return Double return the tagLon1
     */
    public Double getTagLon1() {
        return tagLon1;
    }

    /**
     * @param tagLon1 the tagLon1 to set
     */
    public void setTagLon1(Double tagLon1) {
        this.tagLon1 = tagLon1;
    }

    /**
     * @return Double return the tagLat2
     */
    public Double getTagLat2() {
        return tagLat2;
    }

    /**
     * @param tagLat2 the tagLat2 to set
     */
    public void setTagLat2(Double tagLat2) {
        this.tagLat2 = tagLat2;
    }

    /**
     * @return Double return the tagLon2
     */
    public Double getTagLon2() {
        return tagLon2;
    }

    /**
     * @param tagLon2 the tagLon2 to set
     */
    public void setTagLon2(Double tagLon2) {
        this.tagLon2 = tagLon2;
    }

    /**
     * @return String return the imageName1
     */
    public String getImageName1() {
        return imageName1;
    }

    /**
     * @param imageName1 the imageName1 to set
     */
    public void setImageName1(String imageName1) {
        this.imageName1 = imageName1;
    }

    /**
     * @return String return the imageName2
     */
    public String getImageName2() {
        return imageName2;
    }

    /**
     * @param imageName2 the imageName2 to set
     */
    public void setImageName2(String imageName2) {
        this.imageName2 = imageName2;
    }

    /**
     * @return String return the imageName3
     */
    public String getImageName3() {
        return imageName3;
    }

    /**
     * @param imageName3 the imageName3 to set
     */
    public void setImageName3(String imageName3) {
        this.imageName3 = imageName3;
    }

    /**
     * @return Integer return the kondisi1
     */
    public Integer getKondisi1() {
        return kondisi1;
    }

    /**
     * @param kondisi1 the kondisi1 to set
     */
    public void setKondisi1(Integer kondisi1) {
        this.kondisi1 = kondisi1;
    }

    /**
     * @return Integer return the kondisi2
     */
    public Integer getKondisi2() {
        return kondisi2;
    }

    /**
     * @param kondisi2 the kondisi2 to set
     */
    public void setKondisi2(Integer kondisi2) {
        this.kondisi2 = kondisi2;
    }

    /**
     * @return TbKegiatan return the kegiatanBean
     */
    public TbKegiatan getKegiatanBean() {
        return kegiatanBean;
    }

    /**
     * @param kegiatanBean the kegiatanBean to set
     */
    public void setKegiatanBean(TbKegiatan kegiatanBean) {
        this.kegiatanBean = kegiatanBean;
    }

    /**
     * @return TbKecamatan return the kecamatanBean
     */
    public TbKecamatan getKecamatanBean() {
        return kecamatanBean;
    }

    /**
     * @param kecamatanBean the kecamatanBean to set
     */
    public void setKecamatanBean(TbKecamatan kecamatanBean) {
        this.kecamatanBean = kecamatanBean;
    }

    /**
     * @return TbDesa return the desaBean
     */
    public TbDesa getDesaBean() {
        return desaBean;
    }

    /**
     * @param desaBean the desaBean to set
     */
    public void setDesaBean(TbDesa desaBean) {
        this.desaBean = desaBean;
    }

    /**
     * @return List<TbKegiatanLokasiPics> return the listKegiatanLokasiPics
     */
    public List<TbKegiatanLokasiPics> getListKegiatanLokasiPics() {
        return listKegiatanLokasiPics;
    }

    /**
     * @param listKegiatanLokasiPics the listKegiatanLokasiPics to set
     */
    public void setListKegiatanLokasiPics(List<TbKegiatanLokasiPics> listKegiatanLokasiPics) {
        this.listKegiatanLokasiPics = listKegiatanLokasiPics;
    }

    /**
     * @return int return the tempInt1
     */
    public int getTempInt1() {
        return tempInt1;
    }

    /**
     * @param tempInt1 the tempInt1 to set
     */
    public void setTempInt1(int tempInt1) {
        this.tempInt1 = tempInt1;
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

}