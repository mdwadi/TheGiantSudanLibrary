package com.wadi.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wadi.bo.Author;
import com.wadi.bo.Category;
import com.wadi.bo.addBookBo;
import com.wadi.dao.AuthorRepository;
import com.wadi.dao.CategoryRepository;
import com.wadi.dao.booksRepository;
import com.wadi.dto.AddBookDto;
import com.wadi.dto.AuthorDto;
import com.wadi.dto.CategoryDto;
import com.wadi.file.UploadServiceInterface;
import com.wadi.share.Utils;

@Service
public class addBooksServices implements addBooksServiceInt {

	@Autowired
	private booksRepository repository;

	@Autowired
	private CategoryRepository category;

	@Autowired
	private AuthorRepository author;

	@Autowired
	private AuthorRepository authorRpository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UploadServiceInterface upload;

	@Autowired
	Utils util;

	@Override
	public AddBookDto addBookService(AddBookDto dto) throws IOException {

		String title;
		addBookBo bo = new addBookBo();

		BeanUtils.copyProperties(dto, bo);

		if (dto.getAuthor().getNameAr() == null) {

			title = dto.getTitle().trim().concat(" by " + dto.getAuthor().getNameAr().trim());

		} else {

			title = dto.getTitle().trim().concat(" by " + dto.getAuthor().getNameEn().trim());

		}
		title.trim();
		bo.setBookUrl(title);
		bo.setImageUrl(title);
		try {

			if (repository.findByBookUrl(bo.getBookUrl()) != null)
				throw new RuntimeException("Record already exist");
			upload.upload(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (author.findByNameAr(bo.getAuthor().getNameAr()) == null
				|| author.findByNameEn(bo.getAuthor().getNameEn()) == null) {
			Author bores = author.save(bo.getAuthor());
		}
		addBookBo resbo = repository.save(bo);


		AddBookDto resDto = new AddBookDto();
		BeanUtils.copyProperties(resbo, resDto);

		return resDto;
	}

	public List<AddBookDto> listOfBook() throws IOException {

		List<addBookBo> bo = new ArrayList<addBookBo>();

		bo = repository.findAll();

		List<AddBookDto> listDto = upload.findListimage(bo);

		return listDto;

	}

	@Override
	public AddBookDto findBookByid(long id) {

		addBookBo bo = repository.findById(id);

		AddBookDto dto = new AddBookDto();

		BeanUtils.copyProperties(bo, dto);

		return dto;
	}

	@Override
	public String deleteBook(long id) {

		addBookBo bo = repository.findById(id);
		try {
			String delete = upload.deleteUrl(bo.getBookUrl());

			if (delete.equals("Success")) {
				String msg = repository.deleteById(id);

				return delete;
			} else {
				return delete;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return "Exception";
		}

	}

	@Override
	public AddBookDto editBook(AddBookDto dto) {

		String title;
		title = dto.getTitle().trim().concat(" by " + dto.getAuthor().getNameEn().trim());
		addBookBo bo = repository.findById(dto.getId());
		String url = title.trim();
		upload.editFile(bo.getBookUrl(), url);

		addBookBo editbo = new addBookBo();
		BeanUtils.copyProperties(dto, editbo);

		editbo.setBookUrl(url);
		editbo.setImageUrl(url);
		addBookBo resbo = repository.save(editbo);

		AddBookDto resdto = new AddBookDto();
		BeanUtils.copyProperties(resbo, resdto);

		return resdto;
	}

	@Override
	public CategoryDto addCategory(CategoryDto dto) {

		Category bo = new Category();

		BeanUtils.copyProperties(dto, bo);

		bo = category.save(bo);

		CategoryDto resdto = new CategoryDto();
		BeanUtils.copyProperties(bo, resdto);

		return resdto;
	}

	@Override
	public List<CategoryDto> getCategory() {

		List<Category> bolist = new ArrayList<>();

		bolist = category.findAll();

		List<CategoryDto> listdto = new ArrayList<>();

		for (Category bo : bolist) {
			CategoryDto dto = new CategoryDto();
			BeanUtils.copyProperties(bo, dto);

			listdto.add(dto);
		}

		return listdto;
	}

	@Override
	public AuthorDto addAuthor(AuthorDto dto) {
		// TODO Auto-generated method stub
		Author bo = new Author();

		BeanUtils.copyProperties(dto, bo);

		bo = author.save(bo);

		AuthorDto resdto = new AuthorDto();
		BeanUtils.copyProperties(bo, resdto);

		return resdto;
	}

	@Override
	public List<AuthorDto> getAuthor() {

		List<Author> authlist = author.findAll();

		List<AuthorDto> dtolist = new ArrayList<>();
		for (Author bo : authlist) {
			AuthorDto dto = new AuthorDto();
			BeanUtils.copyProperties(bo, dto);

			dtolist.add(dto);

		}
		return dtolist;
	}

}
