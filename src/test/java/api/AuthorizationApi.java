package api;

import api.models.AuthRequestModel;
import api.models.AuthResponseModel;
import io.qameta.allure.Step;

import static api.specs.TestSpecs.requestSpecification;
import static api.specs.TestSpecs.statusCodeResponseSpec;
import static io.restassured.RestAssured.given;

public class AuthorizationApi {

    @Step("Authorize with API and save auth response")
    public static final AuthResponseModel getAuthResponse(AuthRequestModel credentials) {

        return given(requestSpecification)
                .body(credentials)
                .when()
                .post("Account/v1/Login")
                .then()
                .spec(statusCodeResponseSpec(200))
                .extract().as(AuthResponseModel.class);
    }

}
