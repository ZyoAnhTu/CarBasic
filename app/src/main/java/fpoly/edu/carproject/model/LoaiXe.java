package fpoly.edu.carproject.model;

public class LoaiXe {
    private int maLoaiXe;
    private String tenLoaiXe;

    public LoaiXe() {
    }

    public LoaiXe(int maLoaiXe, String tenLoaiXe) {
        this.maLoaiXe = maLoaiXe;
        this.tenLoaiXe = tenLoaiXe;
    }

    public int getMaLoaiXe() {
        return maLoaiXe;
    }

    public void setMaLoaiXe(int maLoaiXe) {
        this.maLoaiXe = maLoaiXe;
    }

    public String getTenLoaiXe() {
        return tenLoaiXe;
    }

    public void setTenLoaiXe(String tenLoaiXe) {
        this.tenLoaiXe = tenLoaiXe;
    }
}
