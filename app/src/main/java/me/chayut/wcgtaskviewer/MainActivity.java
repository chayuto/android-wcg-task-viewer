package me.chayut.wcgtaskviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

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

    private ListView listview;
    private Spinner spinnerResultState;

    ArrayList<WCGResult> list = new ArrayList<WCGResult>();

    resultAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

        String url ="https://secure.worldcommunitygrid.org/api/members/chayut_orapinpatipat/" +
                "results?code=6be4a4bbf9a57b39ffd296f29e899309&limit=20&ValidateState=0";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        if(response.has("ResultsStatus"))
                            try {
                                JSONObject ResultStatusJSON = response.getJSONObject("ResultsStatus");
                                JSONArray ResultJSONA = ResultStatusJSON.getJSONArray("Results");

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
