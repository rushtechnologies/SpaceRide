package application.android.com.rushtechnologies.spaceride.Service.Volley;

import android.content.Context;

import com.github.johnpersano.supertoasts.library.SuperActivityToast;

import org.json.JSONArray;

public class WebService {
    public Object[] objects;
    public boolean response;
    private final Request request;

    public WebService(Context context) {
        this.request = new Request(context);
    }

    public void myWebServiceFun(String url) {
        request.getResponse(url,
                new VolleyCallback() {
                    @Override
                    public void onSuccessResponse(JSONArray array) {
                        response = true;
                        try {
                            int length = array.length();
                            if (length != 0) {
                                System.out.println("Objects received:");
                                objects = new Object[length];
                                for (int i = 0; i < length; i++) {
                                    objects[i] = array.get(i);
                                    System.out.println(objects[i]);
                                }
                            } else {
                                objects = null;
                                System.out.println("Objects are empty");
                            }
                        } catch (Exception e) {
                            System.out.println("Error at myWebServiceFun: " + e.getMessage());
                        }
                    }
                });
    }

    public void waitForResponse(SuperActivityToast superActivityToast) {
        for (int iWait = 0; iWait < 30; ++iWait){
            try{
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                System.out.println("Interrupted exception:" + ie.getMessage());
                break;
            }
            if(response){
                superActivityToast.setTouchToDismiss(true);
                response = false;
                break;
            } else {
                superActivityToast.setProgress(iWait * 10);
            }
        }
    }

    public Object[] getObjects() {
        return objects;
    }

}
