package com.fortune.restful.webservice;

import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import com.commonsware.cwac.wakeful.WakefulIntentService;
import com.fortune.restful.CommonUtils;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebService
  extends WakefulIntentService
{
  public static final String EXTRAS_LOGIN_STATUS = "com.fortune.restful.login_status";
  public static final String EXTRAS_REQUEST_TYPE = "com.fortune.restful.req_type";
  public static final String EXTRAS_RESPONSE_MESSAGE = "com.fortune.restful.response_message";
  public static final String EXTRAS_RESPONSE_TYPE = "com.fortune.restful.resp_type";
  public static final String EXTRAS_SUCCESS = "com.fortune.restful.success";
  public static final String WEB_INTENT_FILTER = "com.fortune.restful.intent.filter";
  public static final int WS_MESG_BUY = 2;
  public static final int WS_MESG_DELETE_SALE = 3;
  public static final int WS_MESG_EDIT_SALE = 4;
  public static final int WS_MESG_GEN_NETPAY_REPORT = 18;
  public static final int WS_MESG_GEN_PAYMENT_REPORT = 14;
  public static final int WS_MESG_GEN_RESULT_ENTRY_REPORT = 15;
  public static final int WS_MESG_GEN_SALE_REPORT = 12;
  public static final int WS_MESG_GEN_WIN_REPORT = 13;
  public static final int WS_MESG_GET_APP_UPDATE = 17;
  public static final int WS_MESG_GET_CUSTOMERS = 5;
  public static final int WS_MESG_GET_CUST_CREDIT = 6;
  public static final int WS_MESG_GET_LOTTS = 7;
  public static final int WS_MESG_GET_LOTTS_GLOBAL = 16;
  public static final int WS_MESG_GET_LOTT_RATE = 8;
  public static final int WS_MESG_GET_SALES_EXPIRY = 9;
  public static final int WS_MESG_GET_SALE_INFO = 11;
  public static final int WS_MESG_GET_SERVER_TIME = 10;
  public static final int WS_MESG_LOGIN = 1;
  private String baseUrl = null;
  private final IBinder binder = new WebBinder();
  protected Context context;
  
  public WebService()
  {
    super("UpdateService");
  }
  
  private void broadCast(int paramInt, boolean paramBoolean, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("com.fortune.restful.success", paramBoolean);
    localIntent.putExtra("com.fortune.restful.response_message", paramString);
    localIntent.putExtra("com.fortune.restful.resp_type", paramInt);
    localIntent.setAction("com.fortune.restful.intent.filter");
    sendBroadcast(localIntent);
  }
  
  /* Error */
  private void wsBuy(Bundle paramBundle)
    throws JSONException, NoSuchAlgorithmException, UnsupportedEncodingException
  {
    // Byte code:
    //   0: new 192	java/lang/StringBuilder
    //   3: dup
    //   4: aload_0
    //   5: getfield 79	com/fortune/restful/webservice/WebService:baseUrl	Ljava/lang/String;
    //   8: invokestatic 198	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   11: invokespecial 199	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   14: ldc 201
    //   16: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: aload_1
    //   20: ldc 207
    //   22: invokevirtual 213	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   25: invokevirtual 216	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   28: ldc 218
    //   30: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: aload_1
    //   34: ldc 220
    //   36: invokevirtual 213	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   39: invokevirtual 216	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   42: ldc 218
    //   44: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: aload_1
    //   48: ldc 222
    //   50: invokevirtual 213	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   53: invokevirtual 216	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   56: ldc 224
    //   58: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   61: aload_1
    //   62: ldc 226
    //   64: invokevirtual 213	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   67: invokevirtual 216	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   70: ldc 218
    //   72: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: aload_1
    //   76: ldc 228
    //   78: invokevirtual 213	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   81: invokevirtual 216	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   84: ldc 230
    //   86: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   89: invokevirtual 234	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   92: astore 4
    //   94: aconst_null
    //   95: astore_2
    //   96: new 236	org/json/JSONObject
    //   99: dup
    //   100: invokespecial 237	org/json/JSONObject:<init>	()V
    //   103: astore_3
    //   104: aload_3
    //   105: ldc 239
    //   107: aload_1
    //   108: ldc 239
    //   110: invokevirtual 243	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   113: invokevirtual 247	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   116: pop
    //   117: aload_3
    //   118: ldc 249
    //   120: aload 4
    //   122: invokestatic 254	com/fortune/restful/CommonUtils:genKeyFromUrl	(Ljava/lang/String;)Ljava/lang/String;
    //   125: invokevirtual 247	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   128: pop
    //   129: new 256	com/fortune/restful/webservice/RestClient
    //   132: dup
    //   133: aload 4
    //   135: invokespecial 257	com/fortune/restful/webservice/RestClient:<init>	(Ljava/lang/String;)V
    //   138: astore 4
    //   140: aload 4
    //   142: aload_3
    //   143: invokevirtual 258	org/json/JSONObject:toString	()Ljava/lang/String;
    //   146: invokevirtual 261	com/fortune/restful/webservice/RestClient:setJSONString	(Ljava/lang/String;)V
    //   149: aload 4
    //   151: getstatic 267	com/fortune/restful/webservice/RequestMethod:POST	Lcom/fortune/restful/webservice/RequestMethod;
    //   154: invokevirtual 271	com/fortune/restful/webservice/RestClient:execute	(Lcom/fortune/restful/webservice/RequestMethod;)V
    //   157: aload 4
    //   159: invokevirtual 275	com/fortune/restful/webservice/RestClient:getResponseCode	()I
    //   162: sipush 200
    //   165: if_icmpne +62 -> 227
    //   168: aload_0
    //   169: aload_0
    //   170: invokevirtual 279	com/fortune/restful/webservice/WebService:getApplicationContext	()Landroid/content/Context;
    //   173: putfield 281	com/fortune/restful/webservice/WebService:context	Landroid/content/Context;
    //   176: new 236	org/json/JSONObject
    //   179: dup
    //   180: aload 4
    //   182: invokevirtual 284	com/fortune/restful/webservice/RestClient:getResponse	()Ljava/lang/String;
    //   185: invokespecial 285	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   188: astore_2
    //   189: aload_2
    //   190: ldc_w 287
    //   193: invokevirtual 291	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
    //   196: istore_3
    //   197: iload_3
    //   198: ifne +22 -> 220
    //   201: iconst_0
    //   202: istore_3
    //   203: aload_2
    //   204: astore_2
    //   205: iload_3
    //   206: ifeq +13 -> 219
    //   209: aload_0
    //   210: iconst_2
    //   211: iload_3
    //   212: aload_2
    //   213: invokevirtual 258	org/json/JSONObject:toString	()Ljava/lang/String;
    //   216: invokespecial 293	com/fortune/restful/webservice/WebService:broadCast	(IZLjava/lang/String;)V
    //   219: return
    //   220: iconst_1
    //   221: istore_3
    //   222: aload_2
    //   223: astore_2
    //   224: goto -19 -> 205
    //   227: iconst_0
    //   228: istore_3
    //   229: goto -24 -> 205
    //   232: astore_2
    //   233: aload_2
    //   234: invokevirtual 296	java/lang/Exception:printStackTrace	()V
    //   237: goto -18 -> 219
    //   240: astore_2
    //   241: goto -8 -> 233
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	244	0	this	WebService
    //   0	244	1	paramBundle	Bundle
    //   95	129	2	localJSONObject1	JSONObject
    //   232	2	2	localException1	Exception
    //   240	1	2	localException2	Exception
    //   103	40	3	localJSONObject2	JSONObject
    //   196	33	3	bool	boolean
    //   92	89	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   149	189	232	java/lang/Exception
    //   209	219	232	java/lang/Exception
    //   189	197	240	java/lang/Exception
  }
  
  private void wsDeleteSale(Bundle paramBundle)
    throws JSONException, NoSuchAlgorithmException, UnsupportedEncodingException
  {
    Object localObject = this.baseUrl + "/user/" + paramBundle.getInt("user") + "-" + paramBundle.getInt("type") + "-" + paramBundle.getInt("preferred-db") + "/customer/" + paramBundle.getInt("customer-id") + "-" + paramBundle.getInt("customer-type") + "/sale/" + paramBundle.getInt("sale-id") + "/D";
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("sale-jsonml", paramBundle.getString("sale-jsonml"));
    localJSONObject.put("sale-date", paramBundle.getString("sale-date"));
    localJSONObject.put("key", CommonUtils.genKeyFromUrl((String)localObject));
    localObject = new RestClient((String)localObject);
    ((RestClient)localObject).setJSONString(localJSONObject.toString());
    try
    {
      ((RestClient)localObject).execute(RequestMethod.PUT);
      boolean bool;
      if (((RestClient)localObject).getResponseCode() == 200)
      {
        this.context = getApplicationContext();
        localJSONObject = new JSONObject(((RestClient)localObject).getResponse());
        if (!localJSONObject.getBoolean("db-connected")) {
          bool = false;
        }
      }
      for (;;)
      {
        broadCast(3, bool, "Sales entry deleted");
        break;
        bool = bool.getString("sales-status").equals("success");
        if (bool)
        {
          bool = true;
        }
        else
        {
          bool = false;
          continue;
          bool = false;
        }
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void wsEditSale(Bundle paramBundle)
    throws JSONException, NoSuchAlgorithmException, UnsupportedEncodingException
  {
    Object localObject = this.baseUrl + "/user/" + paramBundle.getInt("user") + "-" + paramBundle.getInt("type") + "-" + paramBundle.getInt("preferred-db") + "/customer/" + paramBundle.getInt("customer-id") + "-" + paramBundle.getInt("customer-type") + "/sale/" + paramBundle.getInt("sale-id") + "/U";
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("sale-jsonml", paramBundle.getString("sale-jsonml"));
    localJSONObject.put("sale-date", paramBundle.getString("sale-date"));
    localJSONObject.put("key", CommonUtils.genKeyFromUrl((String)localObject));
    localObject = new RestClient((String)localObject);
    ((RestClient)localObject).setJSONString(localJSONObject.toString());
    try
    {
      ((RestClient)localObject).execute(RequestMethod.PUT);
      boolean bool;
      if (((RestClient)localObject).getResponseCode() == 200)
      {
        this.context = getApplicationContext();
        localJSONObject = new JSONObject(((RestClient)localObject).getResponse());
        if (!localJSONObject.getBoolean("db-connected")) {
          bool = false;
        }
      }
      for (;;)
      {
        broadCast(4, bool, "Sale edited");
        break;
        bool = bool.getString("sales-status").equals("success");
        if (bool)
        {
          bool = true;
        }
        else
        {
          bool = false;
          continue;
          bool = false;
        }
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void wsGenNetPayReport(Bundle paramBundle)
    throws JSONException, NoSuchAlgorithmException, UnsupportedEncodingException
  {
    JSONObject localJSONObject1 = null;
    JSONObject localJSONObject2 = new JSONObject();
    String str = this.baseUrl + "/user/" + paramBundle.getInt("user") + "-" + paramBundle.getInt("type") + "-" + paramBundle.getInt("preferred-db") + "/genreport/netpay";
    RestClient localRestClient = new RestClient(str);
    localJSONObject2.put("from-date", paramBundle.getLong("from-date"));
    localJSONObject2.put("to-date", paramBundle.getLong("to-date"));
    localJSONObject2.put("username", paramBundle.getString("username"));
    localJSONObject2.put("key", CommonUtils.genKeyFromUrl(str));
    localRestClient.setJSONString(localJSONObject2.toString());
    try
    {
      localRestClient.execute(RequestMethod.POST);
      boolean bool;
      if (localRestClient.getResponseCode() == 200)
      {
        localJSONObject1 = new JSONObject(localRestClient.getResponse());
        bool = true;
        localJSONObject1 = localJSONObject1;
      }
      for (;;)
      {
        if (bool) {
          broadCast(18, bool, localJSONObject1.toString());
        }
        return;
        bool = false;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  private void wsGenPaymentReport(Bundle paramBundle)
    throws JSONException, NoSuchAlgorithmException, UnsupportedEncodingException
  {
    JSONObject localJSONObject1 = null;
    JSONObject localJSONObject2 = new JSONObject();
    String str = this.baseUrl + "/user/" + paramBundle.getInt("user") + "-" + paramBundle.getInt("type") + "-" + paramBundle.getInt("preferred-db") + "/genreport/paymentgiven";
    RestClient localRestClient = new RestClient(str);
    localJSONObject2.put("from-date", paramBundle.getLong("from-date"));
    localJSONObject2.put("to-date", paramBundle.getLong("to-date"));
    localJSONObject2.put("customer", paramBundle.getInt("customer"));
    localJSONObject2.put("key", CommonUtils.genKeyFromUrl(str));
    localRestClient.setJSONString(localJSONObject2.toString());
    try
    {
      localRestClient.execute(RequestMethod.POST);
      boolean bool;
      if (localRestClient.getResponseCode() == 200)
      {
        localJSONObject1 = new JSONObject(localRestClient.getResponse());
        bool = true;
        localJSONObject1 = localJSONObject1;
      }
      for (;;)
      {
        if (bool) {
          broadCast(14, bool, localJSONObject1.toString());
        }
        return;
        bool = false;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  private void wsGenResultEntryReport(Bundle paramBundle)
    throws JSONException, NoSuchAlgorithmException, UnsupportedEncodingException
  {
    JSONObject localJSONObject1 = null;
    JSONObject localJSONObject2 = new JSONObject();
    String str = this.baseUrl + "/user/" + paramBundle.getInt("user") + "-" + paramBundle.getInt("type") + "-" + paramBundle.getInt("preferred-db") + "/genreport/resultentry";
    RestClient localRestClient = new RestClient(str);
    localJSONObject2.put("from-date", paramBundle.getLong("from-date"));
    localJSONObject2.put("to-date", paramBundle.getLong("to-date"));
    localJSONObject2.put("lottery-name", paramBundle.getString("lottery-name"));
    localJSONObject2.put("lott-type", paramBundle.getInt("lott-type"));
    localJSONObject2.put("key", CommonUtils.genKeyFromUrl(str));
    localRestClient.setJSONString(localJSONObject2.toString());
    try
    {
      localRestClient.execute(RequestMethod.POST);
      boolean bool;
      if (localRestClient.getResponseCode() == 200)
      {
        localJSONObject1 = new JSONObject(localRestClient.getResponse());
        bool = true;
        localJSONObject1 = localJSONObject1;
      }
      for (;;)
      {
        if (bool) {
          broadCast(15, bool, localJSONObject1.toString());
        }
        return;
        bool = false;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  private void wsGenSaleReport(Bundle paramBundle)
    throws JSONException, NoSuchAlgorithmException, UnsupportedEncodingException
  {
    JSONObject localJSONObject1 = null;
    JSONObject localJSONObject2 = new JSONObject();
    String str = this.baseUrl + "/user/" + paramBundle.getInt("user") + "-" + paramBundle.getInt("type") + "-" + paramBundle.getInt("preferred-db") + "/genreport/sales";
    RestClient localRestClient = new RestClient(str);
    localJSONObject2.put("from-date", paramBundle.getLong("from-date"));
    localJSONObject2.put("to-date", paramBundle.getLong("to-date"));
    localJSONObject2.put("lott-group", paramBundle.getInt("lott-group"));
    localJSONObject2.put("consolidate", paramBundle.get("consolidate"));
    localJSONObject2.put("own-rep", paramBundle.get("own-rep"));
    localJSONObject2.put("display", paramBundle.get("display"));
    localJSONObject2.put("customer", paramBundle.get("customer"));
    localJSONObject2.put("lottery", paramBundle.get("lottery"));
    localJSONObject2.put("key", CommonUtils.genKeyFromUrl(str));
    localRestClient.setJSONString(localJSONObject2.toString());
    try
    {
      localRestClient.execute(RequestMethod.POST);
      boolean bool;
      if (localRestClient.getResponseCode() == 200)
      {
        localJSONObject1 = new JSONObject(localRestClient.getResponse());
        bool = true;
        localJSONObject1 = localJSONObject1;
      }
      for (;;)
      {
        if (bool) {
          broadCast(12, bool, localJSONObject1.toString());
        }
        return;
        bool = false;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  private void wsGenWinReport(Bundle paramBundle)
    throws JSONException, NoSuchAlgorithmException, UnsupportedEncodingException
  {
    JSONObject localJSONObject1 = null;
    JSONObject localJSONObject2 = new JSONObject();
    String str = this.baseUrl + "/user/" + paramBundle.getInt("user") + "-" + paramBundle.getInt("type") + "-" + paramBundle.getInt("preferred-db") + "/genreport/winning";
    RestClient localRestClient = new RestClient(str);
    localJSONObject2.put("from-date", paramBundle.getLong("from-date"));
    localJSONObject2.put("to-date", paramBundle.getLong("to-date"));
    localJSONObject2.put("sales-id", paramBundle.getString("sales-id"));
    localJSONObject2.put("customer", paramBundle.getString("customer"));
    localJSONObject2.put("display", paramBundle.getString("display"));
    localJSONObject2.put("consol-by-customers", paramBundle.getBoolean("consol-by-customers"));
    localJSONObject2.put("consol-by-bill", paramBundle.getBoolean("consol-by-bill"));
    localJSONObject2.put("key", CommonUtils.genKeyFromUrl(str));
    localRestClient.setJSONString(localJSONObject2.toString());
    try
    {
      localRestClient.execute(RequestMethod.POST);
      boolean bool;
      if (localRestClient.getResponseCode() == 200)
      {
        localJSONObject1 = new JSONObject(localRestClient.getResponse());
        bool = true;
        localJSONObject1 = localJSONObject1;
      }
      for (;;)
      {
        if (bool) {
          broadCast(13, bool, localJSONObject1.toString());
        }
        return;
        bool = false;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  private void wsGetAppUpdate(Bundle paramBundle)
    throws JSONException
  {
    Object localObject = new RestClient(this.baseUrl + "/user/" + paramBundle.getInt("user") + "-" + paramBundle.getInt("type") + "-" + paramBundle.getInt("preferred-db") + "/appupdate");
    label123:
    for (;;)
    {
      try
      {
        ((RestClient)localObject).execute(RequestMethod.GET);
        if (((RestClient)localObject).getResponseCode() == 200)
        {
          this.context = getApplicationContext();
          localObject = new JSONObject(((RestClient)localObject).getResponse());
        }
        localException1.printStackTrace();
      }
      catch (Exception localException1)
      {
        try
        {
          broadCast(17, true, ((JSONObject)localObject).toString());
          return;
        }
        catch (Exception localException2)
        {
          break label123;
        }
        localException1 = localException1;
      }
    }
  }
  
  private void wsGetCustCredit(Bundle paramBundle)
  {
    Double localDouble = Double.valueOf(0.0D);
    RestClient localRestClient = new RestClient(this.baseUrl + "/user/" + paramBundle.getInt("user") + "-" + paramBundle.getInt("type") + "-" + paramBundle.getInt("preferred-db") + "/customer/" + paramBundle.getInt("customer-id") + "-" + paramBundle.getInt("customer-type") + "/credit");
    try
    {
      localRestClient.execute(RequestMethod.GET);
      if (localRestClient.getResponseCode() == 200)
      {
        this.context = getApplicationContext();
        localDouble = Double.valueOf(new JSONObject(localRestClient.getResponse()).getDouble("credit-limit"));
      }
      for (boolean bool = true;; bool = false)
      {
        if (bool) {
          broadCast(6, bool, localDouble.toString());
        }
        return;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  private void wsGetCustomers(Bundle paramBundle)
  {
    JSONArray localJSONArray = new JSONArray();
    Object localObject = new RestClient(this.baseUrl + "/user/" + paramBundle.getInt("user") + "-" + paramBundle.getInt("type") + "-" + paramBundle.getInt("preferred-db") + "/customers");
    try
    {
      ((RestClient)localObject).execute(RequestMethod.GET);
      boolean bool;
      if (((RestClient)localObject).getResponseCode() == 200)
      {
        this.context = getApplicationContext();
        localObject = new JSONObject(((RestClient)localObject).getResponse());
        if (!((JSONObject)localObject).getBoolean("db-connected")) {
          bool = false;
        }
      }
      while (bool)
      {
        broadCast(5, bool, localJSONArray.toString());
        break;
        localJSONArray = bool.getJSONArray("cust-list");
        localJSONArray = localJSONArray;
        bool = true;
        continue;
        bool = false;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void wsGetLottRate(Bundle paramBundle)
  {
    Double localDouble = Double.valueOf(0.0D);
    RestClient localRestClient = new RestClient(this.baseUrl + "/user/" + paramBundle.getInt("user") + "-" + paramBundle.getInt("type") + "-" + paramBundle.getInt("preferred-db") + "/customer/" + paramBundle.getInt("customer-id") + "-" + paramBundle.getInt("customer-type") + "/lott/" + paramBundle.getInt("lott-id") + "/rate");
    try
    {
      localRestClient.execute(RequestMethod.GET);
      if (localRestClient.getResponseCode() == 200)
      {
        this.context = getApplicationContext();
        localDouble = Double.valueOf(new JSONObject(localRestClient.getResponse()).getDouble("rate"));
      }
      for (boolean bool = true;; bool = false)
      {
        if (bool) {
          broadCast(8, bool, localDouble.toString());
        }
        return;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  private void wsGetLotts(Bundle paramBundle)
  {
    JSONArray localJSONArray = new JSONArray();
    RestClient localRestClient = new RestClient(this.baseUrl + "/user/" + paramBundle.getInt("user") + "-" + paramBundle.getInt("type") + "-" + paramBundle.getInt("preferred-db") + "/customer/" + paramBundle.getInt("customer-id") + "-" + paramBundle.getInt("customer-type") + "/lotts/" + paramBundle.getInt("lott-type"));
    try
    {
      localRestClient.execute(RequestMethod.GET);
      if (localRestClient.getResponseCode() == 200)
      {
        this.context = getApplicationContext();
        localJSONArray = new JSONObject(localRestClient.getResponse()).getJSONArray("lott-array");
      }
      for (boolean bool = true;; bool = false)
      {
        if (bool) {
          broadCast(7, bool, localJSONArray.toString());
        }
        return;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  private void wsGetLottsGlobal(Bundle paramBundle)
  {
    JSONArray localJSONArray = new JSONArray();
    RestClient localRestClient = new RestClient(this.baseUrl + "/user/" + paramBundle.getInt("user") + "-" + paramBundle.getInt("type") + "-" + paramBundle.getInt("preferred-db") + "/lotts");
    try
    {
      localRestClient.execute(RequestMethod.GET);
      if (localRestClient.getResponseCode() == 200)
      {
        this.context = getApplicationContext();
        localJSONArray = new JSONObject(localRestClient.getResponse()).getJSONArray("lott-array");
      }
      for (boolean bool = true;; bool = false)
      {
        if (bool) {
          broadCast(16, bool, localJSONArray.toString());
        }
        return;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  /* Error */
  private void wsGetSaleInfo(Bundle paramBundle)
    throws JSONException
  {
    // Byte code:
    //   0: new 192	java/lang/StringBuilder
    //   3: dup
    //   4: aload_0
    //   5: getfield 79	com/fortune/restful/webservice/WebService:baseUrl	Ljava/lang/String;
    //   8: invokestatic 198	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   11: invokespecial 199	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   14: ldc 201
    //   16: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: aload_1
    //   20: ldc 207
    //   22: invokevirtual 213	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   25: invokevirtual 216	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   28: ldc 218
    //   30: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   33: aload_1
    //   34: ldc 220
    //   36: invokevirtual 213	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   39: invokevirtual 216	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   42: ldc 218
    //   44: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: aload_1
    //   48: ldc 222
    //   50: invokevirtual 213	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   53: invokevirtual 216	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   56: ldc_w 298
    //   59: invokevirtual 205	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: aload_1
    //   63: ldc_w 300
    //   66: invokevirtual 213	android/os/Bundle:getInt	(Ljava/lang/String;)I
    //   69: invokevirtual 216	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   72: invokevirtual 234	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   75: astore_3
    //   76: aconst_null
    //   77: astore_2
    //   78: new 256	com/fortune/restful/webservice/RestClient
    //   81: dup
    //   82: aload_3
    //   83: invokespecial 257	com/fortune/restful/webservice/RestClient:<init>	(Ljava/lang/String;)V
    //   86: astore_3
    //   87: aload_3
    //   88: getstatic 383	com/fortune/restful/webservice/RequestMethod:GET	Lcom/fortune/restful/webservice/RequestMethod;
    //   91: invokevirtual 271	com/fortune/restful/webservice/RestClient:execute	(Lcom/fortune/restful/webservice/RequestMethod;)V
    //   94: aload_3
    //   95: invokevirtual 275	com/fortune/restful/webservice/RestClient:getResponseCode	()I
    //   98: sipush 200
    //   101: if_icmpne +66 -> 167
    //   104: aload_0
    //   105: aload_0
    //   106: invokevirtual 279	com/fortune/restful/webservice/WebService:getApplicationContext	()Landroid/content/Context;
    //   109: putfield 281	com/fortune/restful/webservice/WebService:context	Landroid/content/Context;
    //   112: new 236	org/json/JSONObject
    //   115: dup
    //   116: aload_3
    //   117: invokevirtual 284	com/fortune/restful/webservice/RestClient:getResponse	()Ljava/lang/String;
    //   120: invokespecial 285	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   123: astore_2
    //   124: aload_2
    //   125: ldc_w 287
    //   128: invokevirtual 291	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
    //   131: istore_3
    //   132: iload_3
    //   133: ifne +27 -> 160
    //   136: iconst_0
    //   137: istore_3
    //   138: aload_2
    //   139: astore_2
    //   140: iload_3
    //   141: ifeq +18 -> 159
    //   144: aload_2
    //   145: ifnull +14 -> 159
    //   148: aload_0
    //   149: bipush 11
    //   151: iload_3
    //   152: aload_2
    //   153: invokevirtual 258	org/json/JSONObject:toString	()Ljava/lang/String;
    //   156: invokespecial 293	com/fortune/restful/webservice/WebService:broadCast	(IZLjava/lang/String;)V
    //   159: return
    //   160: iconst_1
    //   161: istore_3
    //   162: aload_2
    //   163: astore_2
    //   164: goto -24 -> 140
    //   167: iconst_0
    //   168: istore_3
    //   169: goto -29 -> 140
    //   172: astore_2
    //   173: aload_2
    //   174: invokevirtual 296	java/lang/Exception:printStackTrace	()V
    //   177: goto -18 -> 159
    //   180: astore_2
    //   181: goto -8 -> 173
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	184	0	this	WebService
    //   0	184	1	paramBundle	Bundle
    //   77	87	2	localJSONObject	JSONObject
    //   172	2	2	localException1	Exception
    //   180	1	2	localException2	Exception
    //   75	42	3	localObject	Object
    //   131	38	3	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   87	124	172	java/lang/Exception
    //   148	159	172	java/lang/Exception
    //   124	132	180	java/lang/Exception
  }
  
  private void wsGetSalesExpiry(Bundle paramBundle) {}
  
  private void wsGetServerTime(Bundle paramBundle) {}
  
  private void wsLogin(Bundle paramBundle)
    throws JSONException
  {
    String str = this.baseUrl + "/user";
    JSONObject localJSONObject = null;
    RestClient localRestClient = new RestClient(str);
    localRestClient.addParam("user", paramBundle.getString("username"));
    localRestClient.addParam("authkey", paramBundle.getString("authkey"));
    localRestClient.addParam("devid", paramBundle.getString("device-id"));
    localRestClient.addParam("appver", paramBundle.getString("app-version"));
    try
    {
      localRestClient.execute(RequestMethod.GET);
      boolean bool;
      if (localRestClient.getResponseCode() == 200)
      {
        bool = true;
        this.context = getApplicationContext();
        localJSONObject = new JSONObject(localRestClient.getResponse());
      }
      for (;;)
      {
        broadCast(1, bool, localJSONObject.toString());
        return;
        bool = false;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
  }
  
  public void doRestRequest(final Bundle paramBundle)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        switch (paramBundle.getInt("com.fortune.restful.req_type"))
        {
        }
        for (;;)
        {
          return;
          try
          {
            WebService.this.wsLogin(paramBundle);
          }
          catch (JSONException localJSONException1)
          {
            localJSONException1.printStackTrace();
          }
          continue;
          try
          {
            WebService.this.wsBuy(paramBundle);
          }
          catch (NoSuchAlgorithmException localNoSuchAlgorithmException1)
          {
            localNoSuchAlgorithmException1.printStackTrace();
          }
          catch (UnsupportedEncodingException localUnsupportedEncodingException1)
          {
            localUnsupportedEncodingException1.printStackTrace();
          }
          catch (JSONException localJSONException2)
          {
            localJSONException2.printStackTrace();
          }
          continue;
          try
          {
            WebService.this.wsDeleteSale(paramBundle);
          }
          catch (JSONException localJSONException3)
          {
            localJSONException3.printStackTrace();
          }
          catch (NoSuchAlgorithmException localNoSuchAlgorithmException2)
          {
            localNoSuchAlgorithmException2.printStackTrace();
          }
          catch (UnsupportedEncodingException localUnsupportedEncodingException2)
          {
            localUnsupportedEncodingException2.printStackTrace();
          }
          continue;
          try
          {
            WebService.this.wsEditSale(paramBundle);
          }
          catch (JSONException localJSONException4)
          {
            localJSONException4.printStackTrace();
          }
          catch (NoSuchAlgorithmException localNoSuchAlgorithmException3)
          {
            localNoSuchAlgorithmException3.printStackTrace();
          }
          catch (UnsupportedEncodingException localUnsupportedEncodingException3)
          {
            localUnsupportedEncodingException3.printStackTrace();
          }
          continue;
          WebService.this.wsGetCustomers(paramBundle);
          continue;
          WebService.this.wsGetCustCredit(paramBundle);
          continue;
          WebService.this.wsGetLotts(paramBundle);
          continue;
          WebService.this.wsGetLottRate(paramBundle);
          continue;
          WebService.this.wsGetSalesExpiry(paramBundle);
          continue;
          WebService.this.wsGetServerTime(paramBundle);
          continue;
          try
          {
            WebService.this.wsGetSaleInfo(paramBundle);
          }
          catch (JSONException localJSONException5)
          {
            localJSONException5.printStackTrace();
          }
          continue;
          try
          {
            WebService.this.wsGenSaleReport(paramBundle);
          }
          catch (JSONException localJSONException6)
          {
            localJSONException6.printStackTrace();
          }
          catch (NoSuchAlgorithmException localNoSuchAlgorithmException4)
          {
            localNoSuchAlgorithmException4.printStackTrace();
          }
          catch (UnsupportedEncodingException localUnsupportedEncodingException4)
          {
            localUnsupportedEncodingException4.printStackTrace();
          }
          continue;
          try
          {
            WebService.this.wsGenWinReport(paramBundle);
          }
          catch (JSONException localJSONException7)
          {
            localJSONException7.printStackTrace();
          }
          catch (NoSuchAlgorithmException localNoSuchAlgorithmException5)
          {
            localNoSuchAlgorithmException5.printStackTrace();
          }
          catch (UnsupportedEncodingException localUnsupportedEncodingException5)
          {
            localUnsupportedEncodingException5.printStackTrace();
          }
          continue;
          WebService.this.wsGetLottsGlobal(paramBundle);
          continue;
          try
          {
            WebService.this.wsGenResultEntryReport(paramBundle);
          }
          catch (JSONException localJSONException8)
          {
            localJSONException8.printStackTrace();
          }
          catch (NoSuchAlgorithmException localNoSuchAlgorithmException6)
          {
            localNoSuchAlgorithmException6.printStackTrace();
          }
          catch (UnsupportedEncodingException localUnsupportedEncodingException6)
          {
            localUnsupportedEncodingException6.printStackTrace();
          }
          continue;
          try
          {
            WebService.this.wsGenPaymentReport(paramBundle);
          }
          catch (JSONException localJSONException9)
          {
            localJSONException9.printStackTrace();
          }
          catch (NoSuchAlgorithmException localNoSuchAlgorithmException7)
          {
            localNoSuchAlgorithmException7.printStackTrace();
          }
          catch (UnsupportedEncodingException localUnsupportedEncodingException7)
          {
            localUnsupportedEncodingException7.printStackTrace();
          }
          continue;
          try
          {
            WebService.this.wsGetAppUpdate(paramBundle);
          }
          catch (JSONException localJSONException10)
          {
            localJSONException10.printStackTrace();
          }
          continue;
          try
          {
            WebService.this.wsGenNetPayReport(paramBundle);
          }
          catch (JSONException localJSONException11)
          {
            localJSONException11.printStackTrace();
          }
          catch (NoSuchAlgorithmException localNoSuchAlgorithmException8)
          {
            localNoSuchAlgorithmException8.printStackTrace();
          }
          catch (UnsupportedEncodingException localUnsupportedEncodingException8)
          {
            localUnsupportedEncodingException8.printStackTrace();
          }
        }
      }
    }).start();
  }
  
  protected void doWakefulWork(Intent paramIntent)
  {
    doRestRequest(paramIntent.getExtras());
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return this.binder;
  }
  
  public void onCreate()
  {
    super.onCreate();
    this.context = getApplicationContext();
    this.baseUrl = getString(2130968578);
  }
  
  public void onDestroy()
  {
    super.onDestroy();
  }
  
  public class WebBinder
    extends Binder
  {
    public WebBinder() {}
    
    public WebService getService()
    {
      return WebService.this;
    }
  }
}


/* Location:           D:\soft\ANDROID\APK HACK\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     com.fortune.restful.webservice.WebService
 * JD-Core Version:    0.7.0.1
 */