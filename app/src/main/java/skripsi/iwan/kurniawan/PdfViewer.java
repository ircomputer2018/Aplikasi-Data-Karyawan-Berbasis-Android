package skripsi.iwan.kurniawan;


import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PdfViewer extends AppCompatActivity {
    private static final int TIME_DELAY = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);
        ListFragment listFragment = new ListFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, listFragment);
        fragmentTransaction.commit();

        }
    }
