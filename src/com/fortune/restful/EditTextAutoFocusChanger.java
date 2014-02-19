package com.fortune.restful;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import com.fortune.restful.activities.BuyActivity;

public class EditTextAutoFocusChanger
  implements TextWatcher
{
  private BuyActivity mBuyActivity;
  private EditText mEditText;
  private View mNextView;
  
  public EditTextAutoFocusChanger(EditText paramEditText, View paramView, BuyActivity paramBuyActivity)
  {
    this.mEditText = paramEditText;
    this.mNextView = paramView;
    this.mBuyActivity = paramBuyActivity;
  }
  
  public void afterTextChanged(Editable paramEditable)
  {
    if (this.mBuyActivity.getSelectedLottType() <= this.mEditText.getText().length()) {
      this.mNextView.requestFocus();
    }
  }
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
}


/* Location:           D:\soft\ANDROID\APK HACK\get-apk-source_win\classes_dex2jar.jar
 * Qualified Name:     com.fortune.restful.EditTextAutoFocusChanger
 * JD-Core Version:    0.7.0.1
 */