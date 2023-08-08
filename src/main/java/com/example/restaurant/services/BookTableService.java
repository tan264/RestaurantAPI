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
}
