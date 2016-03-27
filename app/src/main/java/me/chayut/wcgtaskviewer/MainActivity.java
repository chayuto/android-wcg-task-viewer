package me.chayut.wcgtaskviewer;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    // Instantiate the RequestQueue.
    RequestQueue queue ;

    //UI
    private ListView listview;
    private Spinner spinnerResultState;

    private String username,code;

    ArrayList<WCGResult> list = new ArrayList<WCGResult>();
    resultAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Intent intent = getIntent();
        username = intent.getStringExtra(getString(R.string.username_key));
        code = intent.getStringExtra(getString(R.string.verifyCode_key));

        //UI setup
        listview = (ListView) findViewById(R.id.listView);
        spinnerResultState = (Spinner) findViewById(R.id.spinner);

        //setup array
        mAdapter = new resultAdapter(this,R.layout.row_result, list);

        //query queue
        queue = Volley.newRequestQueue(this);
    }

    public void onButtonPressed(View view){
        getResultsList();
    }

    private void ListUpdate(){

        listview.setAdapter(mAdapter);
    }

    public void getResultsList (){

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

        } else {

            Toast.makeText(getApplicationContext(), "No network connection available.",
                    Toast.LENGTH_SHORT).show();
            Log.d(TAG,"No network connection available.");
            return;
        }


        String url = String.format("https://secure.worldcommunitygrid.org/api/members/%s/" +
                "results?code=%s&limit=20&SortBy=ReceivedTime&ServerState=5",username,code);
        //&ValidateState=0

        int position = spinnerResultState.getSelectedItemPosition();


        switch (position){
            case 0: break;
            case 1: url = url + "&ValidateState=1"; //valid
                break;
            case 2: url = url + "&ValidateState=0"; //pending
                break;
            case 3: url = url + "&ValidateState=2"; //invalid
                break;
        }

        Log.d(TAG,url);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        if(response.has("ResultsStatus"))
                            try {
                                JSONObject ResultStatusJSON = response.getJSONObject("ResultsStatus");
                                JSONArray ResultJSONA = ResultStatusJSON.getJSONArray("Results");

                                int resultAvailable = ResultStatusJSON.getInt("ResultsAvailable");
                                int resultReturned = ResultStatusJSON.getInt("ResultsReturned");

                                Toast.makeText(getApplicationContext(),
                                        String.format("Available: %d, Displayed: %d", resultAvailable,resultReturned),Toast.LENGTH_SHORT).show();

                                list.clear();
                                for(int n = 0; n < ResultJSONA.length(); n++) {

                                    JSONObject resultObject = ResultJSONA.getJSONObject(n);
                                    Log.d(TAG,resultObject.toString());
                                    WCGResult mResult = new WCGResult(resultObject);

                                    list.add(mResult);

                                    Log.d(TAG,"Result: " + resultObject.toString());
                                }

                                ListUpdate(); //update UI

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                    }
                });




        // Add the request to the RequestQueue.
        queue.add(jsObjRequest);
    }


}
