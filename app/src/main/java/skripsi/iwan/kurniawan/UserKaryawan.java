package skripsi.iwan.kurniawan;

public class UserKaryawan {

    public String nama,jabatan, uid, email;
    public UserKaryawan() {
    }
    public UserKaryawan(String nama,String  jabatan, String uid, String email) {

        this.nama = nama;
        this.jabatan = jabatan;
        this.uid= uid;

    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama)
    {
        this.nama = nama;
    }
    public String getJabatan()
    {
        return jabatan;
    }
    public void setJabatan(String jabatan)
    {
        this.jabatan = jabatan;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getUid()
    {
        return uid;
    }
    public void setUid(String uid)
    {
        this.uid = uid;
    }
}




