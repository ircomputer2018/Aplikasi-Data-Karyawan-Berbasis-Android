package skripsi.iwan.kurniawan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Direktori extends AppCompatActivity {

    Button button, button1;
    Intent intent;

    private List<UserJabatan> datajabatan = new ArrayList<>();
    jabatan adapter;
    RecyclerView listuserRec;
    private DatabaseReference mDatabase;
    String userId ,JabatanBaru,GpBaru;

    private ProgressBar progressBar;
    private Dialog dialog;

    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direktori);
        getSupportActionBar().setTitle("Data Pekerjaan");
        listuserRec = findViewById(R.id.listuserRec);
        adapter = new jabatan(this, datajabatan);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        listuserRec.setLayoutManager(mLayoutManager);
        listuserRec.setItemAnimator(new DefaultItemAnimator());
        listuserRec.setAdapter(adapter);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        button = (Button)findViewById(R.id.btntambah);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), EditPekerjaan.class);
                startActivity(intent);
            }
        });



        progressBar.setVisibility(View.VISIBLE);
        MyRecyclerView();
        adapter.setOnItemClickListener(new jabatan.ClickListener() {
            @Override
            public void onItemClick(final int position, View v) {
                // custom dialog
                dialog = new Dialog(Direktori.this);
                dialog.setContentView(R.layout.edit_dialog_jabatan);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                final EditText text1 =  dialog.findViewById(R.id.text1);
                text1.setText(datajabatan.get(position).getJabatan());
                final EditText text2 =  dialog.findViewById(R.id.text2);
                text2.setText(datajabatan.get(position).getGp());

                Button updateButton = dialog.findViewById(R.id.ubahdata);
                updateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userId  =  datajabatan.get(position).getUid();
                        JabatanBaru = text1.getText().toString();
                        GpBaru = text2.getText().toString();
                        Map<String, Object> updates = new HashMap<String,Object>();
                        updates.put("jabatan", JabatanBaru);
                        updates.put("gp", GpBaru);
                        mDatabase.child(userId).updateChildren(updates);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
            public void onItemLongClick(int position, View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Direktori.this
                );
                builder.setTitle("Menghapus Data");
                builder.setMessage("Apakah anda yakin?");
                userId =  datajabatan.get(position).getUid();
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

        mDatabase = FirebaseDatabase.getInstance().getReference("datajabatan");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                datajabatan.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    UserJabatan user = postSnapshot.getValue(UserJabatan.class);
                    user.setUid(postSnapshot.getKey());
                    datajabatan.add(user);

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

        layoutManager =new LinearLayoutManager(this);
        listuserRec .setLayoutManager(layoutManager);
        listuserRec.setHasFixedSize(true);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.line));
        listuserRec.addItemDecoration(itemDecoration);
    }
}


