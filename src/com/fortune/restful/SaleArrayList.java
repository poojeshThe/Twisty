package com.fortune.restful;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SaleArrayList
  extends ArrayList<SaleItem>
{
  private Double saleTotalAmt = Double.valueOf(0.0D);
  private int saleTotalCount = 0;
  
  public void add(int paramInt, SaleItem paramSaleItem)
  {
    this.saleTotalAmt = Double.valueOf(this.saleTotalAmt.doubleValue() + paramSaleItem.getAmount().doubleValue());
    this.saleTotalCount += paramSaleItem.getCount();
    super.add(paramInt, paramSaleItem);
  }
  
  public boolean add(SaleItem paramSaleItem)
  {
    boolean bool = super.add(paramSaleItem);
    if (bool)
    {
      this.saleTotalAmt = Double.valueOf(this.saleTotalAmt.doubleValue() + paramSaleItem.getAmount().doubleValue());
      this.saleTotalCount += paramSaleItem.getCount();
    }
    return bool;
  }
  
  public void clear()
  {
    this.saleTotalAmt = Double.valueOf(0.0D);
    this.saleTotalCount = 0;
    super.clear();
  }
  
  public void disable(Object paramObject)
  {
    SaleItem localSaleItem = (SaleItem)paramObject;
    if (!localSaleItem.isDisabled())
    {
      localSaleItem.disable();
      this.saleTotalAmt = Double.valueOf(this.saleTotalAmt.doubleValue() - localSaleItem.getAmount().doubleValue());
      this.saleTotalCount -= localSaleItem.getCount();
    }
  }
  
  public void enable(SaleItem paramSaleItem)
  {
    if (paramSaleItem.isDisabled())
    {
      paramSaleItem.enable();
      this.saleTotalAmt = Double.valueOf(this.saleTotalAmt.doubleValue() + paramSaleItem.getAmount().doubleValue());
      this.saleTotalCount += paramSaleItem.getCount();
    }
  }
  
  public Double getSaleTotalAmt()
  {
    return this.saleTotalAmt;
  }
  
  public int getSaleTotalCount()
  {
    return this.saleTotalCount;
  }
  
  public boolean isAnyEnabled()
  {
    boolean bool = false;
    if (!super.isEmpty())
    {
      for (int i = 0;; i++)
      {
        if (i >= size()) {
          return bool;
        }
        if (!((SaleItem)get(i)).isDisabled()) {
          break;
        }
      }
      bool = true;
    }
    else
    {
      bool = false;
    }
    return bool;
  }
  
  public boolean remove(Object paramObject)
  {
    SaleItem localSaleItem = (SaleItem)paramObject;
    boolean bool = super.remove(localSaleItem);
    if (bool)
    {
      this.saleTotalAmt = Double.valueOf(this.saleTotalAmt.doubleValue() - localSaleItem.getAmount().doubleValue());
      this.saleTotalCount -= localSaleItem.getCount();
    }
    return bool;
  }
  
  public String toJSONML()
    throws JSONException
  {
    JSONObject localJSONObject2 = new JSONObject();
    JSONArray localJSONArray = new JSONArray();
    for (int i = 0; i < size(); i++)
    {
      SaleItem localSaleItem = (SaleItem)get(i);
      if (!localSaleItem.isDisabled())
      {
        JSONObject localJSONObject1 = new JSONObject();
        localJSONObject1.put("LotteryID", localSaleItem.getLottMasterID());
        localJSONObject1.put("SalesAmount", localSaleItem.getAmount());
        localJSONObject1.put("tagName", "LotteryDetails");
        localJSONObject1.put("SerialNo", localSaleItem.getSerialNo());
        localJSONObject1.put("Counts", localSaleItem.getCount());
        localJSONArray.put(localJSONObject1);
      }
    }
    localJSONObject2.put("childNodes", localJSONArray);
    localJSONObject2.put("tagName", "Sales");
    return localJSONObject2.toString();
  }
}


/* Location:           D:\soft\ANDROID\APK HACK\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     com.fortune.restful.SaleArrayList
 * JD-Core Version:    0.7.0.1
 */