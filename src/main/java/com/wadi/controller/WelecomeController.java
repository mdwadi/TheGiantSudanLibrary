package com.wadi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.wadi.dto.AddBookDto;
import com.wadi.service.addBooksServiceInt;

@Controller
public class WelecomeController {

	@Autowired
	private addBooksServiceInt booksServiece;

	@PostMapping("searchbook")
	public String searchBook(@ModelAttribute("searchfrm") AddBookDto bookDto) {
		System.out.println("WelecomeController.searchBook()");
		return "searchbook";

	}

	@GetMapping("/home")
	public String welecome(Model model) throws IOException {

		// List<AddBookDto> Listdto = booksServiece.listOfBook();

		List<AddBookDto> listDto = booksServiece.listOfBook();

		model.addAttribute("listDto", listDto);

		return "home";
	}

	@GetMapping("/aboutUs")
	public String aboutUs() {

		return "aboutUs";
	}

	@GetMapping("/listBook")
	public String BookList(Model model) throws IOException {

		List<AddBookDto> listDto = booksServiece.listOfBook();


		model.addAttribute("listDto", listDto);
		return "bookList";
	}

	@GetMapping("/stories")
	public String SoriesPage() {

		return "stories";
	}

}
