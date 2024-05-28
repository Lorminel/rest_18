package api.models;

import lombok.Data;

@Data
public class DeleteOneBookModel {

    private String isbn, userId;
}
