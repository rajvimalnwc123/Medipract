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
 * Created by nwcemp01 on 20/7/16.
 */
public class specializationapi {


    private static specializationapi ourInstance = new specializationapi();

    public static specializationapi getInstance() {
        return ourInstance;
    }

    private specializationapi() {
    }

    public void Callresponse(Callback<ResponseBasespecialization> mCallback, String hospitalid) {
        SpecializationApi mGitapi = CommonMember.getInstance().getApiBuilder().create(SpecializationApi.class);
        mGitapi.specialization(hospitalid, mCallback);
    }

    public interface SpecializationApi {
        @FormUrlEncoded
        @POST("/specialist")
        public void specialization(@Field("hospital_id") String hospitalid, Callback<ResponseBasespecialization> mCallback);     //string user is for passing values from edittext for eg: user=basil2style,google


    }


    public class ResponseBasespecialization {


     //   private Boolean success;
      //  private Messageobj message;
        private String Result;
        private List<specializationdata> data = new ArrayList<specializationdata>();
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
       // public Boolean getsuccess() {
        //    return success;
        //}

        /**
         *
         * @param success
         * The success
         */
      /*  public void setsuccess(Boolean success) {
            this.success = success;
        }*/

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
        public List<specializationdata> getData() {
            return data;
        }

        /**
         *
         * @param data
         * The data
         */
        public void setData(List<specializationdata> data) {
            this.data = data;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

        public class Messageobj {


            public String Success;
            private Map<String, Object> additionalProperties = new HashMap<String, Object>();

            /**
             *
             * @return
             * The success
             */
            public String getSuccess() {
                return Success;
            }

            /**
             *
             * @param success
             * The Success
             */
            public void setSuccess(String success) {
                this.Success = Success;
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
