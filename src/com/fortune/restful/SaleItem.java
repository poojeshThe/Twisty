package com.fortune.restful;

public class SaleItem
{
  private int count = 0;
  private boolean disabled = false;
  private Lottery lott = null;
  private int serialNo = 0;
  
  public SaleItem(Lottery paramLottery, int paramInt1, int paramInt2)
  {
    this.lott = paramLottery;
    this.serialNo = paramInt1;
    this.count = paramInt2;
    this.disabled = false;
  }
  
  public void disable()
  {
    this.disabled = true;
  }
  
  public void enable()
  {
    this.disabled = false;
  }
  
  public Double getAmount()
  {
    return Double.valueOf(this.lott.getRate().doubleValue() * this.count);
  }
  
  public int getCount()
  {
    return this.count;
  }
  
  public int getLottLength()
  {
    return this.lott.getTicketLength();
  }
  
  public int getLottMasterID()
  {
    return this.lott.getMasterID();
  }
  
  public String getLottName()
  {
    return this.lott.getLottName();
  }
  
  public String getSerialNo()
  {
    int i;
    if (!this.lott.getGroupCode().equals("0"))
    {
      if (!this.lott.getGroupCode().equals("00")) {
        i = 3;
      } else {
        i = 2;
      }
    }
    else {
      i = 1;
    }
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(i);
    String str = String.format("%%0%dd", arrayOfObject);
    arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(this.serialNo);
    return String.format(str, arrayOfObject);
  }
  
  public boolean isDisabled()
  {
    return this.disabled;
  }
  
  public void setCount(int paramInt)
  {
    this.count = paramInt;
  }
  
  public void setSerialNo(int paramInt)
  {
    this.serialNo = paramInt;
  }
  
  public String toString()
  {
    int i;
    if (!this.lott.getGroupCode().equals("0"))
    {
      if (!this.lott.getGroupCode().equals("00")) {
        i = 3;
      } else {
        i = 2;
      }
    }
    else {
      i = 1;
    }
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(i);
    String str = String.format("%%0%dd", arrayOfObject);
    arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(this.serialNo);
    str = String.format(str, arrayOfObject);
    return this.lott.getLottName() + " - No: " + str + ", Count: " + this.count + ", Amt: " + getAmount();
  }
}


/* Location:           D:\soft\ANDROID\APK HACK\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     com.fortune.restful.SaleItem
 * JD-Core Version:    0.7.0.1
 */