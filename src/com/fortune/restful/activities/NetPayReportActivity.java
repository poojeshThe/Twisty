package com.fortune.restful.activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.ViewFlipper;
import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.fortune.restful.webservice.WebService;
import com.fortune.restful.webservice.WebService.WebBinder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NetPayReportActivity
  extends Activity
{
  private Date fromDate = new Date();
  private final View.OnClickListener genReportBtnListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      try
      {
        NetPayReportActivity.this.netPayReptActivity.generatePaymentReport();
        return;
      }
      catch (JSONException localJSONException)
      {
        for (;;)
        {
          localJSONException.printStackTrace();
        }
      }
    }
  };
  private NetPayReportActivity netPayReptActivity;
  private ArrayList<String> netPayReptList = new ArrayList();
  private ArrayAdapter<String> netPayReptListAdapter;
  private ListView netPayReptListView;
  private final BroadcastReceiver paymentReptReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      Object localObject1 = paramAnonymousIntent.getExtras();
      String str = ((Bundle)localObject1).getString("com.fortune.restful.response_message");
      if (18 == ((Bundle)localObject1).getInt("com.fortune.restful.resp_type")) {
        try
        {
          localObject1 = new JSONObject(str);
          JSONArray localJSONArray = ((JSONObject)localObject1).getJSONArray("netpay-report");
          new JSONObject();
          int k = 0;
          int j;
          if (localJSONArray != null) {
            j = localJSONArray.length();
          }
          for (int i = 0;; i++)
          {
            if (i >= j)
            {
              NetPayReportActivity.this.netPayReptList.add("--------------------------");
              NetPayReportActivity.this.netPayReptList.add("Balance: " + k);
              NetPayReportActivity.this.netPayReptListAdapter.notifyDataSetChanged();
              NetPayReportActivity.this.netPayReptListView.scrollTo(0, 0);
              NetPayReportActivity.this.reportViewFlipper.setDisplayedChild(1);
              return;
            }
            JSONObject localJSONObject = localJSONArray.getJSONObject(i);
            try
            {
              m = localJSONObject.getInt("opening-balance");
              i1 = m;
            }
            catch (JSONException localJSONException2)
            {
              for (;;)
              {
                int m;
                int n;
                Object localObject2;
                int i1 = 0;
              }
            }
            n = localJSONObject.getInt("cr");
            m = localJSONObject.getInt("dr");
            localObject2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(localJSONObject.getString("sales-date").substring(0, 21));
            localObject2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.S").format((Date)localObject2).substring(0, 10);
            NetPayReportActivity.this.netPayReptList.add("Opening Bal: " + i1 + ", Date: " + (String)localObject2 + ", Cr: " + n + ", Dr: " + m + ", Remark: " + localJSONObject.get("remark"));
            k += m + (i1 + n);
          }
          return;
        }
        catch (JSONException localJSONException1)
        {
          localJSONException1.printStackTrace();
        }
        catch (ParseException localParseException)
        {
          localParseException.printStackTrace();
        }
      }
    }
  };
  private int preferredDb;
  private ViewFlipper reportViewFlipper;
  private final ServiceConnection serviceConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      NetPayReportActivity.this.webService = ((WebService.WebBinder)paramAnonymousIBinder).getService();
    }
    
    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      NetPayReportActivity.this.webService = null;
    }
  };
  private boolean showNetpay = false;
  private Date toDate = new Date();
  private int userID;
  private String userName = "";
  private int userType;
  private WebService webService;
  
  private void bindService()
  {
    Object localObject = new Intent(this, WebService.class);
    ((Intent)localObject).putExtra("fromApplication", true);
    WakefulIntentService.sendWakefulWork(getApplicationContext(), (Intent)localObject);
    bindService(new Intent(this, WebService.class), this.serviceConnection, 1);
    localObject = new IntentFilter("com.fortune.restful.intent.filter");
    registerReceiver(this.paymentReptReceiver, (IntentFilter)localObject);
  }
  
  public void generatePaymentReport()
    throws JSONException
  {
    Intent localIntent = new Intent(this, WebService.class);
    localIntent.putExtra("fromApplication", true);
    localIntent.putExtra("com.fortune.restful.req_type", 18);
    DatePicker localDatePicker = (DatePicker)findViewById(2131165239);
    this.toDate.setYear(-1900 + localDatePicker.getYear());
    this.toDate.setDate(localDatePicker.getDayOfMonth());
    this.toDate.setMonth(localDatePicker.getMonth());
    localDatePicker = (DatePicker)findViewById(2131165237);
    this.fromDate.setYear(-1900 + localDatePicker.getYear());
    this.fromDate.setDate(localDatePicker.getDayOfMonth());
    this.fromDate.setMonth(localDatePicker.getMonth());
    localIntent.putExtra("from-date", this.fromDate.getTime());
    localIntent.putExtra("to-date", this.toDate.getTime());
    localIntent.putExtra("user", this.userID);
    localIntent.putExtra("username", this.userName);
    localIntent.putExtra("type", this.userType);
    localIntent.putExtra("preferred-db", this.preferredDb);
    WakefulIntentService.sendWakefulWork(getApplicationContext(), localIntent);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903043);
    bindService();
    this.netPayReptActivity = this;
    Intent localIntent = getIntent();
    this.userID = localIntent.getIntExtra("user-id", 0);
    this.userName = localIntent.getStringExtra("user-name");
    this.userType = localIntent.getIntExtra("user-type", 0);
    this.preferredDb = localIntent.getIntExtra("preferred-db", 0);
    this.showNetpay = localIntent.getBooleanExtra("show-net-pay", false);
    this.reportViewFlipper = ((ViewFlipper)findViewById(2131165235));
    this.netPayReptListAdapter = new ArrayAdapter(this, 2130903053, this.netPayReptList);
    this.netPayReptListView = ((ListView)findViewById(2131165241));
    this.netPayReptListView.setAdapter(this.netPayReptListAdapter);
    ((Button)findViewById(2131165240)).setOnClickListener(this.genReportBtnListener);
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    unregisterReceiver(this.paymentReptReceiver);
    unbindService(this.serviceConnection);
  }
  
  public void setFromDate(Date paramDate)
  {
    this.fromDate = paramDate;
  }
  
  public void setToDate(Date paramDate)
  {
    this.toDate = paramDate;
  }
}


/* Location:           D:\soft\ANDROID\APK HACK\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     com.fortune.restful.activities.NetPayReportActivity
 * JD-Core Version:    0.7.0.1
 */