

java.io.UnsupportedEncodingException
java.security.MessageDigest
java.security.NoSuchAlgorithmException

CommonUtils

  DEVICE_ACTIVATION_KEY_SALT = "O*YR#GU421ca;dJB&*TFjnc(&TfyYG&*^"
  PASSWORD_RAND_SALT_LENTH = 5
  WS_REQ_KEY_SALT = "F^hd23bunc98t8UINCSf67#S$INMNPOknsdbE$^Eu"
  
  bytesToHexString[]
  
     = ()
     ( = 0; <length; ++
    
       = toHexString0xFF
       (length()==1 {
        append'0'
      
      append
    
    toString()
  
  
  checkDevActivation, 
    , 
  
     = 
     = getInstance"SHA-256"
    updategetBytes"UTF-8"
    update"O*YR#GU421ca;dJB&*TFjnc(&TfyYG&*^"getBytes"UTF-8"
     (bytesToHexStringdigest()equals {
       = 
    
    
  
  
  genKeyFromUrl
    , 
  
    getKeyFromString, "F^hd23bunc98t8UINCSf67#S$INMNPOknsdbE$^Eu"
  
  
  getKeyFromPassword
    , 
  
     = 5nextString()
     = getKeyFromString, 
    
  
  
  getKeyFromString, 
    , 
  
     = getInstance"SHA-256"
    updategetBytes"UTF-8"
    updategetBytes"UTF-8"
    bytesToHexStringdigest()
  



/* Location:           D:\soft\ANDROID\APK HACK\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     com.fortune.restful.CommonUtils
 * JD-Core Version:    0.7.0.1
 */