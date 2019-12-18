package com.wadi.file;

import java.io.IOException;

import com.wadi.dto.AddBookDto;

public interface UploadServiceInterface {

	public void upload(AddBookDto dto) throws IOException;

	public AddBookDto findBook(String book) throws IOException;

	public AddBookDto findimage(String image) throws IOException;

	public String deleteUrl(String fileUrl) throws IOException;

	public void editFile(String oldUrl, String newUrl);

}
