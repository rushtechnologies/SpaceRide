package application.android.com.rushtechnologies.spaceride.Service.Volley;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class Request {
    public static Context context;
    RequestQueue request;
    JsonArrayRequest jsonArrayRequest;

    public Request(Context con) {
        this.context = con;
        request = Volley.newRequestQueue(context);
    }

    public void getResponse(String url, final VolleyCallback callback) {
        RequestQueue requestQueue = MySingleton.getInstance(context).getRequestQueue();
        jsonArrayRequest = new JsonArrayRequest(com.android.volley.Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray array) {
                System.out.println("Response received");
                callback.onSuccessResponse(array);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError e) {
                System.out.println("Error at request: " + e.getMessage() + "\n");
                e.printStackTrace();
                Toast.makeText(context, e + "error", Toast.LENGTH_LONG).show();
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
        requestQueue = MySingleton.getInstance(context).getRequestQueue();
        Cache cache = requestQueue.getCache();
    }

}
