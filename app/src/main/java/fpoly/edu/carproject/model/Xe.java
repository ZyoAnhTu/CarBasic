package fpoly.edu.carproject.model;

public class Xe {
    private int maXe;
    private String tenXe;
    private String dongXe;
    private String nhienLieu;
    private int giaBan;
    private int maLoaiXe;

    public Xe() {
    }

    public Xe(int maXe, String tenXe, String dongXe, String nhienLieu, int giaBan, int maLoaiXe) {
        this.maXe = maXe;
        this.tenXe = tenXe;
        this.dongXe = dongXe;
        this.nhienLieu = nhienLieu;
        this.giaBan = giaBan;
        this.maLoaiXe = maLoaiXe;
    }

    public int getMaXe() {
        return maXe;
    }

    public void setMaXe(int maXe) {
        this.maXe = maXe;
    }

    public String getTenXe() {
        return tenXe;
    }

    public void setTenXe(String tenXe) {
        this.tenXe = tenXe;
    }

    public String getDongXe() {
        return dongXe;
    }

    public void setDongXe(String dongXe) {
        this.dongXe = dongXe;
    }

    public String getNhienLieu() {
        return nhienLieu;
    }

    public void setNhienLieu(String nhienLieu) {
        this.nhienLieu = nhienLieu;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public int getMaLoaiXe() {
        return maLoaiXe;
    }

    public void setMaLoaiXe(int maLoaiXe) {
        this.maLoaiXe = maLoaiXe;
    }
}
