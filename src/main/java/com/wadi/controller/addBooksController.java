package com.wadi.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.wadi.bo.addBookBo;
import com.wadi.dao.booksRepository;
import com.wadi.dto.AddBookDto;
import com.wadi.file.UploadServiceInterface;
import com.wadi.response.BookVoResponse;
import com.wadi.service.addBooksServiceInt;
import com.wadi.vo.AddBookVo;

@Controller
public class addBooksController {

	@Autowired
	private addBooksServiceInt booksServiece;

	@Autowired
	private UploadServiceInterface upload;

	private static final String Uploadpath = "books";
	private static final String imageUploadpath = "books/bookImages";

	@GetMapping("/AddBook")
	public String AddBook(@ModelAttribute("addfrm") AddBookVo vo, BindingResult result, Model model) {

		System.out.println("WelecomeController.AddBook()");

		return "AddBook";
	}

	@PostMapping("/AddBook")
	public BookVoResponse AddBookOperation(@ModelAttribute("addfrm") AddBookVo vo,
			@RequestParam("book") MultipartFile book, @RequestParam("image") MultipartFile image, BindingResult result,
			Model model, HttpSession session) throws IOException {

		AddBookDto dto = new AddBookDto();
		BeanUtils.copyProperties(vo, dto);

		dto.setBook(book);
		dto.setImage(image);
		AddBookDto resDto = booksServiece.addBookService(dto);

		BookVoResponse resVo = new BookVoResponse();
		BeanUtils.copyProperties(resDto, resVo);

		return resVo;
	}

	@PostMapping("/Edit")
	public String addEdit(@ModelAttribute("addbookfrm") AddBookDto bookDto) {
		AddBookDto dto = new AddBookDto();

		String msg;
		System.out.println(bookDto.getImgeContent() + "addBooksController.addEdit()" + bookDto.getImgeContent());

		try {
			dto = booksServiece.addBookService(bookDto);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "success";

	}

	@GetMapping("/EditBook")
	public String EditBook(@ModelAttribute("addbookfrm") AddBookDto bookDto, HttpServletRequest req,
			HttpSession session) {
		int id = Integer.parseInt(req.getParameter("id"));

		AddBookDto dto = booksServiece.findBookByid(id);

		bookDto.setTitle(dto.getTitle());
		bookDto.setAuthor(dto.getAuthor());
		bookDto.setDiscription(dto.getDiscription());
		bookDto.setImgeContent(dto.getImgeContent());
		bookDto.setBookContent(dto.getBookContent());

		return "edit-book";

	}

	@PostMapping("/EditBook")
	public String saveEditBook(@ModelAttribute("addbookfrm") AddBookDto bookDto) {

		try {
			AddBookDto dto = booksServiece.addBookService(bookDto);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";

	}

	@GetMapping("/ReadBook")
	public String ReadBook(HttpServletRequest req, HttpSession session) throws IOException {

		String bookUrl = req.getParameter("bookUrl");
		AddBookDto dto = upload.findBook(bookUrl);

		session.setAttribute("bookfile", dto.getBookContent());

		return "pdfViewer";

	}

	@GetMapping("/DownloadBook")
	public String DownloadBook(HttpServletRequest req, HttpSession session) throws IOException {

		String bookUrl = req.getParameter("bookUrl");
		AddBookDto dto = upload.findBook(bookUrl);

		session.setAttribute("bookfile", dto.getBookContent());

		return "pdfDownload";

	}

}
