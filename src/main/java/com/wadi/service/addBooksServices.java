package com.wadi.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wadi.bo.addBookBo;
import com.wadi.dao.booksRepository;
import com.wadi.dto.AddBookDto;
import com.wadi.file.UploadServiceInterface;
import com.wadi.share.Utils;

@Service
public class addBooksServices implements addBooksServiceInt {

	@Autowired
	private booksRepository repository;

	@Autowired
	private UploadServiceInterface upload;

	@Autowired
	Utils util;

	@Override
	public AddBookDto addBookService(AddBookDto dto) throws IOException {

		String title;
		addBookBo bo = new addBookBo();

		BeanUtils.copyProperties(dto, bo);

		title = dto.getTitle().trim().concat(" by " + dto.getAuthor().trim());

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
		addBookBo resbo = repository.save(bo);

		AddBookDto resDto = new AddBookDto();
		BeanUtils.copyProperties(resbo, resDto);

		return resDto;
	}

	public List<AddBookDto> listOfBook() {

		List<addBookBo> bo = new ArrayList<addBookBo>();

		bo = repository.findAll();

		List<AddBookDto> listDto = new ArrayList<>();

		for (addBookBo bookbo : bo) {

			AddBookDto dto = new AddBookDto();

			BeanUtils.copyProperties(bookbo, dto);

			listDto.add(dto);

		}

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
		title = dto.getTitle().trim().concat(" by " + dto.getAuthor().trim());
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

}
