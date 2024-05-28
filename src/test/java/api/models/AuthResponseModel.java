package api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthResponseModel {
    private String expires, password, token, userId, username, isActive;

    @JsonProperty("created_date")
    private String createdDate;
}
