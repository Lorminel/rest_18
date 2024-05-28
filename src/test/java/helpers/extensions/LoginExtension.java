package helpers.extensions;

import api.models.AuthResponseModel;
import io.qameta.allure.Step;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static api.AuthorizationApi.getAuthResponse;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static tests.TestData.credentials;

public class LoginExtension implements BeforeEachCallback {

    private static final String userIdCookieKey = "userID";
    private static final String expiresCookieKey = "expires";
    private static final String tokenCookieKey = "token";

    @Override
    @Step("Set cookie to browser")
    public void beforeEach(ExtensionContext context) {

        AuthResponseModel authResponse = getAuthResponse(credentials);

        open("/favicon.ico");

        getWebDriver().manage().addCookie(new Cookie(userIdCookieKey, authResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie(expiresCookieKey, authResponse.getExpires()));
        getWebDriver().manage().addCookie(new Cookie(tokenCookieKey, authResponse.getToken()));

    }
}
