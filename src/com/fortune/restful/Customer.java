

android.os.Parcel
android.os.Parcelable
android.os.Parcelable.Creator

Customer
  

  CREATOR = ()
  
    createFromParcel
    
      
    
    
    []newArray
    
      
    
  
  creditLimit
  custName
  loginType
  preferredDB
  userLoginID
  
  Customer
  
    custName = paramParcel.readString();
    this.userLoginID = paramParcel.readInt();
    this.loginType = paramParcel.readInt();
    this.preferredDB = paramParcel.readString();
    this.creditLimit = Double.valueOf(paramParcel.readDouble());
  }
  
  public Customer(String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    this.custName = paramString1;
    this.userLoginID = paramInt1;
    this.loginType = paramInt2;
    this.preferredDB = paramString2;
    this.creditLimit = Double.valueOf(0.0D);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Double getCreditLimit()
  {
    return this.creditLimit;
  }
  
  public String getCustName()
  {
    return this.custName;
  }
  
  public int getLoginType()
  {
    return this.loginType;
  }
  
  public String getPreferredDB()
  {
    return this.preferredDB;
  }
  
  public int getUserLoginID()
  {
    return this.userLoginID;
  }
  
  public void setCreditLimit(Double paramDouble)
  {
    this.creditLimit = paramDouble;
  }
  
  public void setCustName(String paramString)
  {
    this.custName = paramString;
  }
  
  public void setLoginType(int paramInt)
  {
    this.loginType = paramInt;
  }
  
  public void setPreferredDB(String paramString)
  {
    this.preferredDB = paramString;
  }
  
  public void setUserLoginID(int paramInt)
  {
    this.userLoginID = paramInt;
  }
  
  public String toString()
  {
    return this.custName;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.custName);
    paramParcel.writeInt(this.userLoginID);
    paramParcel.writeInt(this.loginType);
    paramParcel.writeString(this.preferredDB);
    paramParcel.writeDouble(this.creditLimit.doubleValue());
  }
}


/* Location:           D:\soft\ANDROID\APK HACK\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     com.fortune.restful.Customer
 * JD-Core Version:    0.7.0.1
 */