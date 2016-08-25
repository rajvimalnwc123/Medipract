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
 * Created by nwcemp01 on 26/7/16.
 */
public class booklaterdoctorApi {


    private static booklaterdoctorApi ourInstance = new booklaterdoctorApi();

    public static booklaterdoctorApi getInstance() {
        return ourInstance;
    }

    private booklaterdoctorApi() {
    }

    public void Callresponse(Callback<ResponseBasebooklaterdoctor> mCallback, String hospitalid, String specialistId, String dateselect) {
        SpecializationApi mGitapi = CommonMember.getInstance().getApiBuilder().create(SpecializationApi.class);
        mGitapi.specialization(hospitalid, specialistId, dateselect, mCallback);
    }

    public interface SpecializationApi {
        @FormUrlEncoded
        @POST("/dateavailability")
        public void specialization(@Field("hospital_id") String hospitalid, @Field("specialist_id") String specialistId, @Field("date") String dateselect, Callback<ResponseBasebooklaterdoctor> mCallback);     //string user is for passing values from edittext for eg: user=basil2style,google


    }


    public class ResponseBasebooklaterdoctor {

        private Boolean success;
        private String Result;
        private List<BooklaterDatum> data = new ArrayList<BooklaterDatum>();
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
         * The result
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

        /**
         *
         * @return
         * The data
         */
        public List<BooklaterDatum> getData() {
            return data;
        }

        /**
         *
         * @param data
         * The data
         */
        public void setData(List<BooklaterDatum> data) {
            this.data = data;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }


    public class BooklaterDatum {
        private Integer id;
        private String name;
        private String image;
        private Integer status;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         *
         * @return
         * The id
         */
        public Integer getId() {
            return id;
        }

        /**
         *
         * @param id
         * The id
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         *
         * @return
         * The name
         */
        public String getName() {
            return name;
        }

        /**
         *
         * @param name
         * The name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         *
         * @return
         * The image
         */
        public String getImage() {
            return image;
        }

        /**
         *
         * @param image
         * The image
         */
        public void setImage(String image) {
            this.image = image;
        }

        /**
         *
         * @return
         * The status
         */
        public Integer getStatus() {
            return status;
        }

        /**
         *
         * @param status
         * The status
         */
        public void setStatus(Integer status) {
            this.status = status;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }
}
