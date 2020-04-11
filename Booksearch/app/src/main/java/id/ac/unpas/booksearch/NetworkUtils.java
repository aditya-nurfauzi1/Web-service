package id.ac.unpas.booksearch;

import android.net.Uri;
import android.util.Log;
import android.util.log;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.HttpUrlConnection;
import java.net.URL;

public class NetworkUtils {

   private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
   private static final String BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes?q=search+terms?";
   private static final String QUERY_PARAM = "q";
   private static final String MAX_RESULTS = "maxResults";
   private static final String PRINT_TYPE ="printType";

   static String getBookInfo(String queryString){
       HttpUrlConnection urlConnection = null;
       BufferedReader reader = null;
       String bookJSONString =null;

       try{
           Url builtUri = Uri.parse(BOOK_BASE_URL).buidUpon().appendQueryParameter(QUERY_PARAM, querySring).appendQueryParameter(MAX_RESULTS, "10").appendQueryParameter(PRINT_TYPE, "books").build();

           URL requestURL = new URL(builtUri.toString());
           urlConnection = (HttpURLConnection) requestURL.openConnection();
           urlConnection.connect();

           InputStream inputStream = urlConnection.getInputStream();
           StringBuffer buffer = new StringBuffer();
           if(inputStream == null){
               return null;
           }
           reader = new BufferedReader(new InputStreamReader(inputStream));
           String line;
           While ((line = reader.readLine()) != null){
               buffer.append(line + "\n");
           }
           if (buffer.length() == 0){
               return null;
           }
           bookJSONString = buffer.toString();
       } catch (Exception ex){
           ex.printStackTrace();;
           return null;
       } finally {
           if(urlConnection !=null){
               urlConnection.disconnect();
           }
           if (reader != null){
               try {
                   reader.close();
               }catch (IOException e){
                   e.printStackTrace();
               }
           }
           Log.i(LOG_TAG, bookJSONString);
           return  bookJSONString;
       }
   }
}
