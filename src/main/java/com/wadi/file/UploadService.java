package com.wadi.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.wadi.dto.AddBookDto;

@Component
public class UploadService implements UploadServiceInterface {

	private static final String Uploadpath = "books";
	private static final String imageUploadpath = "books/bookImages";

	public void upload(AddBookDto dto) throws IOException {

		File uploadFileDir, uploadImageDir;
		String bookName, imageName, bookRename, imageRename;
		InputStream is1, is2;
		OutputStream os1, os2;
		String title = null;

		title = dto.getTitle().trim().concat(" by " + dto.getAuthor().trim());

		dto.setBookUrl(title);
		dto.setImageUrl(title);

		// Locate folder on server machine file system
		uploadFileDir = new File(Uploadpath);
		uploadImageDir = new File(imageUploadpath);

		byte[] imagefile = new byte[(int) uploadImageDir.length()];

		// upload image file
		MultipartFile image = dto.getImage();
		MultipartFile book = dto.getBook();

		// get names of the uploaded files
		bookName = book.getOriginalFilename();

		// get name of uploaded image
		imageName = image.getOriginalFilename();
		// Rename the file
		bookRename = bookName.replace(bookName, dto.getBookUrl());

		// rename image
		imageRename = imageName.replace(imageName, dto.getImageUrl());

		// create InputStream pointing to the uploaded files
		is1 = book.getInputStream();

		is2 = image.getInputStream();

		if (!uploadFileDir.exists())
			uploadFileDir.mkdir();

		if (!uploadImageDir.exists())
			uploadImageDir.mkdir();

		// create OutputStream pointing to dest files
		os1 = new FileOutputStream(Uploadpath + "/" + bookRename.concat(".pdf"));
		os2 = new FileOutputStream(imageUploadpath + "/" + imageRename.concat(".jpg"));

		// complete file uploading1

		IOUtils.copy(is1, os1);
		IOUtils.copy(is2, os2);

		is1.close();
		os1.close();
		is2.close();
		os2.close();

	}

	public AddBookDto findBook(String book) throws IOException {
		byte[] bFile = null;
		File bookDir = null;
		FileInputStream fileInputStream = null;

		// locate the file directory
		bookDir = new File(Uploadpath + "/" + book + ".pdf");
		// specify the lenth of the file
		bFile = new byte[(int) bookDir.length()];

		try {
			// read the file and converted into byte stream
			fileInputStream = new FileInputStream(bookDir);
			// covert the byte stream into the byte[]
			fileInputStream.read(bFile);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AddBookDto dto = new AddBookDto();

		dto.setBookContent(bFile);

		fileInputStream.close();

		return dto;

	}

	public AddBookDto findimage(String image) throws IOException {
		byte[] imageFile = null;
		File imageDir = null;
		FileInputStream fileInputStream = null;

		// locate the file directory
		imageDir = new File(imageUploadpath + "/" + image + ".jpg");
		// specify the lenth of the file
		imageFile = new byte[(int) imageDir.length()];

		try {
			// read the file and converted into byte stream
			fileInputStream = new FileInputStream(imageDir);
			// covert the byte stream into the byte[]
			fileInputStream.read(imageFile);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AddBookDto dto = new AddBookDto();

		dto.setImgeContent(imageFile);

		return dto;

	}

	public String deleteUrl(String fileUrl) throws IOException {
		File uploadFileDir, uploadImageDir;

		uploadFileDir = new File(Uploadpath + "" + fileUrl + ".pdf");
		uploadImageDir = new File(imageUploadpath + "" + fileUrl + ".jpg");

		if (uploadFileDir.delete() && uploadImageDir.delete()) {

			return "Success";
		}

		else {
			return "Fail";
		}

	}

	@Override
	public void editFile(String oldUrl, String newUrl) {

		File uploadFileDir, uploadImageDir;

		uploadFileDir = new File(Uploadpath + "" + oldUrl + ".pdf");
		uploadImageDir = new File(imageUploadpath + "" + oldUrl + ".jpg");

		File RFileDir = new File(Uploadpath + "" + newUrl + ".pdf");
		File RImageDir = new File(imageUploadpath + "" + newUrl + ".jpg");

		uploadImageDir.renameTo(RImageDir);
		uploadFileDir.renameTo(RFileDir);

	}

}
