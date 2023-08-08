package com.example.restaurant.controllers;

import com.example.restaurant.models.BookTable;
import com.example.restaurant.models.ResponseObject;
import com.example.restaurant.services.BookTableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.restaurant.utils.MyTag.BOOK_TABLE;

@RestController
@RequestMapping("/book-table")
@Tag(name = BOOK_TABLE)
public class BookTableController {

    @Autowired
    BookTableService bookTableService;

    @PostMapping("/add")
    @Operation(summary = "Add book table")
    public ResponseEntity<ResponseObject> addBookTable(@RequestBody BookTable bookTable) {
        try {
            BookTable addedBookTable = bookTableService.addBookTable(bookTable);
            return ResponseEntity.ok()
                    .body(new ResponseObject(HttpStatus.OK.name(), "Add book table successfully",
                            addedBookTable));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                            e.getMessage(), null));
        }
    }

}
