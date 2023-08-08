package com.example.restaurant.repositories;

import com.example.restaurant.models.BookTable;
import org.springframework.data.repository.CrudRepository;

public interface BookTableRepository extends CrudRepository<BookTable, Integer> {
}
