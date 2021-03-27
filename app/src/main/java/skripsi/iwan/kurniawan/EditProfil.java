package skripsi.iwan.kurniawan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfil extends AppCompatActivity {

    private FirebaseAuth auth;
    private String GetUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        getSupportActionBar().setTitle("Profil");
    }
    public void Click1(View view) {
//instansiasi edittext
        final EditText text1 = (EditText) findViewById(R.id.nip);
        final EditText text2 = (EditText) findViewById(R.id.nama);
        final EditText text3 = (EditText) findViewById(R.id.jabatan);
        final EditText text4 = (EditText) findViewById(R.id.jeniskelamin);
        final EditText text5 = (EditText) findViewById(R.id.tanggallahir);
        final EditText text6 = (EditText) findViewById(R.id.agama);
        final EditText text7 = (EditText) findViewById(R.id.status);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        GetUserID = user.getUid();
//instansiasi database firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
//Referensi database yang dituju
        DatabaseReference myRef = database.getReference("datakaryawan").child(GetUserID);
//memberi nilai pada referensi yang dituju
        myRef.child("nip").setValue(text1.getText().toString());
        myRef.child("nama").setValue(text2.getText().toString());
        myRef.child("jabatan").setValue(text3.getText().toString());
        myRef.child("jeniskelamin").setValue(text4.getText().toString());
        myRef.child("tanggallahir").setValue(text5.getText().toString());
        myRef.child("agama").setValue(text6.getText().toString());
        myRef.child("status").setValue(text7.getText().toString());

        if (text1.getText().toString().length() == 0) {
            //jika form Email belum di isi / masih kosong
            text1.setError("Harap isi!");
        } else if (text2.getText().toString().length() == 0) {
            //jika form Username belum di isi / masih kosong
            text2.setError("Harap diisi!");

        } else {
            //jika form sudah terisi semua
            Toast.makeText(getApplicationContext(), "Data berhasil disimpan!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
