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

public class DataTunjangan extends AppCompatActivity {


    Button button;
    Intent intent;

    private List<UserTunjangan> datajabatan = new ArrayList<>();
    tunjangan adapter;
    RecyclerView listuserRec;
    private DatabaseReference mDatabase;
    String userId ,JabatanBaru,Tkeluarga, Tkesehatan;
    private ProgressBar progressBar;
    private Dialog dialog;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_tunjangan);
        getSupportActionBar().setTitle("Data tunjangan");

        listuserRec = findViewById(R.id.listuserRec);
        adapter = new tunjangan(this, datajabatan);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        listuserRec.setLayoutManager(mLayoutManager);
        listuserRec.setItemAnimator(new DefaultItemAnimator());
        listuserRec.setAdapter(adapter);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        progressBar.setVisibility(View.VISIBLE);

        MyRecyclerView();
        adapter.setOnItemClickListener(new tunjangan.ClickListener() {
            @Override
            public void onItemClick(final int position, View v) {
                // custom dialog
                dialog = new Dialog(DataTunjangan.this);
                dialog.setContentView(R.layout.edit_dialog_tunjangan);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                final EditText text1 =  dialog.findViewById(R.id.text1);
                text1.setText(datajabatan.get(position).getJabatan());
                final EditText text2 =  dialog.findViewById(R.id.text2);
                text2.setText(datajabatan.get(position).getTkeluarga());
                final EditText text3 =  dialog.findViewById(R.id.text3);
                text3.setText(datajabatan.get(position).getTkesehatan());

                Button updateButton = dialog.findViewById(R.id.ubahdata);
                updateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userId  =  datajabatan.get(position).getUid();
                        JabatanBaru = text1.getText().toString();
                        Tkeluarga = text2.getText().toString();
                        Tkesehatan = text3.getText().toString();
                        Map<String, Object> updates = new HashMap<String,Object>();
                        updates.put("jabatan", JabatanBaru);
                        updates.put("tkeluarga", Tkeluarga);
                        updates.put("tkesehatan", Tkesehatan);
                        mDatabase.child(userId).updateChildren(updates);
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
            public void onItemLongClick(int position, View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DataTunjangan.this);
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
                    UserTunjangan user = postSnapshot.getValue(UserTunjangan.class);
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


