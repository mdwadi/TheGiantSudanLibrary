package com.wadi.service;

import java.io.IOException;
import java.util.List;

import com.wadi.dto.AddBookDto;

public interface addBooksServiceInt {

	public AddBookDto addBookService(AddBookDto dto) throws IOException;

	public List<AddBookDto> listOfBook();

	public AddBookDto findBookByid(long id);

	public String deleteBook(long id);

	public AddBookDto editBook(AddBookDto dto);

}
