package skripsi.iwan.kurniawan;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {

    Button button, button1, button2, button3;
    Intent intent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);



        button = (Button) rootView.findViewById(R.id.profil);
        button1 = (Button) rootView.findViewById(R.id.karyawan);
        button2 = (Button) rootView.findViewById(R.id.pekerjaan);
        button3 = (Button) rootView.findViewById(R.id.tunjangan);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                intent = new Intent(getActivity(), ProfilSaya.class);
                startActivity(intent);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                intent = new Intent(getActivity(), DirektoriKaryawan.class);
                startActivity(intent);
            }

        });

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                intent = new Intent(getActivity(), Direktori.class);
                startActivity(intent);
            }
        });


        button3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                intent = new Intent(getActivity(), DataTunjangan.class);
                startActivity(intent);

            }




        });
        return rootView;
    }

}