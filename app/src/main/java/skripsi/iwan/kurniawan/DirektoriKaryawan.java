package skripsi.iwan.kurniawan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DirektoriKaryawan extends AppCompatActivity {

    Button button;
    Intent intent;

    private List<UserKaryawan> datakaryawan = new ArrayList<>();
    karyawan adapter;
    RecyclerView listuserRec;
    private DatabaseReference mDatabase;
    String userId ,NamaBaru,EmailBaru;
    private RecyclerView.LayoutManager layoutManager;
    private Dialog dialog;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direktori_karyawan);

        listuserRec = findViewById(R.id.listuserRec);
        adapter = new karyawan(this.getApplicationContext(), datakaryawan);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        listuserRec.setLayoutManager(mLayoutManager);
        listuserRec.setItemAnimator(new DefaultItemAnimator());
        listuserRec.setAdapter(adapter);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);


        progressBar.setVisibility(View.VISIBLE);

        MyRecyclerView();

        adapter.setOnItemClickListener(new karyawan.ClickListener() {
            @Override
            public void onItemClick(final int position, View v) {
                dialog = new Dialog(DirektoriKaryawan.this);
                dialog.setContentView(R.layout.edit_dialog_layout);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                final EditText nama =  dialog.findViewById(R.id.nama);
                nama.setText(datakaryawan.get(position).getNama());
                final EditText email =  dialog.findViewById(R.id.email);
                email.setText(datakaryawan.get(position).getEmail());
                Button updateButton = dialog.findViewById(R.id.ubahdata);
                updateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userId  =  datakaryawan.get(position).getUid();
                        NamaBaru = nama.getText().toString();
                        EmailBaru = email.getText().toString();
                        Map<String, Object> updates = new HashMap<String,Object>();
                        updates.put("nama", NamaBaru);
                        updates.put("email", EmailBaru);
                        mDatabase.child(userId).updateChildren(updates);
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
            @Override
            public void onItemLongClick(int position, View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DirektoriKaryawan.this);
                builder.setTitle("Menghapus Data");
                builder.setMessage("Apakah anda yakin?");
                userId =  datakaryawan.get(position).getUid();
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mDatabase.child(userId).removeValue();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference("datakaryawan");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                datakaryawan.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UserKaryawan user = postSnapshot.getValue(UserKaryawan.class);
                    user.setUid(postSnapshot.getKey());
                    datakaryawan.add(user);
                    progressBar.setVisibility(View.GONE);

                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void MyRecyclerView(){

        layoutManager =new LinearLayoutManager(getApplicationContext());
        listuserRec .setLayoutManager(layoutManager);
        listuserRec.setHasFixedSize(true);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.line));
        listuserRec.addItemDecoration(itemDecoration);
    }



}