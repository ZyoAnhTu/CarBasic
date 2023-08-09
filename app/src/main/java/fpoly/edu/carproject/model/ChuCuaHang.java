package fpoly.edu.carproject.model;

public class ChuCuaHang {
    private String maCCH;
    private String hoTen;
    private String matKhau;

    public ChuCuaHang() {
    }

    public ChuCuaHang(String maCCH, String hoTen, String matKhau) {
        this.maCCH = maCCH;
        this.hoTen = hoTen;
        this.matKhau = matKhau;
    }

    public String getMaCCH() {
        return maCCH;
    }

    public void setMaCCH(String maCCH) {
        this.maCCH = maCCH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
}
