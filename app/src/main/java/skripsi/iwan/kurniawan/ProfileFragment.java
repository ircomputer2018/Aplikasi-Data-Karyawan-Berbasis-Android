package skripsi.iwan.kurniawan;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {


    FirebaseAuth mAuth;
    Intent intent;
    ListView listview;
    String e[] = {
            "Edit Profil", "Alamat", "Telepon", "Pajak & Bpjs", "Logout"
    };




    @Override
    public View onCreateView(LayoutInflater i, ViewGroup c, Bundle s) {
        View rootView = i.inflate(R.layout.fragment_profile, c, false);
        listview = (ListView)rootView.findViewById(R.id.listview);
        mAuth=FirebaseAuth.getInstance();
        /*
         *  ArrayAdapter<T> = T Tergantung Dari Tipe Data Variabel,
         *  Jika String Maka Isi String, Jika Integer Maka Tulis Integer
         */
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1, e

        );

        // set data
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int p, long id) {


                if (id == 0) {
                    intent = new Intent(getActivity(), EditProfil.class);
                    startActivity(intent);
                } else if (id == 1) {
                    intent = new Intent(getActivity(), Alamat.class);
                    startActivity(intent);
                } else if (id == 2) {
                    intent = new Intent(getActivity(), Telepon.class);
                    startActivity(intent);
                } else if (id == 3) {
                    intent = new Intent(getActivity(), PajakBpjs.class);
                    startActivity(intent);
                } else if (id == 4) {




                    new AlertDialog.Builder(getActivity())

                            .setMessage("Apakah kamu yakin ingin keluar?")
                            .setPositiveButton("YA", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //Toast.makeText(getActivity(), "Kamu Memilih YES", Toast.LENGTH_LONG).show();




                                    dialog.cancel();
                                    getActivity();
                                    mAuth.signOut();
                                }
                            })
                            .setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getActivity(), "Kamu Memilih TIDAK Ingin Keluar", Toast.LENGTH_LONG).show();
                                    dialog.cancel();
                                }
                            }).show();
                }


            }
        });
        return rootView;
    }
}



