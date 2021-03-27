package skripsi.iwan.kurniawan;

public class UserJabatan {

    public String uid, jabatan, gp;
    public UserJabatan() {
    }
    public UserJabatan(String uid, String  jabatan, String gp) {


        this.uid= uid;
        this.jabatan = jabatan;
        this.gp = gp;

    }

    public String getGp() {
        return gp;
    }
    public void setGp(String gp)
    {
        this.gp = gp;
    }
    public String getJabatan()
    {
        return jabatan;
    }
    public void setJabatan(String jabatan)
    {
        this.jabatan = jabatan;
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




