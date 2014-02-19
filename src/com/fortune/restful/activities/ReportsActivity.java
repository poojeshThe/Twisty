package com.fortune.restful.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.fortune.restful.Customer;
import java.util.ArrayList;

public class ReportsActivity
  extends Activity
{
  private ArrayList<Customer> customerList;
  private final View.OnClickListener netPayReptBtnListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      Intent localIntent = new Intent(ReportsActivity.this, NetPayReportActivity.class);
      localIntent.putExtra("user-id", ReportsActivity.this.userID);
      localIntent.putExtra("user-name", ReportsActivity.this.userName);
      localIntent.putExtra("user-type", ReportsActivity.this.userType);
      localIntent.putExtra("preferred-db", ReportsActivity.this.preferredDb);
      localIntent.putExtra("show-net-pay", ReportsActivity.this.showNetpay);
      ReportsActivity.this.startActivity(localIntent);
    }
  };
  private final View.OnClickListener paymentGivenReptBtnListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      Intent localIntent = new Intent(ReportsActivity.this, PaymentGivenReportActivity.class);
      localIntent.putExtra("user-id", ReportsActivity.this.userID);
      localIntent.putExtra("user-type", ReportsActivity.this.userType);
      localIntent.putExtra("preferred-db", ReportsActivity.this.preferredDb);
      localIntent.putParcelableArrayListExtra("customer-list", ReportsActivity.this.customerList);
      localIntent.putExtra("show-net-pay", ReportsActivity.this.showNetpay);
      ReportsActivity.this.startActivity(localIntent);
    }
  };
  private int preferredDb;
  private final View.OnClickListener resultEntryReptBtnListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      Intent localIntent = new Intent(ReportsActivity.this, ResultEntryReportsActivity.class);
      localIntent.putExtra("user-id", ReportsActivity.this.userID);
      localIntent.putExtra("user-type", ReportsActivity.this.userType);
      localIntent.putExtra("preferred-db", ReportsActivity.this.preferredDb);
      ReportsActivity.this.startActivity(localIntent);
    }
  };
  private final View.OnClickListener salesReptBtnListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      Intent localIntent = new Intent(ReportsActivity.this, SalesReportsActivity.class);
      localIntent.putExtra("user-id", ReportsActivity.this.userID);
      localIntent.putExtra("user-type", ReportsActivity.this.userType);
      localIntent.putExtra("preferred-db", ReportsActivity.this.preferredDb);
      localIntent.putParcelableArrayListExtra("customer-list", ReportsActivity.this.customerList);
      ReportsActivity.this.startActivity(localIntent);
    }
  };
  private boolean showAgentWinning = false;
  private boolean showNetpay = false;
  private boolean showStockistWinning = false;
  private boolean showSubstockistWinning = false;
  private int userID;
  private String userName = "";
  private int userType;
  private final View.OnClickListener winningReptBtnListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      Intent localIntent = new Intent(ReportsActivity.this, WinningReportsActivity.class);
      localIntent.putExtra("user-id", ReportsActivity.this.userID);
      localIntent.putExtra("user-type", ReportsActivity.this.userType);
      localIntent.putExtra("preferred-db", ReportsActivity.this.preferredDb);
      localIntent.putExtra("show-agent-winning", ReportsActivity.this.showAgentWinning);
      localIntent.putExtra("show-stockist-winning", ReportsActivity.this.showStockistWinning);
      localIntent.putExtra("show-substockist-winning", ReportsActivity.this.showSubstockistWinning);
      localIntent.putExtra("show-net-pay", ReportsActivity.this.showNetpay);
      localIntent.putParcelableArrayListExtra("customer-list", ReportsActivity.this.customerList);
      ReportsActivity.this.startActivity(localIntent);
    }
  };
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903045);
    Intent localIntent = getIntent();
    this.userID = localIntent.getIntExtra("user-id", 0);
    this.userName = localIntent.getStringExtra("user-name");
    this.userType = localIntent.getIntExtra("user-type", 0);
    this.preferredDb = localIntent.getIntExtra("preferred-db", 0);
    this.showAgentWinning = localIntent.getBooleanExtra("show-agent-winning", false);
    this.showStockistWinning = localIntent.getBooleanExtra("show-stockist-winning", false);
    this.showSubstockistWinning = localIntent.getBooleanExtra("show-substockist-winning", false);
    this.showNetpay = localIntent.getBooleanExtra("show-net-pay", false);
    this.customerList = localIntent.getParcelableArrayListExtra("customer-list");
    ((Button)findViewById(2131165246)).setOnClickListener(this.salesReptBtnListener);
    ((Button)findViewById(2131165248)).setOnClickListener(this.winningReptBtnListener);
    ((Button)findViewById(2131165249)).setOnClickListener(this.resultEntryReptBtnListener);
    ((Button)findViewById(2131165250)).setOnClickListener(this.paymentGivenReptBtnListener);
    ((Button)findViewById(2131165247)).setOnClickListener(this.netPayReptBtnListener);
    if (this.userType != 2) {
      findViewById(2131165247).setVisibility(8);
    }
  }
}


/* Location:           D:\soft\ANDROID\APK HACK\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     com.fortune.restful.activities.ReportsActivity
 * JD-Core Version:    0.7.0.1
 */