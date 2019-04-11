package com.dib.dib.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data_raw")
public class DataRawFormat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer iD ;
	
	@Column(name = "hoSoSo")
	private String hoSoSo;
	
	@Column(name = "hoSoGoc")
	private String hoSoGoc;
	
	@Column(name = "hoVaTen")
	private String hoVaTen;

	@Column(name = "biDanh")
	private String biDanh;
	
	@Column(name = "ten")
	private String ten;
	
	@Column(name = "hoTenDayDu")
	private String hoTenDayDu;
	
	@Column(name = "ngayThangNamSinh")
	private String ngayThangNamSinh;
	
	@Column(name = "ngaySinh")
	private String ngaySinh ;
	
	@Column(name = "thangSinh")
	private String thangSinh;
	
	@Column(name = "namSinh")
	private Integer namSinh;
	
	@Column(name = "queQuanDD")
	private String queQuanDD;
	
	@Column(name = "queQuanXH")
	private String queQuanXH;
	
	@Column(name = "queQuanTinh")
	private String queQuanTinh;
	
	@Column(name = "queQuanHuyen")
	private String queQuanHuyen;
	
	@Column(name = "queQuanXa")
	private String queQuanXa;
	
	@Column(name = "coQuan")
	private String coQuan;
	
	@Column(name = "ngayDiB")
	private String ngayDiB;
	
	@Column(name = "thangDiB")
	private String thangDiB;
	
	@Column(name = "namDiB")
	private Integer namDiB;
	
	@Column(name = "ngayThangNamDiB")
	private String ngayThangNamDiB;
	
	@Column(name="tenTimKiem")
	private String tenTimKiem;

	public Integer getiD() {
		return iD;
	}

	public void setiD(Integer iD) {
		this.iD = iD;
	}

	public String getHoSoSo() {
		return hoSoSo;
	}

	public void setHoSoSo(String hoSoSo) {
		this.hoSoSo = hoSoSo;
	}

	public String getHoSoGoc() {
		return hoSoGoc;
	}

	public void setHoSoGoc(String hoSoGoc) {
		this.hoSoGoc = hoSoGoc;
	}

	public String getHoVaTen() {
		return hoVaTen;
	}

	public void setHoVaTen(String hoVaTen) {
		this.hoVaTen = hoVaTen;
	}

	public String getBiDanh() {
		return biDanh;
	}

	public void setBiDanh(String biDanh) {
		this.biDanh = biDanh;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getHoTenDayDu() {
		return hoTenDayDu;
	}

	public void setHoTenDayDu(String hoTenDayDu) {
		this.hoTenDayDu = hoTenDayDu;
	}

	public String getNgayThangNamSinh() {
		return ngayThangNamSinh;
	}

	public void setNgayThangNamSinh(String ngayThangNamSinh) {
		this.ngayThangNamSinh = ngayThangNamSinh;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getThangSinh() {
		return thangSinh;
	}

	public void setThangSinh(String thangSinh) {
		this.thangSinh = thangSinh;
	}

	public Integer getNamSinh() {
		return namSinh;
	}

	public void setNamSinh(Integer namSinh) {
		this.namSinh = namSinh;
	}

	public String getQueQuanDD() {
		return queQuanDD;
	}

	public void setQueQuanDD(String queQuanDD) {
		this.queQuanDD = queQuanDD;
	}

	public String getQueQuanXH() {
		return queQuanXH;
	}

	public void setQueQuanXH(String queQuanXH) {
		this.queQuanXH = queQuanXH;
	}

	public String getQueQuanTinh() {
		return queQuanTinh;
	}

	public void setQueQuanTinh(String queQuanTinh) {
		this.queQuanTinh = queQuanTinh;
	}

	public String getQueQuanHuyen() {
		return queQuanHuyen;
	}

	public void setQueQuanHuyen(String queQuanHuyen) {
		this.queQuanHuyen = queQuanHuyen;
	}

	public String getQueQuanXa() {
		return queQuanXa;
	}

	public void setQueQuanXa(String queQuanXa) {
		this.queQuanXa = queQuanXa;
	}

	public String getCoQuan() {
		return coQuan;
	}

	public void setCoQuan(String coQuan) {
		this.coQuan = coQuan;
	}

	public String getNgayDiB() {
		return ngayDiB;
	}

	public void setNgayDiB(String ngayDiB) {
		this.ngayDiB = ngayDiB;
	}

	public String getThangDiB() {
		return thangDiB;
	}

	public void setThangDiB(String thangDiB) {
		this.thangDiB = thangDiB;
	}

	public Integer getNamDiB() {
		return namDiB;
	}

	public void setNamDiB(Integer namDiB) {
		this.namDiB = namDiB;
	}

	public String getNgayThangNamDiB() {
		return ngayThangNamDiB;
	}

	public void setNgayThangNamDiB(String ngayThangNamDiB) {
		this.ngayThangNamDiB = ngayThangNamDiB;
	}

	public String getTenTimKiem() {
		return tenTimKiem;
	}

	public void setTenTimKiem(String tenTimKiem) {
		this.tenTimKiem = tenTimKiem;
	}

	@Override
	public String toString() {
		return "DataRawFormat [iD=" + iD + ", hoSoSo=" + hoSoSo + ", hoSoGoc=" + hoSoGoc + ", hoVaTen=" + hoVaTen
				+ ", biDanh=" + biDanh + ", ten=" + ten + ", hoTenDayDu=" + hoTenDayDu + ", ngayThangNamSinh="
				+ ngayThangNamSinh + ", ngaySinh=" + ngaySinh + ", thangSinh=" + thangSinh + ", namSinh=" + namSinh
				+ ", queQuanDD=" + queQuanDD + ", queQuanXH=" + queQuanXH + ", queQuanTinh=" + queQuanTinh
				+ ", queQuanHuyen=" + queQuanHuyen + ", queQuanXa=" + queQuanXa + ", coQuan=" + coQuan + ", ngayDiB="
				+ ngayDiB + ", thangDiB=" + thangDiB + ", namDiB=" + namDiB + ", ngayThangNamDiB=" + ngayThangNamDiB
				+ ", tenTimKiem=" + tenTimKiem + "]";
	}
	
	
}
