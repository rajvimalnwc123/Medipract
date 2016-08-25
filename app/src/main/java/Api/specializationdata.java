package Api;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nwcemp01 on 20/7/16.
 */

public class specializationdata {


    private Integer specialist_id;
    private String specialist_name;
    private String image;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The specialist_id
     */
    public Integer getSpecialist_id() {
        return specialist_id;
    }

    /**
     * @param specialist_id The specialist_id
     */
    public void setSpecialist_id(Integer specialist_id) {
        this.specialist_id = specialist_id;
    }

    /**
     * @return The specialist_name
     */
    public String getSpecialist_name() {
        return specialist_name;
    }

    /**
     * @param specialist_name The specialist_name
     */
    public void setSpecialist_name(String specialist_name) {
        this.specialist_name = specialist_name;
    }

    /**
     * @return The image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}