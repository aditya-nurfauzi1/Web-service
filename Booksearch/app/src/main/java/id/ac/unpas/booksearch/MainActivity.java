package id.ac.unpas.booksearch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String = MainActivity.class.getSimpleName();
    private EditText mBookInput;
    private TextView mAuthor, mTitleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBookInput = (EditText) findViewById(R.id.bookInput);
        mAuthorText= (EditText) findViewById(R.id.authorText);
        mTitleText= (EditText) findViewById(R.id.titleText);
    }

    public void searchBooks(View view){
        String queryString = mBookInput.getText().toString();

        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow((getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS));

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected() && queryString.length() != 0){
            new FetchBook(mTitleText, mAuthor).execute(queryString);
            mAuthor.setText("");
            mTitleText.setText("Loading...");
        }else{
            if (queryString.length() == 0) {
                mAuthor.setText("");
                mTitleText.setText("Please Input Title Book");
            }else{
                mAuthor.setText("");
                mTitleText.setText("Please check Your Connection");
            }
        }

        public void helpBooks(View view){
            Toast.makeText(getApplicationContext(), "1. Aplikasi ini dapat digunakan " +
                    "untuk mencari buku." + "\n2. Pencarian hanya mengeluarkan 1 hasil, " +
                    "pastikan mencari judul dengan tepat.", Toast.LENGTH_LONG).show();
        }
    }
}
