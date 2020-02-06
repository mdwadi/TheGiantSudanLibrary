package com.wadi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wadi.dto.AddBookDto;
import com.wadi.dto.AuthorDto;
import com.wadi.dto.CategoryDto;
import com.wadi.file.UploadServiceInterface;
import com.wadi.response.BookVoResponse;
import com.wadi.service.addBooksServiceInt;
import com.wadi.vo.AddBookVo;
import com.wadi.vo.AuthorVo;
import com.wadi.vo.CategoryVo;

import ch.qos.logback.classic.pattern.SyslogStartConverter;

@RestController(MediaType.APPLICATION_JSON_VALUE)
public class ControllerAPI {

	@Autowired
	private addBooksServiceInt Service;

	@Autowired
	private UploadServiceInterface upload;
	
	
	@PostMapping("/addBook")
	public BookVoResponse AddBook(@RequestParam("book") MultipartFile book, @RequestParam("image") MultipartFile image, @RequestParam("vo") String vo) throws IOException {
		AddBookDto dto = new AddBookDto();
		//BeanUtils.copyProperties(vo, dto);
		dto=new ObjectMapper().readValue(vo, AddBookDto.class);
		System.out.println("ControllerAPI.AddBook(------)"); 
		dto.setImage(image);
		dto.setBook(book);
		AddBookDto resDto = Service.addBookService(dto);

		BookVoResponse resVo = new BookVoResponse();
		BeanUtils.copyProperties(resDto, resVo);

		return resVo;

	}

	@GetMapping(path = "/findbook/{id}")
	public BookVoResponse findBook(@PathVariable long id) {
		AddBookDto dto = Service.findBookByid(id);

		BookVoResponse vo = new BookVoResponse();

		BeanUtils.copyProperties(dto, vo);

		return vo;

	}

	@GetMapping("/bookList")
	public List<BookVoResponse> getAllBook() throws IOException {

		List<AddBookDto> listDto = Service.listOfBook();

		List<BookVoResponse> listVo = new ArrayList<>();

		for (AddBookDto dto : listDto) {

			BookVoResponse vo = new BookVoResponse();
	
			BeanUtils.copyProperties(dto, vo);

			System.out.println(vo.getTitle() + "====ccdto===" + vo.getImgeContent());

			listVo.add(vo);
		}
		return listVo;

	}

	@PutMapping("/editBook/{id}")
	public BookVoResponse editBook(@PathVariable long id, @RequestBody AddBookVo vo) throws IOException {

		AddBookDto dto = Service.findBookByid(id);

		BookVoResponse resVo = new BookVoResponse();

		if (dto != null) {
			vo.setId(dto.getId());
			BeanUtils.copyProperties(vo, dto);

			AddBookDto resDto = Service.editBook(dto);

			BeanUtils.copyProperties(resDto, resVo);
		} else {
			return null;
		}

		return resVo;

	}

	@DeleteMapping("/deletBook/{id}")
	public String DeleteBook(@PathVariable long id) {

		String delete = Service.deleteBook(id);

		return delete;

	}

	@GetMapping("/readBook/{String}")
	public String ReadBook(@PathVariable String bookUrl, HttpServletRequest req, HttpSession session)
			throws IOException {

		AddBookDto dto = upload.findBook(bookUrl);

		session.setAttribute("bookfile", dto.getBookContent());

		return "pdfViewer";

	}

	@GetMapping("/downloadBook/{String}")
	public String DownloadBook(@PathVariable String bookUrl, HttpServletRequest req, HttpSession session)
			throws IOException {

		AddBookDto dto = upload.findBook(bookUrl);

		session.setAttribute("bookfile", dto.getBookContent());

		return "pdfDownload";

	}

	@GetMapping("/category")
	public List<CategoryDto> getCategory() {

		List<CategoryDto> dto = Service.getCategory();

		return dto;
	}

	@PostMapping("/category")
	public CategoryDto addCategory(@RequestBody CategoryVo vo) {

		CategoryDto dto = new CategoryDto();

		BeanUtils.copyProperties(vo, dto);

		CategoryDto resdto = Service.addCategory(dto);

		return resdto;
	}

	@GetMapping("/author")
	public List<AuthorDto> getAuthor() {

		List<AuthorDto> listdto = Service.getAuthor();

		return listdto;

	}

	@PostMapping("/author")
	public AuthorDto addAuthor(@RequestBody AuthorVo vo) {

		AuthorDto dto = new AuthorDto();

		BeanUtils.copyProperties(vo, dto);

		AuthorDto resdto = Service.addAuthor(dto);

		return resdto;

	}

}
