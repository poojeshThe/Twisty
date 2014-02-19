package com.fortune.restful.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.ViewFlipper;
import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.fortune.restful.Lottery;
import com.fortune.restful.webservice.WebService;
import com.fortune.restful.webservice.WebService.WebBinder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ResultEntryReportsActivity
  extends Activity
{
  private Date fromDate = new Date();
  private final View.OnClickListener genReportBtnListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      try
      {
        ResultEntryReportsActivity.this.generateResultEntryReport();
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
  private final RadioGroup.OnCheckedChangeListener lotTypeSelectListener = new RadioGroup.OnCheckedChangeListener()
  {
    public void onCheckedChanged(RadioGroup paramAnonymousRadioGroup, int paramAnonymousInt)
    {
      switch (paramAnonymousInt)
      {
      default: 
        ResultEntryReportsActivity.this.lotTypeSelected = 0;
        break;
      case 2131165186: 
        ResultEntryReportsActivity.this.lotTypeSelected = 1;
        break;
      case 2131165187: 
        ResultEntryReportsActivity.this.lotTypeSelected = 2;
        break;
      case 2131165188: 
        ResultEntryReportsActivity.this.lotTypeSelected = 3;
      }
    }
  };
  private int lotTypeSelected = 3;
  private Spinner lottSpinner;
  private AdapterView.OnItemSelectedListener lottSpinnerItemSelListener = new AdapterView.OnItemSelectedListener()
  {
    public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
    {
      ResultEntryReportsActivity.this.selectedLott = ((Lottery)ResultEntryReportsActivity.this.lottSpinner.getSelectedItem());
    }
    
    public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
  };
  private View.OnKeyListener lottSpinnerKeyListener = new View.OnKeyListener()
  {
    public boolean onKey(View paramAnonymousView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
    {
      boolean bool;
      if (paramAnonymousInt != 23)
      {
        bool = false;
      }
      else
      {
        if (ResultEntryReportsActivity.this.lottSpinner != null)
        {
          if (ResultEntryReportsActivity.this.selectedLott == null) {
            ResultEntryReportsActivity.this.getLotts();
          }
          ResultEntryReportsActivity.this.lottSpinner.performClick();
        }
        bool = true;
      }
      return bool;
    }
  };
  private View.OnTouchListener lottSpinnerTouchListener = new View.OnTouchListener()
  {
    public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
    {
      int i = 1;
      if (paramAnonymousMotionEvent.getAction() != i) {
        i = 0;
      } else if (ResultEntryReportsActivity.this.lottSpinner != null) {
        if (ResultEntryReportsActivity.this.selectedLott != null) {
          ResultEntryReportsActivity.this.lottSpinner.performClick();
        } else {
          ResultEntryReportsActivity.this.getLotts();
        }
      }
      return i;
    }
  };
  private ArrayAdapter<Lottery> lotteryArrayAdapter;
  private ArrayList<Lottery> lotteryList = new ArrayList();
  private int preferredDb;
  private ViewFlipper reportViewFlipper;
  private ArrayList<String> resultEntryReptList = new ArrayList();
  private ArrayAdapter<String> resultEntryReptListAdapter;
  private ListView resultEntryReptListView;
  private final BroadcastReceiver resultEntryReptReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      Bundle localBundle = paramAnonymousIntent.getExtras();
      Object localObject = localBundle.getString("com.fortune.restful.response_message");
      int i = localBundle.getInt("com.fortune.restful.resp_type");
      if (15 == i)
      {
        ResultEntryReportsActivity.this.dismissProgressDialog();
        try
        {
          localObject = new JSONObject((String)localObject).getJSONArray("result-entry");
          new JSONObject();
          int k;
          if (localObject != null) {
            k = ((JSONArray)localObject).length();
          }
          JSONObject localJSONObject;
          for (int j = 0;; j++)
          {
            if (j >= k)
            {
              ResultEntryReportsActivity.this.resultEntryReptListAdapter.notifyDataSetChanged();
              ResultEntryReportsActivity.this.resultEntryReptListView.scrollTo(0, 0);
              ResultEntryReportsActivity.this.reportViewFlipper.setDisplayedChild(1);
              break;
            }
            localJSONObject = ((JSONArray)localObject).getJSONObject(j);
            if (!localJSONObject.getString("ticket-name").equals("")) {
              ResultEntryReportsActivity.this.resultEntryReptList.add(localJSONObject.getString("ticket-name") + " - " + localJSONObject.getString("group-code") + " - " + localJSONObject.getString("result-date") + " :");
            }
            ResultEntryReportsActivity.this.resultEntryReptList.add("    " + localJSONObject.getString("prize-position") + " : " + localJSONObject.getString("prize-amount") + " - " + localJSONObject.getString("winning-number"));
          }
          if (16 != localJSONObject) {
            return;
          }
        }
        catch (JSONException localJSONException1)
        {
          localJSONException1.printStackTrace();
        }
      }
      else
      {
        try
        {
          ResultEntryReportsActivity.this.handleGetLottsResp(localJSONException1);
        }
        catch (JSONException localJSONException2)
        {
          localJSONException2.printStackTrace();
        }
        catch (ParseException localParseException)
        {
          localParseException.printStackTrace();
        }
      }
    }
  };
  private Lottery selectedLott = null;
  private ProgressDialog serverCommProgressDialog = null;
  private final ServiceConnection serviceConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      ResultEntryReportsActivity.this.webService = ((WebService.WebBinder)paramAnonymousIBinder).getService();
    }
    
    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      ResultEntryReportsActivity.this.webService = null;
    }
  };
  private Date toDate = new Date();
  private int userID;
  private int userType;
  private WebService webService;
  
  private void bindService()
  {
    Object localObject = new Intent(this, WebService.class);
    ((Intent)localObject).putExtra("fromApplication", true);
    WakefulIntentService.sendWakefulWork(getApplicationContext(), (Intent)localObject);
    bindService(new Intent(this, WebService.class), this.serviceConnection, 1);
    localObject = new IntentFilter("com.fortune.restful.intent.filter");
    registerReceiver(this.resultEntryReptReceiver, (IntentFilter)localObject);
  }
  
  private void dismissProgressDialog()
  {
    if (this.serverCommProgressDialog != null)
    {
      this.serverCommProgressDialog.dismiss();
      this.serverCommProgressDialog = null;
    }
  }
  
  private void generateResultEntryReport()
    throws JSONException
  {
    Intent localIntent = new Intent(this, WebService.class);
    localIntent.putExtra("fromApplication", true);
    localIntent.putExtra("com.fortune.restful.req_type", 15);
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
    localIntent.putExtra("type", this.userType);
    localIntent.putExtra("preferred-db", this.preferredDb);
    localIntent.putExtra("lott-type", this.lotTypeSelected);
    if (this.selectedLott != null) {
      localIntent.putExtra("lottery-name", this.selectedLott.getLottName());
    } else {
      localIntent.putExtra("lottery-name", "%");
    }
    WakefulIntentService.sendWakefulWork(getApplicationContext(), localIntent);
    showProgressDialog("Fetching report from server");
  }
  
  private void getLotts()
  {
    showProgressDialog("Fetching lottery list");
    Intent localIntent = new Intent(this, WebService.class);
    localIntent.putExtra("fromApplication", true);
    localIntent.putExtra("com.fortune.restful.req_type", 16);
    localIntent.putExtra("user", this.userID);
    localIntent.putExtra("type", this.userType);
    localIntent.putExtra("preferred-db", this.preferredDb);
    localIntent.putExtra("lott-type", this.lotTypeSelected);
    WakefulIntentService.sendWakefulWork(getApplicationContext(), localIntent);
  }
  
  private void showProgressDialog(String paramString)
  {
    if (this.serverCommProgressDialog == null) {
      this.serverCommProgressDialog = ProgressDialog.show(this, "Reports", paramString, true, true);
    }
  }
  
  public void handleGetLottsResp(String paramString)
    throws JSONException, ParseException
  {
    JSONArray localJSONArray = new JSONArray(paramString);
    new JSONObject();
    this.lotteryArrayAdapter.clear();
    if (localJSONArray != null)
    {
      int j = localJSONArray.length();
      for (int i = 0; i < j; i++)
      {
        Object localObject = localJSONArray.getJSONObject(i);
        int k = ((JSONObject)localObject).getInt("ticket-length");
        if (k == this.lotTypeSelected)
        {
          localObject = new Lottery(((JSONObject)localObject).getString("ticket-name"), ((JSONObject)localObject).getInt("master-id"), "0", ((JSONObject)localObject).getString("group-code"), k);
          this.lotteryArrayAdapter.add(localObject);
        }
      }
      this.lotteryArrayAdapter.notifyDataSetChanged();
      this.lottSpinner.performClick();
    }
    dismissProgressDialog();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903046);
    bindService();
    Intent localIntent = getIntent();
    this.userID = localIntent.getIntExtra("user-id", 0);
    this.userType = localIntent.getIntExtra("user-type", 0);
    this.preferredDb = localIntent.getIntExtra("preferred-db", 0);
    this.reportViewFlipper = ((ViewFlipper)findViewById(2131165251));
    this.resultEntryReptListAdapter = new ArrayAdapter(this, 2130903053, this.resultEntryReptList);
    this.resultEntryReptListView = ((ListView)findViewById(2131165255));
    this.resultEntryReptListView.setAdapter(this.resultEntryReptListAdapter);
    ((Button)findViewById(2131165254)).setOnClickListener(this.genReportBtnListener);
    this.lotteryArrayAdapter = new ArrayAdapter(this, 17367049, this.lotteryList);
    this.lotteryArrayAdapter.setNotifyOnChange(true);
    this.lottSpinner = ((Spinner)findViewById(2131165192));
    this.lottSpinner.setAdapter(this.lotteryArrayAdapter);
    this.lottSpinner.setOnItemSelectedListener(this.lottSpinnerItemSelListener);
    this.lottSpinner.setOnTouchListener(this.lottSpinnerTouchListener);
    this.lottSpinner.setOnKeyListener(this.lottSpinnerKeyListener);
    ((RadioGroup)findViewById(2131165185)).setOnCheckedChangeListener(this.lotTypeSelectListener);
    Calendar localCalendar = Calendar.getInstance();
    int j = localCalendar.get(1);
    int i = localCalendar.get(2);
    int k = localCalendar.get(5);
    ((DatePicker)findViewById(2131165237)).init(j, i, k, null);
    ((DatePicker)findViewById(2131165239)).init(j, i, k, null);
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    unregisterReceiver(this.resultEntryReptReceiver);
    unbindService(this.serviceConnection);
  }
  
  public void setLotTypeSelected(int paramInt)
  {
    this.lotTypeSelected = paramInt;
  }
}


/* Location:           D:\soft\ANDROID\APK HACK\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     com.fortune.restful.activities.ResultEntryReportsActivity
 * JD-Core Version:    0.7.0.1
 */