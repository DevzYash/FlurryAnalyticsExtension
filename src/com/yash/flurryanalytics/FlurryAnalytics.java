package com.yash.flurryanalytics;

import android.content.Context;
import android.util.Log;
import com.flurry.android.FlurryAgent;
import com.flurry.android.FlurryAgentListener;
import com.flurry.android.FlurryPerformance;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.util.YailDictionary;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class FlurryAnalytics extends AndroidNonvisibleComponent {

public Context context;

  public FlurryAnalytics(ComponentContainer container) {
    super(container.$form());
    context = container.$context();
    init();
  }

    @SimpleFunction(description = "")
    public void startEvent(String event){
      FlurryAgent.logEvent(event);
    }



    @SimpleFunction(description = "")
    public void StartEventDict(String eventname, YailDictionary yailDictionary,boolean istimeenabled){
        try {
            JSONObject jsonObject = new JSONObject(yailDictionary.toString());
            JSONArray names = jsonObject.names();
            HashMap hashMap = new HashMap();

            for (int i=0; i<jsonObject.length(); i++){
                String string = names.getString(i);
                Object object = jsonObject.get(string);
                hashMap.put(string,object.toString());
            }
            if (istimeenabled){
                FlurryAgent.logEvent(eventname,hashMap,istimeenabled);
            }
            else FlurryAgent.logEvent(eventname,hashMap);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }




  @SimpleEvent(description = "")
public void OnSessionStarted(String session){
    EventDispatcher.dispatchEvent(this,"OnSessionStarted",session);
  }

  @SimpleFunction(description = "")
  public boolean isSessionActive(){
    return FlurryAgent.isSessionActive();

  }

  @SimpleFunction(description = "")
  public String getSessionId(){
    return FlurryAgent.getSessionId();

  }

@SimpleFunction(description = "")
public void setUserId(String userid){
      FlurryAgent.setUserId(userid);
}

    public void init() {
          new FlurryAgent.Builder().withDataSaleOptOut(false) //CCPA - the default value is false
                  .withCaptureUncaughtExceptions(true)
                  .withIncludeBackgroundSessionsInMetrics(true)
                  .withLogLevel(Log.VERBOSE)
                  .withPerformanceMetrics(FlurryPerformance.ALL)
                  .withListener(new FlurryAgentListener() {
                      @Override
                      public void onSessionStarted() {
                          OnSessionStarted("true");
                      }
                  })
                  .build(context, "PSQPHTF6R39QZ4WRH666");

                      FlurryAgent.setReportLocation(true);
      }




}
