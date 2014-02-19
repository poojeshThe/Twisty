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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.fortune.restful.Customer;
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

public class SalesReportsActivity
  extends Activity
{
  private final CompoundButton.OnCheckedChangeListener checkboxChangeListener = new CompoundButton.OnCheckedChangeListener()
  {
    public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
    {
      SalesReportsActivity.this.setConsolidateRept(Boolean.valueOf(paramAnonymousBoolean));
    }
  };
  private String consolidateRept = "C";
  private ArrayAdapter<Customer> customerArrayAdapter;
  private AutoCompleteTextView customerAutoCompleteView;
  private final AdapterView.OnItemClickListener customerItemClickListener = new AdapterView.OnItemClickListener()
  {
    public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
    {
      SalesReportsActivity.this.selectedCustomer = ((Customer)paramAnonymousAdapterView.getItemAtPosition(paramAnonymousInt));
    }
  };
  private ArrayList<Customer> customerList;
  private Date fromDate = new Date();
  private final View.OnClickListener genReportBtnListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      try
      {
        SalesReportsActivity.this.salesReptActivity.generateSalesReport();
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
        SalesReportsActivity.this.lotTypeSelected = 0;
        break;
      case 2131165186: 
        SalesReportsActivity.this.lotTypeSelected = 1;
        break;
      case 2131165187: 
        SalesReportsActivity.this.lotTypeSelected = 2;
        break;
      case 2131165188: 
        SalesReportsActivity.this.lotTypeSelected = 3;
      }
    }
  };
  private int lotTypeSelected = 0;
  private Spinner lottSpinner;
  private AdapterView.OnItemSelectedListener lottSpinnerItemSelListener = new AdapterView.OnItemSelectedListener()
  {
    public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
    {
      SalesReportsActivity.this.selectedLott = ((Lottery)SalesReportsActivity.this.lottSpinner.getSelectedItem());
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
        if ((SalesReportsActivity.this.lottSpinner != null) && (SalesReportsActivity.this.selectedCustomer != null))
        {
          if (SalesReportsActivity.this.selectedLott == null) {
            SalesReportsActivity.this.getLotts();
          }
          SalesReportsActivity.this.lottSpinner.performClick();
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
      } else if (SalesReportsActivity.this.lottSpinner != null) {
        if (SalesReportsActivity.this.selectedLott != null) {
          SalesReportsActivity.this.lottSpinner.performClick();
        } else {
          SalesReportsActivity.this.getLotts();
        }
      }
      return i;
    }
  };
  private ArrayAdapter<Lottery> lotteryArrayAdapter;
  private ArrayList<Lottery> lotteryList = new ArrayList();
  private int preferredDb;
  private ViewFlipper reportViewFlipper;
  private SalesReportsActivity salesReptActivity;
  private ArrayList<String> salesReptList = new ArrayList();
  private ArrayAdapter<String> salesReptListAdapter;
  private ListView salesReptListView;
  private final BroadcastReceiver salesReptReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      Bundle localBundle = paramAnonymousIntent.getExtras();
      Object localObject1 = localBundle.getString("com.fortune.restful.response_message");
      int i = localBundle.getInt("com.fortune.restful.resp_type");
      if (12 == i) {}
      for (;;)
      {
        int m;
        Object[] arrayOfObject;
        JSONObject localJSONObject;
        int j;
        Double localDouble;
        try
        {
          localObject1 = new JSONObject((String)localObject1);
          boolean bool = ((JSONObject)localObject1).getBoolean("consolidated");
          Object localObject2 = ((JSONObject)localObject1).getJSONArray("sales-report");
          new JSONObject();
          int k = 0;
          localObject1 = Double.valueOf(0.0D);
          if (localObject2 != null)
          {
            int n = ((JSONArray)localObject2).length();
            m = 0;
            if (m >= n)
            {
              ((TextView)SalesReportsActivity.this.findViewById(2131165215)).setText(Integer.toString(k));
              localObject2 = (TextView)SalesReportsActivity.this.findViewById(2131165218);
              arrayOfObject = new Object[1];
              arrayOfObject[0] = localObject1;
              ((TextView)localObject2).setText(String.format("%.2f", arrayOfObject));
              SalesReportsActivity.this.salesReptListAdapter.notifyDataSetChanged();
              SalesReportsActivity.this.salesReptListView.scrollTo(0, 0);
            }
          }
          else
          {
            SalesReportsActivity.this.reportViewFlipper.setDisplayedChild(1);
            break label576;
          }
          localJSONObject = ((JSONArray)localObject2).getJSONObject(m);
          j = Integer.parseInt(localJSONObject.getString("count"));
          localDouble = Double.valueOf(Double.parseDouble(localJSONObject.getString("amount")));
          k += j;
          localObject1 = Double.valueOf(((Double)localObject1).doubleValue() + localDouble.doubleValue());
          if (arrayOfObject != 0) {
            SalesReportsActivity.this.salesReptList.add(localJSONObject.getString("sales-id") + " - " + localJSONObject.getString("date") + " - " + localJSONObject.getString("user") + " Count: " + j + " Amt: " + localDouble);
          } else if (localJSONObject.getString("sales-id").equals("")) {
            SalesReportsActivity.this.salesReptList.add("   - S No: " + localJSONObject.getString("serial-no") + " Cnt: " + j + " Amt: " + localDouble);
          }
        }
        catch (JSONException localJSONException1)
        {
          localJSONException1.printStackTrace();
          break label576;
        }
        SalesReportsActivity.this.salesReptList.add(localJSONObject.getString("sales-id") + " - " + localJSONObject.getString("date") + " - " + localJSONObject.getString("user") + " - " + localJSONObject.getString("ticket-name"));
        SalesReportsActivity.this.salesReptList.add("   - S No: " + localJSONObject.getString("serial-no") + " Cnt: " + j + " Amt: " + localDouble);
        break label577;
        if (16 == arrayOfObject) {
          try
          {
            SalesReportsActivity.this.handleGetLottsResp(localJSONException1);
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
        label576:
        return;
        label577:
        m++;
      }
    }
  };
  private Customer selectedCustomer = null;
  private Lottery selectedLott = null;
  private ProgressDialog serverCommProgressDialog = null;
  private final ServiceConnection serviceConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      SalesReportsActivity.this.webService = ((WebService.WebBinder)paramAnonymousIBinder).getService();
    }
    
    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      SalesReportsActivity.this.webService = null;
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
    registerReceiver(this.salesReptReceiver, (IntentFilter)localObject);
  }
  
  private void dismissProgressDialog()
  {
    if (this.serverCommProgressDialog != null)
    {
      this.serverCommProgressDialog.dismiss();
      this.serverCommProgressDialog = null;
    }
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
    if (this.selectedCustomer == null)
    {
      localIntent.putExtra("customer-id", 0);
      localIntent.putExtra("customer-type", 0);
    }
    else
    {
      localIntent.putExtra("customer-id", this.selectedCustomer.getUserLoginID());
      localIntent.putExtra("customer-type", this.selectedCustomer.getLoginType());
    }
    localIntent.putExtra("lott-type", this.lotTypeSelected);
    WakefulIntentService.sendWakefulWork(getApplicationContext(), localIntent);
  }
  
  private void setupCustomerDropDown()
  {
    this.customerAutoCompleteView = ((AutoCompleteTextView)findViewById(2131165189));
    this.customerArrayAdapter = new ArrayAdapter(this, 17367050, this.customerList);
    this.customerArrayAdapter.setNotifyOnChange(true);
    this.customerAutoCompleteView.setThreshold(0);
    this.customerAutoCompleteView.setAdapter(this.customerArrayAdapter);
    this.customerAutoCompleteView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ((AutoCompleteTextView)paramAnonymousView).showDropDown();
      }
    });
    this.customerAutoCompleteView.setOnItemClickListener(this.customerItemClickListener);
  }
  
  private void showProgressDialog(String paramString)
  {
    if (this.serverCommProgressDialog == null) {
      this.serverCommProgressDialog = ProgressDialog.show(this, "Sales", paramString, true, true);
    }
  }
  
  public void generateSalesReport()
    throws JSONException
  {
    Intent localIntent = new Intent(this, WebService.class);
    localIntent.putExtra("fromApplication", true);
    localIntent.putExtra("com.fortune.restful.req_type", 12);
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
    localIntent.putExtra("lott-group", this.lotTypeSelected);
    localIntent.putExtra("consolidate", this.consolidateRept);
    localIntent.putExtra("display", "A");
    if (this.selectedCustomer != null)
    {
      localIntent.putExtra("customer", this.selectedCustomer.getCustName());
      localIntent.putExtra("own-rep", "N");
    }
    else
    {
      localIntent.putExtra("customer", "%");
      localIntent.putExtra("own-rep", "Y");
    }
    if (this.selectedLott != null) {
      localIntent.putExtra("lottery", this.selectedLott.getLottName());
    } else {
      localIntent.putExtra("lottery", "%");
    }
    WakefulIntentService.sendWakefulWork(getApplicationContext(), localIntent);
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
        localObject = new Lottery(((JSONObject)localObject).getString("ticket-name"), ((JSONObject)localObject).getInt("master-id"), "0", ((JSONObject)localObject).getString("group-code"), ((JSONObject)localObject).getInt("ticket-length"));
        this.lotteryArrayAdapter.add(localObject);
      }
      this.lotteryArrayAdapter.notifyDataSetChanged();
      this.lottSpinner.performClick();
    }
    dismissProgressDialog();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903050);
    bindService();
    this.salesReptActivity = this;
    Intent localIntent = getIntent();
    this.userID = localIntent.getIntExtra("user-id", 0);
    this.userType = localIntent.getIntExtra("user-type", 0);
    this.preferredDb = localIntent.getIntExtra("preferred-db", 0);
    this.reportViewFlipper = ((ViewFlipper)findViewById(2131165263));
    ((RadioGroup)findViewById(2131165185)).setOnCheckedChangeListener(this.lotTypeSelectListener);
    this.salesReptListAdapter = new ArrayAdapter(this, 2130903053, this.salesReptList);
    this.salesReptListView = ((ListView)findViewById(2131165267));
    this.salesReptListView.setAdapter(this.salesReptListAdapter);
    this.customerList = localIntent.getParcelableArrayListExtra("customer-list");
    ((Button)findViewById(2131165266)).setOnClickListener(this.genReportBtnListener);
    ((CheckBox)findViewById(2131165265)).setOnCheckedChangeListener(this.checkboxChangeListener);
    ((CheckBox)findViewById(2131165265)).performClick();
    this.lotteryArrayAdapter = new ArrayAdapter(this, 17367049, this.lotteryList);
    this.lotteryArrayAdapter.setNotifyOnChange(true);
    this.lottSpinner = ((Spinner)findViewById(2131165192));
    this.lottSpinner.setAdapter(this.lotteryArrayAdapter);
    this.lottSpinner.setOnItemSelectedListener(this.lottSpinnerItemSelListener);
    this.lottSpinner.setOnTouchListener(this.lottSpinnerTouchListener);
    this.lottSpinner.setOnKeyListener(this.lottSpinnerKeyListener);
    Calendar localCalendar = Calendar.getInstance();
    int i = localCalendar.get(1);
    int j = localCalendar.get(2);
    int k = localCalendar.get(5);
    ((DatePicker)findViewById(2131165237)).init(i, j, k, null);
    ((DatePicker)findViewById(2131165239)).init(i, j, k, null);
    setupCustomerDropDown();
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    unregisterReceiver(this.salesReptReceiver);
    unbindService(this.serviceConnection);
  }
  
  public void setConsolidateRept(Boolean paramBoolean)
  {
    if (!paramBoolean.booleanValue()) {
      this.consolidateRept = "A";
    } else {
      this.consolidateRept = "C";
    }
  }
  
  public void setFromDate(Date paramDate)
  {
    this.fromDate = paramDate;
  }
  
  public void setLotTypeSelected(int paramInt)
  {
    this.lotTypeSelected = paramInt;
  }
  
  public void setToDate(Date paramDate)
  {
    this.toDate = paramDate;
  }
}


/* Location:           D:\soft\ANDROID\APK HACK\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     com.fortune.restful.activities.SalesReportsActivity
 * JD-Core Version:    0.7.0.1
 */