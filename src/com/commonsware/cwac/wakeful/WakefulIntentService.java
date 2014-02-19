

android.app.IntentService
android.content.Context
android.content.Intent
android.os.PowerManager
android.os.PowerManager.WakeLock

WakefulIntentService
  

  LOCK_NAME_STATIC = "com.commonsware.cwac.wakeful.WakefulIntentService"
  lockStatic = 
  
  WakefulIntentService
  
    
    setIntentRedelivery
  
  
  /**
   * @deprecated
   */
  getLock
  
    
    
       (lockStatic==
      
        lockStatic = getSystemService"power"newWakeLock1, "com.commonsware.cwac.wakeful.WakefulIntentService"
        lockStaticsetReferenceCounted
      
       = lockStatic
      
    
    
    
       = 
      
    
  
  
  sendWakefulWork, 
  
    getLockacquire()
    startService
  
  
  sendWakefulWork, 
  
    sendWakefulWork, , 
  
  
  doWakefulWork
  
  onHandleIntent
  
    
    
      doWakefulWork
      
    
    
    
      getLockrelease()
    
  
  
  onStartCommand, , 
  
     (getLockisHeld() {
      getLockacquire()
    
    onStartCommand, , 
    3
  



/* Location:           D:\soft\ANDROID\APK HACK\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     com.commonsware.cwac.wakeful.WakefulIntentService
 * JD-Core Version:    0.7.0.1
 */