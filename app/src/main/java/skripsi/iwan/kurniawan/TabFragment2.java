package skripsi.iwan.kurniawan;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TabFragment2 extends Fragment {

    private TextView GetEmail, GetJabatan, GetMulaikerja;
    private FirebaseAuth auth;
    private FirebaseDatabase getDatabase;
    private DatabaseReference getRefenence;
    private String GetUserID;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup c, Bundle s) {
        View rootView = i.inflate(R.layout.activity_tab_fragment2, c, false);
        GetEmail = rootView.findViewById(R.id.email);
        GetJabatan = rootView.findViewById(R.id.jabatan);
        GetMulaikerja = rootView.findViewById(R.id.mulaikerja);
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

                GetEmail.setText(""+userProfil.getEmail());
                GetJabatan.setText(""+userProfil.getJabatan());
                GetMulaikerja.setText(""+userProfil.getMulaikerja());
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return rootView;
    }
}
