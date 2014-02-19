package com.fortune.restful.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.fortune.restful.Customer;
import java.util.ArrayList;

public class SetCredLimitActivity
  extends Activity
{
  private ArrayList<Customer> customerList;
  private int preferredDb;
  private int userID;
  private int userType;
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903051);
    Intent localIntent = getIntent();
    this.userID = localIntent.getIntExtra("user-id", 0);
    this.userType = localIntent.getIntExtra("user-type", 0);
    this.preferredDb = localIntent.getIntExtra("preferred-db", 0);
    this.customerList = localIntent.getParcelableArrayListExtra("customer-list");
  }
}


/* Location:           D:\soft\ANDROID\APK HACK\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     com.fortune.restful.activities.SetCredLimitActivity
 * JD-Core Version:    0.7.0.1
 */