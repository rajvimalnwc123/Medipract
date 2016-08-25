package utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Validation {
    private static Validation ourInstance = new Validation();
    Context mContext;

    public static Validation getInstance(Context mContext) {

        ourInstance.mContext = mContext;
        return ourInstance;
    }

    private Validation() {
    }

    public boolean nameValidation(String mName) {
        if (TextUtils.isEmpty(mName)) {
            Function.showToast(mContext, "Name is required", Toast.LENGTH_LONG);
            return false;
        }
        if (!mName.matches(CommonMember.NAME_REGEX)) {
            Function.showToast(mContext, "Alphabets, numbers and space characters are allowed in Name", Toast.LENGTH_LONG);
            return false;
        }
        if (!TextUtils.isEmpty(mName)) {
            return true;
        }

        return true;
    }


    public boolean verificationCodeValidation(String mVerificationCode) {
        if (TextUtils.isEmpty(mVerificationCode)) {
            Function.showToast(mContext, "Verification Code is required", Toast.LENGTH_LONG);
            return false;
        }
        if (!TextUtils.isEmpty(mVerificationCode)) {
            return true;
        }

        return true;
    }

    public boolean loginValidation(String mEmail, String mPassword,String mHospitalid) {



        if(TextUtils.isEmpty(mHospitalid)){
            Function.showToast(mContext, "Hospital name is required", Toast.LENGTH_LONG);
            return false;
        }


        if (TextUtils.isEmpty(mEmail)) {
            Function.showToast(mContext, "Email is required", Toast.LENGTH_LONG);
            return false;
        }
        if (!mEmail.matches(CommonMember.EMAIL_REGEX)) {
            Function.showToast(mContext, "Email is Invaild", Toast.LENGTH_LONG);
            return false;

        }
        if (TextUtils.isEmpty(mPassword)) {
            Function.showToast(mContext, "Password is required", Toast.LENGTH_LONG);
            return false;
        }

        if (!TextUtils.isEmpty(mEmail) && mEmail.matches(CommonMember.EMAIL_REGEX)
                && !TextUtils.isEmpty(mPassword)) {
            return true;
        }

        return true;
    }


    public boolean signupValidation(String mName, String mEmail, String mPassword, String mPhoneNo, String mProfession, String mCountry, String sUserType) {

        if (TextUtils.isEmpty(mName)) {
            Function.showToast(mContext, "Name is required", Toast.LENGTH_LONG);
            return false;
        }
        if (!mName.matches(CommonMember.NAME_REGEX)) {
            Function.showToast(mContext, "Alphabets, numbers and space characters are allowed in Name", Toast.LENGTH_LONG);
            return false;
        }
        if (TextUtils.isEmpty(mEmail)) {
            Function.showToast(mContext, "Email is required", Toast.LENGTH_LONG);
            return false;
        }
        if (!mEmail.matches(CommonMember.EMAIL_REGEX)) {
            Function.showToast(mContext, "Email is invaild", Toast.LENGTH_LONG);
            return false;
        }
        if (TextUtils.isEmpty(mPassword) && sUserType != CommonMember.FACEBOOK_SIGNUP) {
            Function.showToast(mContext, "Password is required", Toast.LENGTH_LONG);
            return false;
        }
        if (TextUtils.isEmpty(mPhoneNo)) {
            Function.showToast(mContext, "Phone number is required", Toast.LENGTH_LONG);
            return false;
        }
        if (mPhoneNo.length() < 10) {
            Function.showToast(mContext, "Phone number should be 10 character", Toast.LENGTH_LONG);
            return false;
        }
        if (TextUtils.isEmpty(mProfession)) {
            Function.showToast(mContext, "My profession is required", Toast.LENGTH_LONG);
            return false;
        }
        if (TextUtils.isEmpty(mCountry)) {
            Function.showToast(mContext, "My country is required", Toast.LENGTH_LONG);
            return false;
        }
        if (!TextUtils.isEmpty(mName) && mName.matches(CommonMember.NAME_REGEX) && !TextUtils.isEmpty(mEmail) && mEmail.matches(CommonMember.EMAIL_REGEX)
                && (!TextUtils.isEmpty(mPassword) || sUserType == CommonMember.FACEBOOK_SIGNUP) && !TextUtils.isEmpty(mPhoneNo) && mPhoneNo.length() >= 10
                && !TextUtils.isEmpty(mProfession)
                && !TextUtils.isEmpty(mCountry)) {
            return true;
        }
        return true;

    }


    public boolean forgotPasswordValidation(String mEmail) {
        if (TextUtils.isEmpty(mEmail)) {
            Function.showToast(mContext, "Email is required", Toast.LENGTH_LONG);
            return false;

        }
        if (!mEmail.matches(CommonMember.EMAIL_REGEX)) {
            Function.showToast(mContext, "Email is Invaild", Toast.LENGTH_LONG);
            return false;

        }
        if (!TextUtils.isEmpty(mEmail) && mEmail.matches(CommonMember.EMAIL_REGEX)) {
            return true;
        }
        return true;
    }

    public boolean isNotEmpty(View view, String mError) {
        if (view != null && view instanceof EditText) {
            TextView temp = (EditText) view;
            if (!temp.getText().toString().trim().equals(""))
                return true;
            else {
                // String val=String.format(mContext.gtString(R.string.Validation_NoTEmpty), temp.getHint());
                Function.showToast(mContext, mError, Toast.LENGTH_LONG);
                temp.setError(mError);
                return false;
            }
        }
        return true;
    }


    public boolean isEmail(View vEmail, String mError) {
        if (vEmail != null && vEmail instanceof EditText) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            EditText temp = (EditText) vEmail;
            CharSequence inputStr = temp.getText();

            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(inputStr);
            if (matcher.matches()) {
                return true;
            } else {
                // String val=String.format(mContext.getString(R.string.Validation_Notvalid), temp.getHint());
                Function.showToast(mContext, mError, Toast.LENGTH_LONG);
                temp.setError(mError);
                return false;
            }
            //return ((EditText)view).getText().toString().trim();
        }
        return true;
    }

    public boolean isMobileNo(View vNo, String mError) {
        if (vNo != null && vNo instanceof EditText) {
            TextView temp = (EditText) vNo;
            if (((EditText) vNo).getText().toString().length() == 10) {
                return true;
            } else {
                //String val=String.format(mContext.getString(R.string.Validation_Notvalid), ((EditText) vNo).getHint());
                String val = String.format(temp.getHint().toString());
                Function.showToast(mContext, val, Toast.LENGTH_LONG);
                ((EditText) vNo).setError(val);
                return false;
            }
        }
        return true;
    }

    public String getOnlyText(View view) {
        if (view != null && view instanceof EditText) {
            return ((EditText) view).getText().toString().trim();
        }
        return "";
    }

    public long getNumberText(View vNo) {
        if (vNo != null && vNo instanceof EditText) {
            try {
                String val = ((EditText) vNo).getText().toString();

                return Long.parseLong(val);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return 0;
    }
}
