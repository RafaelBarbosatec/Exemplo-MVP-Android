package com.rafael.mpvexemplo.util_conection;

import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by rafael on 08/09/15.
 */
public interface ResponseConnection {

    Map<String,String> doBefore();

    void doAfter(JSONObject jsonObject);

    void erroServer(VolleyError error);
}
