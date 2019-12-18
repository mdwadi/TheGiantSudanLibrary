package com.wadi.vo;

import javax.persistence.Lob;

import org.springframework.web.multipart.MultipartFile;

public class AddBookVo {
	
	private long id;

	private String title;

	private String imageUrl;

	private String bookUrl;

	private String author;
	
	private String discription;
	
	private MultipartFile book;

	private MultipartFile image;

	private byte[] filecontent;

	private byte[] imgfile;

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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
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

	public byte[] getFilecontent() {
		return filecontent;
	}

	public void setFilecontent(byte[] filecontent) {
		this.filecontent = filecontent;
	}

	public byte[] getImgfile() {
		return imgfile;
	}

	public void setImgfile(byte[] imgfile) {
		this.imgfile = imgfile;
	}
	

}
