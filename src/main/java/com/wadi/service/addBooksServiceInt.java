package com.wadi.service;

import java.io.IOException;
import java.util.List;

import com.wadi.dto.AddBookDto;
import com.wadi.dto.AuthorDto;
import com.wadi.dto.CategoryDto;

public interface addBooksServiceInt {

	public AddBookDto addBookService(AddBookDto dto) throws IOException;

	public List<AddBookDto> listOfBook() throws IOException;

	public AddBookDto findBookByid(long id);

	public String deleteBook(long id);

	public AddBookDto editBook(AddBookDto dto);
	
	public AuthorDto addAuthor(AuthorDto author);

	public List<AuthorDto> getAuthor();
	
	public CategoryDto addCategory(CategoryDto dto);
	
	public List<CategoryDto> getCategory();
	
	

}
