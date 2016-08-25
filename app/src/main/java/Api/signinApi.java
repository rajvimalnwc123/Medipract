package Api;


import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit.http.Query;
import utils.CommonMember;

/**
 * Created by nwcemp01 on 18/7/16.
 */
public class signinApi {


    private static signinApi ourInstance = new signinApi();

    public static signinApi getInstance() {
        return ourInstance;
    }

    private signinApi() {
    }

    public void Callresponse(Callback<BaseResponsesignin> mCallback, String hospitalid, String loginemail, String password) {
        SignInapi mGitapi = CommonMember.getInstance().getApiBuilder().create(SignInapi.class);
        mGitapi.Login(hospitalid, loginemail, password, mCallback);
    }

    public interface SignInapi {
        @FormUrlEncoded
        @POST("/staffs")
        public void Login(@Field("hospital_id") String hospitalid, @Field("email") String loginemail, @Field("password") String password, Callback<BaseResponsesignin> mCallback);     //string user is for passing values from edittext for eg: user=basil2style,google


    }

    public class BaseResponsesignin {


        private Boolean success;
        private String Result;
        private Data data;
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
         * The message
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
        public Data getData() {
            return data;
        }

        /**
         *
         * @param data
         * The data
         */
        public void setData(Data data) {
            this.data = data;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }


    public class Data {

        private Integer id;
        private String name;
        private String email;
        private Integer role_id;
        private String speacialist_id;
        private String gender;
        private String mobile_no;
        private String address;
        private Integer status;
        private String hospital_id;
        private Integer created_by;
        private String created_at;
        private String updated_at;
        private Integer updated_by;
        private Map<String, Object> additionalProperties = new HashMap<String, Object>();

        /**
         * @return The id
         */
        public Integer getId() {
            return id;
        }

        /**
         * @param id The id
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         * @return The name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name The name
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return The email
         */
        public String getEmail() {
            return email;
        }

        /**
         * @param email The email
         */
        public void setEmail(String email) {
            this.email = email;
        }

        /**
         * @return The role_id
         */
        public Integer getRole_id() {
            return role_id;
        }

        /**
         * @param role_id The role_id
         */
        public void setRole_id(Integer role_id) {
            this.role_id = role_id;
        }

        /**
         * @return The speacialist_id
         */
        public String getSpeacialist_id() {
            return speacialist_id;
        }

        /**
         * @param speacialist_id The speacialist_id
         */
        public void setSpeacialist_id(String speacialist_id) {
            this.speacialist_id = speacialist_id;
        }

        /**
         * @return The gender
         */
        public String getGender() {
            return gender;
        }

        /**
         * @param gender The gender
         */
        public void setGender(String gender) {
            this.gender = gender;
        }

        /**
         * @return The mobile_no
         */
        public String getMobile_no() {
            return mobile_no;
        }

        /**
         * @param mobile_no The mobile_no
         */
        public void setMobile_no(String mobile_no) {
            this.mobile_no = mobile_no;
        }

        /**
         * @return The address
         */
        public String getAddress() {
            return address;
        }

        /**
         * @param address The address
         */
        public void setAddress(String address) {
            this.address = address;
        }

        /**
         * @return The status
         */
        public Integer getStatus() {
            return status;
        }

        /**
         * @param status The status
         */
        public void setStatus(Integer status) {
            this.status = status;
        }

        /**
         * @return The hospital_id
         */
        public String getHospital_id() {
            return hospital_id;
        }

        /**
         * @param hospital_id The hospital_id
         */
        public void setHospital_id(String hospital_id) {
            this.hospital_id = hospital_id;
        }

        /**
         * @return The created_by
         */
        public Integer getCreated_by() {
            return created_by;
        }

        /**
         * @param created_by The created_by
         */
        public void setCreated_by(Integer created_by) {
            this.created_by = created_by;
        }

        /**
         * @return The created_at
         */
        public String getCreated_at() {
            return created_at;
        }

        /**
         * @param created_at The created_at
         */
        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        /**
         * @return The updated_at
         */
        public String getUpdated_at() {
            return updated_at;
        }

        /**
         * @param updated_at The updated_at
         */
        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        /**
         * @return The updated_by
         */
        public Integer getUpdated_by() {
            return updated_by;
        }

        /**
         * @param updated_by The updated_by
         */
        public void setUpdated_by(Integer updated_by) {
            this.updated_by = updated_by;
        }

        public Map<String, Object> getAdditionalProperties() {
            return this.additionalProperties;
        }

        public void setAdditionalProperty(String name, Object value) {
            this.additionalProperties.put(name, value);
        }

    }
}

