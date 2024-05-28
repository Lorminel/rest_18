package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {

    private SelenideElement booksTable = $(".ReactTable"),
            userNameField = $("#userName-value");

    @Step("Open profile page")
    public ProfilePage openPage() {
        open("/profile");

        return this;
    }

    @Step("Check username on profile page")
    public ProfilePage checkUser(String userName) {
        userNameField.shouldHave(text(userName));

        return this;
    }

    @Step("Check that specific book is not displayed n profile")
    public ProfilePage checkBooksListDoesNotContainBook(String bookName) {

        booksTable.shouldNotHave(text(bookName));

        return this;
    }


}
