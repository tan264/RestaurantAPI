package com.example.restaurant.controllers;

import com.example.restaurant.models.BookTable;
import com.example.restaurant.models.ResponseObject;
import com.example.restaurant.services.BookTableService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.restaurant.utils.MyTag.BOOK_TABLE;

@RestController
@RequestMapping("/book-table")
@Tag(name = BOOK_TABLE)
public class BookTableController {

    @Autowired
    BookTableService bookTableService;

    @PostMapping("/add")
    @Operation(description = "Add book table")
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

    @GetMapping("/get-all")
    @Operation(description = "Get all book tables")
    public ResponseEntity<ResponseObject> getAllBookTables() {
        try {
            return ResponseEntity.ok()
                    .body(new ResponseObject(HttpStatus.OK.name(),
                            "Get all book tables successfully",
                            bookTableService.getAllBookTables()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                            e.getMessage(), null));
        }
    }

    @Operation(summary = "You need login with admin account to do this")
    @DeleteMapping("/delete-by-id")
    public ResponseEntity<ResponseObject> deleteById(@RequestParam int id) {
        try {
            return ResponseEntity.ok()
                    .body(new ResponseObject(HttpStatus.OK.name(), "Delete book table successfully",
                            bookTableService.deleteById(id)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(),
                            e.getMessage(), null));
        }
    }

}
