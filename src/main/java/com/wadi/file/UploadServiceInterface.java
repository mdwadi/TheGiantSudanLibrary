package com.wadi.file;

import java.io.IOException;
import java.util.Set;

import com.wadi.bo.addBookBo;
import com.wadi.dto.AddBookDto;
import java.util.List;


public interface UploadServiceInterface {

	public void upload(AddBookDto dto) throws IOException;

	public AddBookDto findBook(String book) throws IOException;

	public AddBookDto findimage(String image) throws IOException;

	public String deleteUrl(String fileUrl) throws IOException;

	public void editFile(String oldUrl, String newUrl);

	public List<AddBookDto> findListimage(List<addBookBo> listBo) throws IOException;

}
