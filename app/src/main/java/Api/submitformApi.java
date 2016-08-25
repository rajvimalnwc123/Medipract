package Api;

import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import utils.CommonMember;

/**
 * Created by nwcemp01 on 25/7/16.
 */
public class submitformApi {

    private static submitformApi ourInstance = new submitformApi();

    public static submitformApi getInstance() {
        return ourInstance;
    }

    private submitformApi() {
    }

    public void Callresponse(Callback<BaseResponsesubmitform> mCallback, String Pname, String Pmobileno, String Pdate,String specialityid, String doctorid, String hospitalid, String createdtime) {
        SignInapi mGitapi = CommonMember.getInstance().getApiBuilder().create(SignInapi.class);
        mGitapi.Login(Pname, Pmobileno, Pdate, specialityid,doctorid, hospitalid, createdtime, mCallback);
    }

    public interface SignInapi {
        @FormUrlEncoded
        @POST("/saveappointment")
        public void Login(@Field("patient_name") String Pname, @Field("mobile_no") String Pmobileno, @Field("appointment_date") String Pdate,@Field("specialist_id")String specialityid, @Field("doctor_id") String doctorid, @Field("hospital_id") String hospitalid, @Field("appointment_session") String createdtime, Callback<BaseResponsesubmitform> mCallback);     //string user is for passing values from edittext for eg: user=basil2style,google


    }

    public class BaseResponsesubmitform {


        private Boolean success;
        private String Result;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * @return The success
         */
        public Boolean getSuccess() {
            return success;
        }

        /**
         * @param success The success
         */
        public void setSuccess(Boolean success) {
            this.success = success;
        }

        /**
         * @return The message
         */
        public String getMessage() {
            return Result;
        }

        /**
         *
         * @param message
         * The Message
         */
        public void setMessage(String message) {
            this.Result = message;
        }


        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }


    public class Message {

        private String success;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * @return The success
         */
        public String getSuccess() {
            return success;
        }

        /**
         * @param success The Success
         */
        public void setSuccess(String success) {
            this.success = success;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }
    }
}
