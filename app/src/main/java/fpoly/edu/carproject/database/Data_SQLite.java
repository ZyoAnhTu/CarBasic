package fpoly.edu.carproject.database;

public class Data_SQLite {
    public static final String INSERT_CHU_CUA_HANG = "INSERT INTO ChuCuaHang (maCCH, hoTen, matKhau) VALUES " +
            "('ngvana','Nguyen Van A','123456')," +
            "('ngvanb','Nguyen Van B','123456')," +
            "('ngvanc','Nguyen Van C','123456')," +
            "('ngvand','Nguyen Van D','123456')," +
            "('ngvane','Nguyen Van E','123456')," +
            "('ngvanf','Nguyen Van F','123456')";
    public static final String INSERT_LOAI_XE = "INSERT INTO LoaiXe (tenLoaiXe)  VALUES" +
            "(' Phân khúc xe hạng A ')," +
            "('Phân khúc xe hạng B')," +
            "('Phân khúc hạng C')," +
            "(' Phân khúc xe hạng D')," +
            "('Phân khúc xe hạng E')," +
            "(' Phân khúc xe hạng J')," +
            "('Phân khúc xe hạng S ')";
    public static final String INSERT_NHAN_VIEN = "INSERT INTO NhanVien(hoten,namSinh,soDT) VALUES " +
            "(' Tran Van A ','2000',123456)," +
            "('Tran Van B','2001',134567)," +
            "('Tran Van C','2000',145678)," +
            "('Tran Van D','2002',156789)," +
            "('Tran Van F','2003',1678910)";
    public static final String INSERT_XE = "INSERT INTO Xe(tenXe,dongXe,nhienLieu,giaBan,maLoaiXe) VALUES" +
            "('Honda ','Honda CR – V 2022','Xăng',10000,1)," +
            "('Mercedes ','Mercedes C-Class','Xăng',30000,2)," +
            "('BMW ','BMW 530i MSport 2021','Xăng',50000,2)," +
            "('Porsche ','Porsche Cayenne 2022','Xăng',60000,2)," +
            "('Rolls-Royce ','Rolls-Royce Phantom EWB','Xăng',90000,5)";
    public static final String INSERT_DON_HANG = "INSERT INTO DonHang(maXe,maNV,ngay,gia,maCCH) VALUES" +
            "(1,1,'2020-05-23',10000,'admin')," +
            "(2,1,'2020-06-23',30000,'admin')," +
            "(3,1,'2020-07-23',50000,'admin')," +
            "(4,1,'2020-09-23',60000,'admin')," +
            "(5,1,'2020-12-23',90000,'admin')";
}
