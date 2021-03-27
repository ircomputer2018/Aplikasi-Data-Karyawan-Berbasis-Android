package skripsi.iwan.kurniawan;

import android.content.Intent;
import android.os.Bundle;;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DirektoriFragment extends Fragment {


    Button button;
    Intent intent;

    @Override
    public View onCreateView(LayoutInflater i, ViewGroup c, Bundle s) {
        View view = i.inflate(R.layout.fragment_direktori, c, false);


        button = (Button) view.findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), BukaArsipPDF.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
