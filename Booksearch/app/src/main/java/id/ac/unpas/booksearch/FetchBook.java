package id.ac.unpas.booksearch;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class FetchBook extends AsyncTask<String, Void, String>{
    private TextView mTitleText, mAuthorText;

    public FetchBook(TextView mTitleText, TextView mAuthorText){
        this.mAuthorText = mAuthorText;
        this.mTitleText = mTitleText;
    }

    @Override
    Protected String doInBacground(String... strings){
        return NetworkUtils.getBookInfo(strings[0])
    }

    @Override
    protected  void onPostExecute(String s){
        super.onPostExecute(s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemArray = jsonObject.getJSONArray("items");

                for (int i = 0; i<itemArray.length();i++){
                    JSONObject book =itemArray.getJSONObject(i);
                    String title=null;
                    String authors =null;
                    JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                    try {
                        title = volumeInfo.getString("tittle");
                        authors = volumeInfo.getString("authors")
                    }catch (Exception e){
                        e.printStackTrace();;
                    }

                    if (title != null && authors != null){
                        mTitleText.setText(title);
                        mAuthorText.setText(authors);
                        return;
                    }
                }
                mTitleText.setText("No Result Found");
                mAuthorText.setText("");
        }catch (Exception e){
            mTitleText.setText("No Result Found");
            mAuthorText.setText("");
            e.printStackTrace();
        }
    }
}
