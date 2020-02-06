package com.wadi.dto;

import org.springframework.web.multipart.MultipartFile;

import com.wadi.bo.Author;
import com.wadi.bo.Category;

public class AddBookDto {

	private long id;

	private String title;

	private String imageUrl;

	private String bookUrl;

	private Author author;

	private Category category;

	private String discription;

	private MultipartFile book;

	private MultipartFile image;

	private byte[] bookContent;

	private byte[] imgeContent;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getBookUrl() {
		return bookUrl;
	}

	public void setBookUrl(String bookUrl) {
		this.bookUrl = bookUrl;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public MultipartFile getBook() {
		return book;
	}

	public void setBook(MultipartFile book) {
		this.book = book;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public byte[] getBookContent() {
		return bookContent;
	}

	public void setBookContent(byte[] bookContent) {
		this.bookContent = bookContent;
	}

	public byte[] getImgeContent() {
		return imgeContent;
	}

	public void setImgeContent(byte[] imgeContent) {
		this.imgeContent = imgeContent;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
