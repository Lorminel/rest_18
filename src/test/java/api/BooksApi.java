package api;

import api.models.AddBookRequestModel;
import api.models.AuthResponseModel;
import api.models.DeleteOneBookModel;
import io.qameta.allure.Step;

import static api.specs.TestSpecs.requestSpecification;
import static api.specs.TestSpecs.statusCodeResponseSpec;
import static io.restassured.RestAssured.given;

public class BooksApi {

    @Step("Delete all books from profile")
    public void deleteAllBooks(AuthResponseModel authResponse) {

        given(requestSpecification)
                .header("Authorization", "Bearer " + authResponse.getToken())
                .queryParam("UserId", authResponse.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(statusCodeResponseSpec(204));
    }

    @Step("Delete one specific book from profile")
    public void deleteOneBook(AuthResponseModel authResponse, DeleteOneBookModel deleteOneBookData) {

        given(requestSpecification)
                .header("Authorization", "Bearer " + authResponse.getToken())
                .body(deleteOneBookData)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(statusCodeResponseSpec(204));
    }

    @Step("Add book to profile")
    public void addBook(AuthResponseModel authResponse, AddBookRequestModel addBooks) {

        given(requestSpecification)
                .header("Authorization", "Bearer " + authResponse.getToken())
                .body(addBooks)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(statusCodeResponseSpec(201));
    }
}
