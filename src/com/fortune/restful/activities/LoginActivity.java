package com.fortune.restful.activities;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.fortune.restful.CommonUtils;
import com.fortune.restful.webservice.WebService;
import com.fortune.restful.webservice.WebService.WebBinder;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class LoginActivity
  extends Activity
{
  public static final String PREFS_DATA = "data";
  public static final String PREFS_NAME = "com.fortune.restful.webservice";
  private final int DIALOG_DISPLAY_STRING_WITH_OK = 0;
  private LoginActivity activity;
  private String appVersionName = "";
  private final View.OnClickListener loginBtnListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      Object localObject2 = (EditText)LoginActivity.this.findViewById(2131165231);
      Object localObject1 = (EditText)LoginActivity.this.findViewById(2131165233);
      LoginActivity.this.userName = ((EditText)localObject2).getText().toString();
      String str = ((EditText)localObject1).getText().toString();
      if ((localObject2 == null) || (localObject1 == null)) {
        Toast.makeText(LoginActivity.this.activity, "Couldn't find the 'txt_username' or 'txt_password' EditView in main.xml", 0).show();
      }
      for (;;)
      {
        return;
        localObject1 = new Intent(LoginActivity.this, WebService.class);
        ((Intent)localObject1).putExtra("fromApplication", true);
        ((Intent)localObject1).putExtra("com.fortune.restful.req_type", 1);
        ((Intent)localObject1).putExtra("username", LoginActivity.this.userName);
        localObject2 = "";
        try
        {
          localObject2 = CommonUtils.getKeyFromPassword(str);
          localObject2 = localObject2;
        }
        catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
        {
          for (;;)
          {
            localNoSuchAlgorithmException.printStackTrace();
          }
        }
        catch (UnsupportedEncodingException localUnsupportedEncodingException)
        {
          for (;;)
          {
            localUnsupportedEncodingException.printStackTrace();
          }
        }
        ((Intent)localObject1).putExtra("authkey", (String)localObject2);
        ((Intent)localObject1).putExtra("device-id", LoginActivity.this.getDeviceID());
        ((Intent)localObject1).putExtra("app-version", LoginActivity.this.appVersionName);
        WakefulIntentService.sendWakefulWork(LoginActivity.this.getApplicationContext(), (Intent)localObject1);
        LoginActivity.this.showProgressDialog("Logging in");
      }
    }
  };
  private BroadcastReceiver myReceiver = new BroadcastReceiver()
  {
    /* Error */
    public void onReceive(android.content.Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      // Byte code:
      //   0: aload_2
      //   1: invokevirtual 29	android/content/Intent:getExtras	()Landroid/os/Bundle;
      //   4: astore 5
      //   6: aload 5
      //   8: ldc 31
      //   10: invokevirtual 37	android/os/Bundle:getInt	(Ljava/lang/String;)I
      //   13: istore 4
      //   15: aconst_null
      //   16: astore_3
      //   17: iconst_1
      //   18: iload 4
      //   20: if_icmpne +128 -> 148
      //   23: aload_0
      //   24: getfield 12	com/fortune/restful/activities/LoginActivity$2:this$0	Lcom/fortune/restful/activities/LoginActivity;
      //   27: invokestatic 40	com/fortune/restful/activities/LoginActivity:access$6	(Lcom/fortune/restful/activities/LoginActivity;)V
      //   30: aload 5
      //   32: ldc 42
      //   34: iconst_0
      //   35: invokevirtual 46	android/os/Bundle:getBoolean	(Ljava/lang/String;Z)Z
      //   38: istore 6
      //   40: iconst_0
      //   41: istore 4
      //   43: aload 5
      //   45: ldc 48
      //   47: invokevirtual 52	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   50: astore 5
      //   52: new 54	org/json/JSONObject
      //   55: dup
      //   56: aload 5
      //   58: invokespecial 57	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   61: astore 5
      //   63: iload 6
      //   65: ifne +84 -> 149
      //   68: aload_0
      //   69: getfield 12	com/fortune/restful/activities/LoginActivity$2:this$0	Lcom/fortune/restful/activities/LoginActivity;
      //   72: ldc 59
      //   74: invokestatic 63	com/fortune/restful/activities/LoginActivity:access$7	(Lcom/fortune/restful/activities/LoginActivity;Ljava/lang/String;)V
      //   77: aload 5
      //   79: astore_3
      //   80: iload 4
      //   82: ifeq +66 -> 148
      //   85: new 25	android/content/Intent
      //   88: dup
      //   89: aload_0
      //   90: getfield 12	com/fortune/restful/activities/LoginActivity$2:this$0	Lcom/fortune/restful/activities/LoginActivity;
      //   93: ldc 65
      //   95: invokespecial 68	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
      //   98: astore 4
      //   100: aload 4
      //   102: ldc 70
      //   104: aload_3
      //   105: ldc 72
      //   107: invokevirtual 76	org/json/JSONObject:getJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   110: invokevirtual 80	org/json/JSONObject:toString	()Ljava/lang/String;
      //   113: invokevirtual 84	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
      //   116: pop
      //   117: aload 4
      //   119: ldc 86
      //   121: aload_0
      //   122: getfield 12	com/fortune/restful/activities/LoginActivity$2:this$0	Lcom/fortune/restful/activities/LoginActivity;
      //   125: invokestatic 90	com/fortune/restful/activities/LoginActivity:access$2	(Lcom/fortune/restful/activities/LoginActivity;)Ljava/lang/String;
      //   128: invokevirtual 84	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
      //   131: pop
      //   132: aload_0
      //   133: getfield 12	com/fortune/restful/activities/LoginActivity$2:this$0	Lcom/fortune/restful/activities/LoginActivity;
      //   136: aload 4
      //   138: invokevirtual 94	com/fortune/restful/activities/LoginActivity:startActivity	(Landroid/content/Intent;)V
      //   141: aload_0
      //   142: getfield 12	com/fortune/restful/activities/LoginActivity$2:this$0	Lcom/fortune/restful/activities/LoginActivity;
      //   145: invokevirtual 97	com/fortune/restful/activities/LoginActivity:finish	()V
      //   148: return
      //   149: aload 5
      //   151: ldc 99
      //   153: invokevirtual 102	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
      //   156: ifeq +18 -> 174
      //   159: aload_0
      //   160: getfield 12	com/fortune/restful/activities/LoginActivity$2:this$0	Lcom/fortune/restful/activities/LoginActivity;
      //   163: ldc 104
      //   165: invokestatic 63	com/fortune/restful/activities/LoginActivity:access$7	(Lcom/fortune/restful/activities/LoginActivity;Ljava/lang/String;)V
      //   168: aload 5
      //   170: astore_3
      //   171: goto -91 -> 80
      //   174: aload 5
      //   176: ldc 106
      //   178: invokevirtual 102	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
      //   181: ifne +18 -> 199
      //   184: aload_0
      //   185: getfield 12	com/fortune/restful/activities/LoginActivity$2:this$0	Lcom/fortune/restful/activities/LoginActivity;
      //   188: ldc 108
      //   190: invokestatic 63	com/fortune/restful/activities/LoginActivity:access$7	(Lcom/fortune/restful/activities/LoginActivity;Ljava/lang/String;)V
      //   193: aload 5
      //   195: astore_3
      //   196: goto -116 -> 80
      //   199: aload 5
      //   201: ldc 110
      //   203: invokevirtual 102	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
      //   206: ifne +18 -> 224
      //   209: aload_0
      //   210: getfield 12	com/fortune/restful/activities/LoginActivity$2:this$0	Lcom/fortune/restful/activities/LoginActivity;
      //   213: ldc 112
      //   215: invokestatic 63	com/fortune/restful/activities/LoginActivity:access$7	(Lcom/fortune/restful/activities/LoginActivity;Ljava/lang/String;)V
      //   218: aload 5
      //   220: astore_3
      //   221: goto -141 -> 80
      //   224: aload_0
      //   225: getfield 12	com/fortune/restful/activities/LoginActivity$2:this$0	Lcom/fortune/restful/activities/LoginActivity;
      //   228: invokestatic 115	com/fortune/restful/activities/LoginActivity:access$3	(Lcom/fortune/restful/activities/LoginActivity;)Ljava/lang/String;
      //   231: aload 5
      //   233: ldc 117
      //   235: invokevirtual 118	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   238: invokestatic 124	com/fortune/restful/CommonUtils:checkDevActivation	(Ljava/lang/String;Ljava/lang/String;)Z
      //   241: ifne +18 -> 259
      //   244: aload_0
      //   245: getfield 12	com/fortune/restful/activities/LoginActivity$2:this$0	Lcom/fortune/restful/activities/LoginActivity;
      //   248: ldc 126
      //   250: invokestatic 63	com/fortune/restful/activities/LoginActivity:access$7	(Lcom/fortune/restful/activities/LoginActivity;Ljava/lang/String;)V
      //   253: aload 5
      //   255: astore_3
      //   256: goto -176 -> 80
      //   259: iconst_1
      //   260: istore 4
      //   262: aload 5
      //   264: astore_3
      //   265: goto -185 -> 80
      //   268: astore 6
      //   270: aload_0
      //   271: getfield 12	com/fortune/restful/activities/LoginActivity$2:this$0	Lcom/fortune/restful/activities/LoginActivity;
      //   274: ldc 128
      //   276: invokestatic 63	com/fortune/restful/activities/LoginActivity:access$7	(Lcom/fortune/restful/activities/LoginActivity;Ljava/lang/String;)V
      //   279: aload 6
      //   281: invokevirtual 131	org/json/JSONException:printStackTrace	()V
      //   284: goto -204 -> 80
      //   287: astore 6
      //   289: aload_0
      //   290: getfield 12	com/fortune/restful/activities/LoginActivity$2:this$0	Lcom/fortune/restful/activities/LoginActivity;
      //   293: ldc 133
      //   295: invokestatic 63	com/fortune/restful/activities/LoginActivity:access$7	(Lcom/fortune/restful/activities/LoginActivity;Ljava/lang/String;)V
      //   298: aload 6
      //   300: invokevirtual 134	java/io/UnsupportedEncodingException:printStackTrace	()V
      //   303: goto -223 -> 80
      //   306: astore 6
      //   308: aload_0
      //   309: getfield 12	com/fortune/restful/activities/LoginActivity$2:this$0	Lcom/fortune/restful/activities/LoginActivity;
      //   312: ldc 133
      //   314: invokestatic 63	com/fortune/restful/activities/LoginActivity:access$7	(Lcom/fortune/restful/activities/LoginActivity;Ljava/lang/String;)V
      //   317: aload 6
      //   319: invokevirtual 135	java/security/NoSuchAlgorithmException:printStackTrace	()V
      //   322: goto -242 -> 80
      //   325: astore_3
      //   326: aload_0
      //   327: getfield 12	com/fortune/restful/activities/LoginActivity$2:this$0	Lcom/fortune/restful/activities/LoginActivity;
      //   330: ldc 128
      //   332: invokestatic 63	com/fortune/restful/activities/LoginActivity:access$7	(Lcom/fortune/restful/activities/LoginActivity;Ljava/lang/String;)V
      //   335: aload_3
      //   336: invokevirtual 131	org/json/JSONException:printStackTrace	()V
      //   339: goto -191 -> 148
      //   342: astore 6
      //   344: aload 5
      //   346: astore_3
      //   347: goto -39 -> 308
      //   350: astore 6
      //   352: aload 5
      //   354: astore_3
      //   355: goto -66 -> 289
      //   358: astore 6
      //   360: aload 5
      //   362: astore_3
      //   363: goto -93 -> 270
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	366	0	this	2
      //   0	366	1	paramAnonymousContext	android.content.Context
      //   0	366	2	paramAnonymousIntent	Intent
      //   16	249	3	localObject1	Object
      //   325	11	3	localJSONException1	org.json.JSONException
      //   346	17	3	localObject2	Object
      //   13	68	4	i	int
      //   98	39	4	localIntent	Intent
      //   260	1	4	j	int
      //   4	357	5	localObject3	Object
      //   38	26	6	bool	boolean
      //   268	12	6	localJSONException2	org.json.JSONException
      //   287	12	6	localUnsupportedEncodingException1	UnsupportedEncodingException
      //   306	12	6	localNoSuchAlgorithmException1	NoSuchAlgorithmException
      //   342	1	6	localNoSuchAlgorithmException2	NoSuchAlgorithmException
      //   350	1	6	localUnsupportedEncodingException2	UnsupportedEncodingException
      //   358	1	6	localJSONException3	org.json.JSONException
      // Exception table:
      //   from	to	target	type
      //   52	63	268	org/json/JSONException
      //   52	63	287	java/io/UnsupportedEncodingException
      //   52	63	306	java/security/NoSuchAlgorithmException
      //   100	148	325	org/json/JSONException
      //   68	77	342	java/security/NoSuchAlgorithmException
      //   149	253	342	java/security/NoSuchAlgorithmException
      //   68	77	350	java/io/UnsupportedEncodingException
      //   149	253	350	java/io/UnsupportedEncodingException
      //   68	77	358	org/json/JSONException
      //   149	253	358	org/json/JSONException
    }
  };
  private ProgressDialog serverCommProgressDialog = null;
  private ServiceConnection serviceConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      LoginActivity.this.webService = ((WebService.WebBinder)paramAnonymousIBinder).getService();
    }
    
    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      LoginActivity.this.webService = null;
    }
  };
  private String userName = "";
  private WebService webService;
  
  private void bindWebService()
  {
    IntentFilter localIntentFilter = new IntentFilter("com.fortune.restful.intent.filter");
    registerReceiver(this.myReceiver, localIntentFilter);
    bindService(new Intent(this, WebService.class), this.serviceConnection, 1);
  }
  
  private void dismissProgressDialog()
  {
    if (this.serverCommProgressDialog != null)
    {
      this.serverCommProgressDialog.dismiss();
      this.serverCommProgressDialog = null;
    }
  }
  
  private String getDeviceID()
  {
    return ((TelephonyManager)getSystemService("phone")).getDeviceId().toString();
  }
  
  private void showDialogWithString(String paramString)
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("dialog-message", paramString);
    this.activity.showDialog(0, localBundle);
  }
  
  private void showProgressDialog(String paramString)
  {
    if (this.serverCommProgressDialog == null) {
      this.serverCommProgressDialog = ProgressDialog.show(this, "Login", paramString, true, true);
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    this.activity = this;
    super.onCreate(paramBundle);
    setContentView(2130903042);
    bindWebService();
    ((Button)findViewById(2131165234)).setOnClickListener(this.loginBtnListener);
    TextView localTextView = (TextView)findViewById(2131165229);
    try
    {
      this.appVersionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
      localTextView.setText("Wify v" + this.appVersionName);
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        localNameNotFoundException.printStackTrace();
      }
    }
  }
  
  protected Dialog onCreateDialog(int paramInt, Bundle paramBundle)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.activity);
    switch (paramInt)
    {
    default: 
      localBuilder = null;
      break;
    case 0: 
      localBuilder.setMessage(paramBundle.getString("dialog-message")).setPositiveButton("OK", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.cancel();
        }
      });
    }
    return localBuilder.create();
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    unregisterReceiver(this.myReceiver);
    unbindService(this.serviceConnection);
  }
  
  protected void onResume()
  {
    super.onResume();
  }
}


/* Location:           D:\soft\ANDROID\APK HACK\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     com.fortune.restful.activities.LoginActivity
 * JD-Core Version:    0.7.0.1
 */