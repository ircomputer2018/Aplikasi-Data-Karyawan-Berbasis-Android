package skripsi.iwan.kurniawan;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;
import java.util.List;

public class BukaArsipPDF extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final static int REQUEST_CODE = 42;
    public static final int PERMISSION_CODE = 42042;
    public static final String SAMPLE_FILE = "sample.pdf";
    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";
    private PDFView pdfView;
    private Button openPdf;
    private Uri uri;
    private Integer pageNumber = 0;
    private String pdfFileName;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buka_arsip_pdf);

        getSupportActionBar().setTitle("Buka File PDF");

        pdfView = (PDFView) findViewById(R.id.pdfView);
        openPdf = (Button) findViewById(R.id.openpdf);
        openPdf.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                Intent fileIntent = new Intent();
                fileIntent.setType("application/pdf")
                        .setAction(Intent.ACTION_GET_CONTENT);
                int permissionCheck = ContextCompat.checkSelfPermission(BukaArsipPDF.this, READ_EXTERNAL_STORAGE);
                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            BukaArsipPDF.this,
                            new String[]{READ_EXTERNAL_STORAGE},
                            PERMISSION_CODE
                    );
                    return;
                }
                startActivityForResult(Intent.createChooser(fileIntent, "SELECT PDF"), REQUEST_CODE);
            }

        });
    }

    void afterViews() {
        pdfView.setBackgroundColor(Color.LTGRAY);
        if (uri != null) {
            displayFromUri(uri);
        } else {
            displayFromAsset(SAMPLE_FILE);
        }
        setTitle(pdfFileName);
    }
    private void displayFromAsset(String assetFileName) {
        pdfFileName = assetFileName;
        pdfView.fromAsset(SAMPLE_FILE)
                .defaultPage(pageNumber)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10) // in dp
                .load();
    }

    private void displayFromUri(Uri uri) {

        pdfFileName = getFileName(uri);
        pdfView.fromUri(uri)
                .defaultPage(pageNumber)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(10) // in dp
                .load();
    }

    private String getFileName(Uri uri) {

        String result = null;

        if (uri.getScheme().equals("content")) {

            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result == null) {

            result = uri.getLastPathSegment();
        }
        return result;
    }

    @Override
    public void loadComplete(int nbPages) {

        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        Log.e(TAG, "title = " + meta.getTitle());
        Log.e(TAG, "author = " + meta.getAuthor());
        Log.e(TAG, "subject = " + meta.getSubject());
        Log.e(TAG, "keywords = " + meta.getKeywords());
        Log.e(TAG, "creator = " + meta.getCreator());
        Log.e(TAG, "producer = " + meta.getProducer());
        Log.e(TAG, "creationDate = " + meta.getCreationDate());
        Log.e(TAG, "modDate = " + meta.getModDate());
        printBookmarksTree(pdfView.getTableOfContents(), "-");
    }

    private void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));
            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            uri = data.getData();
            displayFromUri(uri);
        }
    }
}