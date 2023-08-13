package com.example.restaurant.services;

import com.example.restaurant.models.BookTable;
import com.example.restaurant.repositories.BookTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookTableService {

    @Autowired
    BookTableRepository bookTableRepository;

    public BookTable addBookTable(BookTable bookTable) {
        return bookTableRepository.save(bookTable);
    }

    public Iterable<BookTable> getAllBookTables() {
        return bookTableRepository.findAll();
    }

    public BookTable deleteById(int id) {
        BookTable foundedBookTable = bookTableRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(
                        String.format("Book table with id = %d does not exist", id)));

        bookTableRepository.deleteById(id);
        return foundedBookTable;
    }
}
