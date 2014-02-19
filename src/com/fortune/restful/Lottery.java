package com.fortune.restful;

public class Lottery
{
  String drawDate;
  String groupCode;
  String lottName;
  int masterID;
  Double rate;
  int ticketLength;
  
  public Lottery(String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2)
  {
    this.lottName = paramString1;
    this.masterID = paramInt1;
    this.drawDate = paramString2;
    this.groupCode = paramString3;
    this.ticketLength = paramInt2;
    this.rate = Double.valueOf(0.0D);
  }
  
  public String getDrawDate()
  {
    return this.drawDate;
  }
  
  public String getGroupCode()
  {
    return this.groupCode;
  }
  
  public String getLottName()
  {
    return this.lottName;
  }
  
  public int getMasterID()
  {
    return this.masterID;
  }
  
  public Double getRate()
  {
    return this.rate;
  }
  
  public int getTicketLength()
  {
    return this.ticketLength;
  }
  
  public void setDrawDate(String paramString)
  {
    this.drawDate = paramString;
  }
  
  public void setGroupCode(String paramString)
  {
    this.groupCode = paramString;
  }
  
  public void setLottName(String paramString)
  {
    this.lottName = paramString;
  }
  
  public void setMasterID(int paramInt)
  {
    this.masterID = paramInt;
  }
  
  public void setRate(Double paramDouble)
  {
    this.rate = paramDouble;
  }
  
  public void setTicketLength(int paramInt)
  {
    this.ticketLength = paramInt;
  }
  
  public String toString()
  {
    return this.lottName;
  }
}


/* Location:           D:\soft\ANDROID\APK HACK\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     com.fortune.restful.Lottery
 * JD-Core Version:    0.7.0.1
 */