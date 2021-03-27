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

public class Alamat extends AppCompatActivity {

    private FirebaseAuth auth;
    private String GetUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alamat);
        getSupportActionBar().setTitle("Alamat");
    }

    public void Click1(View view) {
//instansiasi edittext
        final EditText text1 = (EditText) findViewById(R.id.alamat);
        final EditText text2 = (EditText) findViewById(R.id.kota);
        final EditText text3 = (EditText) findViewById(R.id.provinsi);
        final EditText text4 = (EditText) findViewById(R.id.kodepos);

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        GetUserID = user.getUid();
//instansiasi database firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
//Referensi database yang dituju
        DatabaseReference myRef = database.getReference("datakaryawan").child(GetUserID);
//memberi nilai pada referensi yang dituju
        myRef.child("alamat").setValue(text1.getText().toString());
        myRef.child("kota").setValue(text2.getText().toString());
        myRef.child("provinsi").setValue(text3.getText().toString());
        myRef.child("kodepos").setValue(text4.getText().toString());

        if (text1.getText().toString().length() == 0) {
            //jika form Email belum di isi / masih kosong
            text1.setError("Harap isi alamat anda!");

        } else {
            //jika form sudah terisi semua
            Toast.makeText(getApplicationContext(), "Data berhasil disimpan!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
