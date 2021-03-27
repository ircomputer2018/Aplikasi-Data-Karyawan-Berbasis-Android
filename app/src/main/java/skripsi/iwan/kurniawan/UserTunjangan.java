package skripsi.iwan.kurniawan;

public class UserTunjangan {

    public String uid, jabatan, tkeluarga, tkesehatan, ttransportasi, tpendidikan;
    public UserTunjangan() {
    }
    public UserTunjangan(String uid, String  jabatan, String tkeluarga, String tkesehatan, String ttransportasi, String tpendidikan) {

        this.uid= uid;
        this.jabatan = jabatan;
        this.tkeluarga = tkeluarga;
        this.tkesehatan = tkesehatan;
        this.ttransportasi = ttransportasi;
        this.tpendidikan = tpendidikan;
    }
    public String getUid()
    {
        return uid;
    }
    public void setUid(String uid)
    {
        this.uid = uid;
    }
    public String getJabatan()
    {
        return jabatan;
    }
    public void setJabatan(String jabatan)
    {
        this.jabatan = jabatan;
    }
    public String getTkeluarga() {
        return tkeluarga;
    }
    public void setTkeluarga(String tkeluarga)
    {
        this.tkeluarga = tkeluarga;
    }
    public String getTkesehatan() {
        return tkesehatan;
    }
    public void setTkesehatan(String tkesehatan)
    {
        this.tkesehatan = tkesehatan;
    }
    public String getTtransportasi() {
        return ttransportasi;
    }
    public void setTtransportasi(String ttransportasi)
    {
        this.ttransportasi = ttransportasi;
    }
    public String getTpendidikan() {
        return tpendidikan;
    }
    public void setTpendidikan(String tpendidikan)
    {
        this.tpendidikan = tpendidikan;
    }
}




