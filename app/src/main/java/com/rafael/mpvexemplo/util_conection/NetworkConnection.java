package com.rafael.mpvexemplo.util_conection;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by rafael on 08/09/15.
 */
public class NetworkConnection {

    private static NetworkConnection instance;
    private Context mContext;
    private RequestQueue mRequestQueue;

    public NetworkConnection(Context c){
        mContext = c;
        mRequestQueue = Volley.newRequestQueue(mContext);
    }

    public static NetworkConnection getInstance( Context c ){
        if( instance == null ){
            instance = new NetworkConnection( c.getApplicationContext() );
        }
        return( instance );
    }

    public void conectionVolley(final ResponseConnection response,String uri,int method){

        /*  Forma de passar parametros via POST

        params = new HashMap<String, String>();
        params.put("email", etEmail.getText().toString());
        params.put("pasword", etPassword.getText().toString());
        params.put("method", "web-data-jor");*/

        Map<String,String> params = response.doBefore();


        CustomJsonObjectRequest cjor = new CustomJsonObjectRequest(method,uri,params,
                new Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.i("StriptJson", "Sucess: " + jsonObject);
                        response.doAfter(jsonObject);
                    }
                },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                response.erroServer(volleyError);
                Log.i("StriptJson", "Erro: " + volleyError.getMessage());
                //Toast.makeText(mContext, "Erro: " + volleyError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        cjor.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        cjor.setTag("tag");
        mRequestQueue.add(cjor);

    }

}
