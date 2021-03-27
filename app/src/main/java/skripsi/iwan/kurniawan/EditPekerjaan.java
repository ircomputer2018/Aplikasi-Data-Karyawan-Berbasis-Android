package skripsi.iwan.kurniawan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditPekerjaan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pekerjaan);
    }
    public void Click1(View view) {


//instansiasi edittext
        final EditText text1 = findViewById(R.id.edit1);
        final EditText text2 = findViewById(R.id.edit2);
        final EditText text3 = findViewById(R.id.edit3);
        final EditText text4 = findViewById(R.id.edit4);
        final EditText text5 = findViewById(R.id.edit5);
        final EditText text6 = findViewById(R.id.edit6);




//instansiasi database firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();

//Referensi database yang dituju
        DatabaseReference myRef = database.getReference("datajabatan").child(text1.getText().toString());

//memberi nilai pada referensi yang dituju

        myRef.child("jabatan").setValue(text1.getText().toString());
        myRef.child("gp").setValue(text2.getText().toString());
        myRef.child("tkeluarga").setValue(text3.getText().toString());
        myRef.child("tkesehatan").setValue(text4.getText().toString());
        myRef.child("ttransportasi").setValue(text5.getText().toString());
        myRef.child("tpendidikan").setValue(text6.getText().toString());


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
