package com.pravin.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.pravin.model.ProductDetails;
import com.pravin.model.car_product;
import com.pravin.model.company;
import com.pravin.model.user;

public interface ServiceOperation {

	public void RegisterUser(int user_id,String username,String password,String active,String authority,String email)throws Exception;
	public boolean RoleBasedAccess(String name)throws Exception;
	public List<user> getallusers()throws Exception;
	public Map<String,String> userdetails()throws Exception;
	public company companyby_username(String username)throws Exception;
	public List<company> retailer_list(String username)throws Exception;
	public List<user> getUsersByIds(List<Integer> list1);
	public List<company> getcompaniesByIds(List<Integer> complistids);
	public void writedatatocsv(String csvFilePath);
	public List<List<String>> readdatafromcsv(String csvFilePath);
	public void readftpdata();
	public void readxmldata();
	public void readxlsdata();
	public boolean isuserActive(String username);
	public company iscompanyexist(int id);
	public void sendemail(final String emailSubject,final String emailMessage,final String emailToRecipient,final String emailFromRecipient,final CommonsMultipartFile attachFileObj,final JavaMailSender mailSenderObj);
	public boolean imageinsert(String image_file,int image_id,String Status,String image_name)throws IOException;
	public List<car_product> getimages(String carname);
	public car_product productbyimageid(int imageid);
	public ProductDetails getproductbyid(int id);
	public boolean AddNewProduct(String product_name,String product_status,Float product_price,String product_specifications);
	public boolean UpdateProduct(String product_name,String product_status,Float product_price,String product_specifications);
}
