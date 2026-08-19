package vn.edu.crs.course_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ten_mon_hoc", length = 255, nullable = false)
    private String tenMonHoc;

    @Column(name = "so_tin_chi", nullable = false)
    private Integer soTinChi;

    @Column(name = "so_cho_toi_da", nullable = false)
    private Integer soChoToiDa;

    @Column(name = "so_cho_con_lai", nullable = false)
    private Integer soChoConLai;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTenMonHoc() {
        return this.tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public Integer getSoTinChi() {
        return this.soTinChi;
    }

    public void setSoTinChi(Integer soTinChi) {
        this.soTinChi = soTinChi;
    }

    public Integer getSoChoToiDa() {
        return this.soChoToiDa;
    }

    public void setSoChoToiDa(Integer soChoToiDa) {
        this.soChoToiDa = soChoToiDa;
    }

    public Integer getSoChoConLai() {
        return this.soChoConLai;
    }

    public void setSoChoConLai(Integer soChoConLai) {
        this.soChoConLai = soChoConLai;
    }

    public Course() {
    }

    public Course(long id, String tenMonHoc, Integer soTinChi, Integer soChoToiDa, Integer soChoConLai) {
        this.id = id;
        this.tenMonHoc = tenMonHoc;
        this.soTinChi = soTinChi;
        this.soChoToiDa = soChoToiDa;
        this.soChoConLai = soChoConLai;
    }
}
