package fpoly.edu.carproject.model;

public class Top {
    private String Xe;
    private int soLuong;

    public Top() {
    }

    public Top(String xe, int soLuong) {
        Xe = xe;
        this.soLuong = soLuong;
    }

    public String getXe() {
        return Xe;
    }

    public void setXe(String xe) {
        Xe = xe;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
