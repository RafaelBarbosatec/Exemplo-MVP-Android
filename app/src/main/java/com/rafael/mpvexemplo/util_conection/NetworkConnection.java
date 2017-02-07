package com.rafael.mpvexemplo.util_conection;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Map;


/**
 * Created by rafael on 08/09/15.
 */
public class NetworkConnection {

    private static NetworkConnection instance;
    private Context mContext;
    private RequestQueue mRequestQueue;
    private Class classType;
    private Gson gson;

    public NetworkConnection(Context c){
        mContext = c;
        mRequestQueue = Volley.newRequestQueue(mContext);
        gson = new Gson();
    }

    public static NetworkConnection getInstance( Context c ,Class classType){
        if( instance == null ){
            instance = new NetworkConnection( c.getApplicationContext());
        }
        instance.setTypeClass(classType);
        return( instance );
    }

    private void setTypeClass(Class classType){
        this.classType = classType;
    }

    public void conectionVolley(final ResponseConnection response,String uri,int method){

        Map<String,String> params = response.doBefore();

        CustomJsonObjectRequest cjor = new CustomJsonObjectRequest(method,uri,params,
                new Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.i("StriptJson", "Sucess: " + jsonObject);
                        Object ob = gson.fromJson(jsonObject.toString(),classType);
                        response.doAfter(ob);
                    }
                },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                response.erroServer(volleyError);
                Log.e("StriptJson", "Erro: " + volleyError.getMessage());

            }
        });

        cjor.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        cjor.setTag("tag");
        mRequestQueue.add(cjor);

    }

}
