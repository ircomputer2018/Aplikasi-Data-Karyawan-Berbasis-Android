package skripsi.iwan.kurniawan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TabFragment1 extends Fragment {

    private TextView GetNip, GetNama, GetJeniskelamin, GetTanggallahir, GetAgama, GetStatus,
             GetTelp, GetAlamat, GetKota, GetProvinsi, GetKodepos;

    private FirebaseAuth auth;
    private FirebaseDatabase getDatabase;
    private DatabaseReference getRefenence;
    private String GetUserID;
    private ProgressBar progressBar;
    Button button;
    Intent intent;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup c, Bundle s) {
        View rootView = i.inflate(R.layout.activity_tab_fragment1, c, false);

        GetNip = rootView.findViewById(R.id.nip);
        GetNama = rootView.findViewById(R.id.nama);
        GetJeniskelamin = rootView.findViewById(R.id.jk);
        GetTanggallahir = rootView.findViewById(R.id.ttl);
        GetAgama = rootView.findViewById(R.id.agama);
        GetStatus = rootView.findViewById(R.id.status);
        GetTelp = rootView.findViewById(R.id.telp);
        GetAlamat = rootView.findViewById(R.id.alamat);
        GetKota = rootView.findViewById(R.id.kota);
        GetProvinsi = rootView.findViewById(R.id.provinsi);
        GetKodepos = rootView.findViewById(R.id.kodepos);
        progressBar = (ProgressBar)rootView. findViewById(R.id.progressBar);




        progressBar.setVisibility(View.VISIBLE);
        auth = FirebaseAuth.getInstance();

        //Mendapatkan User ID dari akun yang terautentikasi
        FirebaseUser user = auth.getCurrentUser();
        GetUserID = user.getUid();
        getDatabase = FirebaseDatabase.getInstance();
        getRefenence = getDatabase.getReference();
        /*
         * Mendapatkan referensi dari lokasi Admin dan tutunannya yaitu User ID dari masing-masing
         * akun user dan menambahkan ChildListener untuk menangani kejadian saat data ditambahkan,
         * diubah, dihapus dan dialihkan.
         */
        getRefenence.child("datakaryawan").child(GetUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfil userProfil = dataSnapshot.getValue(UserProfil.class);

                GetNip.setText(""+userProfil.getNip());
                GetNama.setText(""+userProfil.getNama());
                GetJeniskelamin.setText(""+userProfil.getJeniskelamin());
                GetTanggallahir.setText(""+userProfil.getTanggallahir());
                GetAgama.setText(""+userProfil.getAgama());
                GetStatus.setText(""+userProfil.getStatus());
                GetKota.setText(""+userProfil.getKota());
                GetProvinsi.setText(""+userProfil.getProvinsi());
                GetKodepos.setText(""+userProfil.getKodepos());
                GetTelp.setText(""+userProfil.getTelp());
                GetAlamat.setText(""+userProfil.getAlamat());
                progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return rootView;
    }
}
