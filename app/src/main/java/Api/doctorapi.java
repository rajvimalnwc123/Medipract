package Api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import utils.CommonMember;

/**
 * Created by nwcemp01 on 21/7/16.
 */
public class doctorapi {


    private static doctorapi ourInstance = new doctorapi();

    public static doctorapi getInstance() {
        return ourInstance;
    }

    private doctorapi() {
    }

    public void Callresponse(Callback<ResponseBasedoctor> mCallback, String hospitalid,String specialistId) {
        SpecializationApi mGitapi = CommonMember.getInstance().getApiBuilder().create(SpecializationApi.class);
        mGitapi.specialization(hospitalid,specialistId, mCallback);
    }

    public interface SpecializationApi {
        @FormUrlEncoded
        @POST("/doctorlist")
        public void specialization(@Field("hospital_id") String hospitalid,@Field("specialist_id")String specialistId, Callback<ResponseBasedoctor> mCallback);     //string user is for passing values from edittext for eg: user=basil2style,google


    }


    public class ResponseBasedoctor {


        private Boolean success;
        private String Result;

        private List<doctordata> data = new ArrayList<doctordata>();
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();
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
        /**
         *
         * @return
         * The success
         */
        public Boolean getSuccess() {
            return success;
        }

        /**
         *
         * @param success
         * The success
         */
        public void setSuccess(Boolean success) {
            this.success = success;
        }

        /**
         *
         * @return
         * The message
         */

        /**
         *
         * @return
         * The imageurl
         */

        /**
         *
         * @return
         * The data
         */
        public List<doctordata> getData() {
            return data;
        }

        /**
         *
         * @param data
         * The data
         */
        public void setData(List<doctordata> data) {
            this.data = data;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        public class Messageobj {


            public String success;
            private Map<String, Object> additionalProperties = new HashMap<String, Object>();

            /**
             *
             * @return
             * The success
             */
            public String getSuccess() {
                return success;
            }

            /**
             *
             * @param success
             * The Success
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



}
