package com.fortune.restful.webservice;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class RestClient
{
  private boolean authentication;
  protected Context context;
  private ArrayList<NameValuePair> headers;
  private String jsonBody;
  private String message;
  private ArrayList<NameValuePair> params;
  private String password;
  private String response;
  private int responseCode;
  private String url;
  private String username;
  
  public RestClient(String paramString)
  {
    this.url = paramString;
    this.params = new ArrayList();
    this.headers = new ArrayList();
  }
  
  private HttpUriRequest addBodyParams(HttpUriRequest paramHttpUriRequest)
    throws Exception
  {
    if (this.jsonBody == null)
    {
      if (!this.params.isEmpty()) {
        if (!(paramHttpUriRequest instanceof HttpPost))
        {
          if ((paramHttpUriRequest instanceof HttpPut)) {
            ((HttpPut)paramHttpUriRequest).setEntity(new UrlEncodedFormEntity(this.params, "UTF-8"));
          }
        }
        else {
          ((HttpPost)paramHttpUriRequest).setEntity(new UrlEncodedFormEntity(this.params, "UTF-8"));
        }
      }
    }
    else
    {
      paramHttpUriRequest.addHeader("Content-Type", "application/json");
      if (!(paramHttpUriRequest instanceof HttpPost))
      {
        if ((paramHttpUriRequest instanceof HttpPut)) {
          ((HttpPut)paramHttpUriRequest).setEntity(new StringEntity(this.jsonBody, "UTF-8"));
        }
      }
      else {
        ((HttpPost)paramHttpUriRequest).setEntity(new StringEntity(this.jsonBody, "UTF-8"));
      }
    }
    return paramHttpUriRequest;
  }
  
  private String addGetParams()
    throws Exception
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (!this.params.isEmpty())
    {
      localStringBuffer.append("?");
      Iterator localIterator = this.params.iterator();
      while (localIterator.hasNext())
      {
        NameValuePair localNameValuePair = (NameValuePair)localIterator.next();
        String str;
        if (localStringBuffer.length() <= 1) {
          str = "";
        } else {
          str = "&";
        }
        localStringBuffer.append(str + localNameValuePair.getName() + "=" + URLEncoder.encode(localNameValuePair.getValue(), "UTF-8"));
      }
    }
    return localStringBuffer.toString();
  }
  
  private HttpUriRequest addHeaderParams(HttpUriRequest paramHttpUriRequest)
    throws Exception
  {
    Object localObject = this.headers.iterator();
    while (((Iterator)localObject).hasNext())
    {
      NameValuePair localNameValuePair = (NameValuePair)((Iterator)localObject).next();
      paramHttpUriRequest.addHeader(localNameValuePair.getName(), localNameValuePair.getValue());
    }
    if (this.authentication)
    {
      localObject = new UsernamePasswordCredentials(this.username, this.password);
      paramHttpUriRequest.addHeader(new BasicScheme().authenticate((Credentials)localObject, paramHttpUriRequest));
    }
    return paramHttpUriRequest;
  }
  
  /* Error */
  private static String convertStreamToString(InputStream paramInputStream)
  {
    // Byte code:
    //   0: new 195	java/io/BufferedReader
    //   3: dup
    //   4: new 197	java/io/InputStreamReader
    //   7: dup
    //   8: aload_0
    //   9: invokespecial 200	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   12: invokespecial 203	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   15: astore_2
    //   16: new 140	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 204	java/lang/StringBuilder:<init>	()V
    //   23: astore_1
    //   24: aload_2
    //   25: invokevirtual 207	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   28: astore_3
    //   29: aload_3
    //   30: ifnonnull +12 -> 42
    //   33: aload_0
    //   34: invokevirtual 212	java/io/InputStream:close	()V
    //   37: aload_1
    //   38: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   41: areturn
    //   42: aload_1
    //   43: new 140	java/lang/StringBuilder
    //   46: dup
    //   47: aload_3
    //   48: invokestatic 146	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   51: invokespecial 148	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   54: ldc 214
    //   56: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: invokevirtual 168	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   62: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: pop
    //   66: goto -42 -> 24
    //   69: astore_2
    //   70: aload_2
    //   71: invokevirtual 217	java/io/IOException:printStackTrace	()V
    //   74: aload_0
    //   75: invokevirtual 212	java/io/InputStream:close	()V
    //   78: goto -41 -> 37
    //   81: astore_2
    //   82: aload_2
    //   83: invokevirtual 217	java/io/IOException:printStackTrace	()V
    //   86: goto -49 -> 37
    //   89: astore_1
    //   90: aload_0
    //   91: invokevirtual 212	java/io/InputStream:close	()V
    //   94: aload_1
    //   95: athrow
    //   96: astore_2
    //   97: aload_2
    //   98: invokevirtual 217	java/io/IOException:printStackTrace	()V
    //   101: goto -7 -> 94
    //   104: astore_2
    //   105: aload_2
    //   106: invokevirtual 217	java/io/IOException:printStackTrace	()V
    //   109: goto -72 -> 37
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	112	0	paramInputStream	InputStream
    //   23	20	1	localStringBuilder	java.lang.StringBuilder
    //   89	6	1	localObject	Object
    //   15	10	2	localBufferedReader	java.io.BufferedReader
    //   69	2	2	localIOException1	IOException
    //   81	2	2	localIOException2	IOException
    //   96	2	2	localIOException3	IOException
    //   104	2	2	localIOException4	IOException
    //   28	20	3	str	String
    // Exception table:
    //   from	to	target	type
    //   24	29	69	java/io/IOException
    //   42	66	69	java/io/IOException
    //   74	78	81	java/io/IOException
    //   24	29	89	finally
    //   42	66	89	finally
    //   70	74	89	finally
    //   90	94	96	java/io/IOException
    //   33	37	104	java/io/IOException
  }
  
  private void executeRequest(HttpUriRequest paramHttpUriRequest, String paramString)
  {
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
    Object localObject = localDefaultHttpClient.getParams();
    HttpConnectionParams.setConnectionTimeout((HttpParams)localObject, 30000);
    HttpConnectionParams.setSoTimeout((HttpParams)localObject, 30000);
    try
    {
      localObject = localDefaultHttpClient.execute(paramHttpUriRequest);
      this.responseCode = ((HttpResponse)localObject).getStatusLine().getStatusCode();
      this.message = ((HttpResponse)localObject).getStatusLine().getReasonPhrase();
      localObject = ((HttpResponse)localObject).getEntity();
      if (localObject != null)
      {
        localObject = ((HttpEntity)localObject).getContent();
        this.response = convertStreamToString((InputStream)localObject);
        ((InputStream)localObject).close();
      }
      return;
    }
    catch (ClientProtocolException localClientProtocolException)
    {
      for (;;)
      {
        localDefaultHttpClient.getConnectionManager().shutdown();
        localClientProtocolException.printStackTrace();
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localDefaultHttpClient.getConnectionManager().shutdown();
        localIOException.printStackTrace();
      }
    }
  }
  
  public void addBasicAuthentication(String paramString1, String paramString2)
  {
    this.authentication = true;
    this.username = paramString1;
    this.password = paramString2;
  }
  
  public void addHeader(String paramString1, String paramString2)
  {
    this.headers.add(new BasicNameValuePair(paramString1, paramString2));
  }
  
  public void addParam(String paramString1, String paramString2)
  {
    this.params.add(new BasicNameValuePair(paramString1, paramString2));
  }
  
  public void execute(RequestMethod paramRequestMethod)
    throws Exception
  {
    switch (paramRequestMethod)
    {
    case DELETE: 
      executeRequest((HttpDelete)addHeaderParams(new HttpDelete(this.url)), this.url);
      break;
    case GET: 
      executeRequest((HttpGet)addHeaderParams(new HttpGet(this.url + addGetParams())), this.url);
      break;
    case POST: 
      executeRequest((HttpPost)addBodyParams((HttpPost)addHeaderParams(new HttpPost(this.url))), this.url);
      break;
    case PUT: 
      executeRequest((HttpPut)addBodyParams((HttpPut)addHeaderParams(new HttpPut(this.url))), this.url);
    }
  }
  
  public String getErrorMessage()
  {
    return this.message;
  }
  
  public String getResponse()
  {
    return this.response;
  }
  
  public int getResponseCode()
  {
    return this.responseCode;
  }
  
  public void setContext(Context paramContext)
  {
    this.context = paramContext;
  }
  
  public void setJSONString(String paramString)
  {
    this.jsonBody = paramString;
  }
}


/* Location:           D:\soft\ANDROID\APK HACK\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     com.fortune.restful.webservice.RestClient
 * JD-Core Version:    0.7.0.1
 */