package com.wadi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wadi.dto.AddBookDto;
import com.wadi.file.UploadServiceInterface;
import com.wadi.response.BookVoResponse;
import com.wadi.service.addBooksServiceInt;
import com.wadi.vo.AddBookVo;
@CrossOrigin(maxAge = 3600)
@RestController
public class ControllerAPI {

	@Autowired
	private addBooksServiceInt Service;

	@Autowired
	private UploadServiceInterface upload;

	@PostMapping("/addBook")
	public BookVoResponse AddBook(@RequestBody AddBookVo vo) throws IOException {
		AddBookDto dto = new AddBookDto();
		BeanUtils.copyProperties(vo, dto);

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

	@CrossOrigin(maxAge = 3600)
	@GetMapping("/bookList")
	public List<BookVoResponse> getAllBook() {

		List<AddBookDto> listDto = Service.listOfBook();

		List<BookVoResponse> listVo = new ArrayList<>();

		for (AddBookDto dto : listDto) {

			BookVoResponse vo = new BookVoResponse();

			BeanUtils.copyProperties(dto, vo);

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

}
