package skripsi.iwan.kurniawan;

public class UserProfil {

    public String nip, nama, jeniskelamin, tanggallahir, agama, status, kota, provinsi, kodepos;
    public String email, jabatan, mulaikerja, bpjssehat, bpjstk,
    telp, alamat, username, npwp;
    public UserProfil() {
    }
    public UserProfil(String nip, String nama, String jeniskelamin, String tanggallahir, String agama, String status,
                      String email, String jabatan, String mulaikerja, String bpjssehat, String bpjstk,
                      String telp, String alamat,String kota, String provinsi, String kodepos, String username, String npwp) {


        this.nip = nip;
        this.nama = nama;
        this.jeniskelamin = jeniskelamin;
        this.tanggallahir = tanggallahir;
        this.agama = agama;
        this.status = status;
        this.email = email;
        this.jabatan = jabatan;
        this.kota = kota;
        this.provinsi = provinsi;
        this.kodepos = kodepos;
        this.mulaikerja = mulaikerja;
        this.bpjssehat = bpjssehat;
        this.bpjstk = bpjstk;
        this.telp = telp;
        this.alamat = alamat;
        this.npwp =npwp;
        this.username = username;
    }

    public String getNip() {
        return nip;
    }
    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    String getJeniskelamin() {
        return jeniskelamin;
    }
    public void setJeniskelamin(String jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
    }

    String getTanggallahir() {
        return tanggallahir;
    }
    public void setTanggallahir(String tanggallahir) {
        this.tanggallahir = tanggallahir;
    }

    String getAgama() {
        return agama;
    }
    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getJabatan() {
        return jabatan;
    }
    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getMulaikerja() {
        return mulaikerja;
    }
    public void setMulaikerja(String mulaikerja) {
        this.mulaikerja = mulaikerja;
    }

    public String getBpjssehat() {
        return bpjssehat;
    }
    public void setBpjssehat(String bpjssehat) {
        this.bpjssehat = bpjssehat;
    }

    public String getBpjstk() {
        return bpjstk;
    }
    public void setBpjstk(String bpjstk) {
        this.bpjstk = bpjstk;
    }

    public String getTelp() {
        return telp;
    }
    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKota() {
        return kota;
    }
    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getProvinsi() {
        return provinsi;
    }
    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKodepos() {
        return kodepos;
    }
    public void setKodepos(String kodepos) {
        this.kodepos = kodepos;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getNpwp() {
        return npwp;
    }
    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

}




