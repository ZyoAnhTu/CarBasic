package fpoly.edu.carproject.model;

import java.util.Date;

public class DonHang {
    private int maDH;
    private int maXe;
    private int maNV;
    private Date ngay;
    private int gia;
    private String maCCH;

    public DonHang() {
    }

    public DonHang(int maDH, int maXe, int maNV, Date ngay, int gia, String maCCH) {
        this.maDH = maDH;
        this.maXe = maXe;
        this.maNV = maNV;
        this.ngay = ngay;
        this.gia = gia;
        this.maCCH = maCCH;
    }

    public int getMaDH() {
        return maDH;
    }

    public void setMaDH(int maDH) {
        this.maDH = maDH;
    }

    public int getMaXe() {
        return maXe;
    }

    public void setMaXe(int maXe) {
        this.maXe = maXe;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getMaCCH() {
        return maCCH;
    }

    public void setMaCCH(String maCCH) {
        this.maCCH = maCCH;
    }
}
