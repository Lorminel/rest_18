package tests;

import api.BooksApi;
import api.models.AddBookRequestModel;
import api.models.AuthResponseModel;
import api.models.DeleteOneBookModel;
import api.models.IsbnModel;
import helpers.extensions.WithLogin;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import java.util.ArrayList;
import java.util.List;

import static api.AuthorizationApi.getAuthResponse;
import static tests.TestData.*;

@Feature("Profile tests on demoqa.com")
public class CollectionTests extends TestBase {
    AuthResponseModel authResponse = getAuthResponse(credentials);
    BooksApi booksApi = new BooksApi();
    ProfilePage profilePage = new ProfilePage();
    IsbnModel isbn = new IsbnModel(isbnGit);
    AddBookRequestModel addBook = new AddBookRequestModel();
    DeleteOneBookModel deleteOneBookData = new DeleteOneBookModel();


    @Test
    @DisplayName("Delete one book from profile check")
    @WithLogin
    void deleteBookFromCollection() {

        booksApi.deleteAllBooks(authResponse);

        List<IsbnModel> isbnList = new ArrayList<>();
        isbnList.add(isbn);
        addBook.setCollectionOfIsbns(isbnList);
        addBook.setUserId(authResponse.getUserId());
        booksApi.addBook(authResponse, addBook);

        deleteOneBookData.setUserId(authResponse.getUserId());
        deleteOneBookData.setIsbn(isbnGit);
        booksApi.deleteOneBook(authResponse, deleteOneBookData);

        profilePage.openPage()
                .checkUser(authResponse.getUsername())
                .checkBooksListDoesNotContainBook(gitBookName);
    }

}
