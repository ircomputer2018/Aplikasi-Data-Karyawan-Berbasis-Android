package skripsi.iwan.kurniawan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DataPekerjaan extends AppCompatActivity {


    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pekerjaan);

        getSupportActionBar().setTitle("Data Pekerjaan");

        button=(Button)findViewById(R.id.btnbuat);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DataPekerjaan.this, EditPekerjaan.class));
            }
        });

    }

}
