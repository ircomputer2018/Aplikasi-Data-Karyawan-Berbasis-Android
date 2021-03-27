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

public class PajakBpjs extends AppCompatActivity {

    private FirebaseAuth auth;
    private String GetUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pajak_bpjs);
        getSupportActionBar().setTitle("Pajak dan Bpjs");
    }

    public void Click1(View view) {
//instansiasi edittext
        final EditText text1 = (EditText) findViewById(R.id.mulaikerja);
        final EditText text2 = (EditText) findViewById(R.id.npwp);
        final EditText text3 = (EditText) findViewById(R.id.bpjssehat);
        final EditText text4 = (EditText) findViewById(R.id.bpjstk);

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        GetUserID = user.getUid();
//instansiasi database firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
//Referensi database yang dituju
        DatabaseReference myRef = database.getReference("datakaryawan").child(GetUserID);
//memberi nilai pada referensi yang dituju

        myRef.child("mulaikerja").setValue(text1.getText().toString());
        myRef.child("npwp").setValue(text2.getText().toString());
        myRef.child("bpjssehat").setValue(text3.getText().toString());
        myRef.child("bpjstk").setValue(text4.getText().toString());
        if (text1.getText().toString().length() == 0) {
            //jika form Email belum di isi / masih kosong
            text1.setError("Harap isi npwpanda anda!");
        } else {
            //jika form sudah terisi semua
            Toast.makeText(getApplicationContext(), "Data berhasil disimpan!",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
