package com.pravin.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.pravin.dao.DaoOperation;
import com.pravin.model.ProductDetails;
import com.pravin.model.car_product;
import com.pravin.model.company;
import com.pravin.model.user;

@Service("articleService")
@Transactional
public class ServiceOperationImpl implements ServiceOperation{

	@Autowired
	private DaoOperation dao;
	
	public ServiceOperationImpl()
	{
		
	}
	
	@Override
	//@Transactional
	public void RegisterUser(int user_id,String username, String password, String active,String authority,String email) throws Exception {
		dao.RegisterUser(user_id,username, password, active,authority,email);
		
	}

	@Override
	public boolean RoleBasedAccess(String name)throws Exception  {
		boolean result=dao.RoleBasedAccess(name);
		if(result==true)
		{
		return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public List<user> getallusers() throws Exception {
		List<user> users1=new ArrayList<user>();
		users1=dao.getallusers();
		return users1;
	}

	@Override
	public Map<String,String> userdetails() throws Exception {
		Map<String,String> m=dao.userdetails();
		return m;
		
	}

	@Override
	public company companyby_username(String username) throws Exception {
		company c=null;
		c=dao.companyby_username(username);
		return c;
	}

	@Override
	public List<company> retailer_list(String username) throws Exception {
		List<company> s=dao.retailer_list(username);
		return s;
	}

	@Override
	public List<user> getUsersByIds(List<Integer> list1) {
		List<user> userlist=dao.getUsersByIds(list1);
		return userlist;
	}

	@Override
	public List<company> getcompaniesByIds(List<Integer> complistids) {
		List<company> comlist=dao.getcompaniesByIds(complistids);
		return comlist;
	}

	@Override
	public void writedatatocsv(String csvFilePath) {
		dao.writedatatocsv(csvFilePath);
		
	}

	@Override
	public List<List<String>> readdatafromcsv(String csvFilePath) {
		List<List<String>> datastr=dao.readdatafromcsv(csvFilePath);
		return datastr;
	}

	@Override
	public void readftpdata() {
		dao.readftpdata();
		
	}

	@Override
	public void readxmldata() {
		dao.readxmldata();
		
	}

	@Override
	public void readxlsdata() {
		dao.readxlsdata();
		
	}

	@Override
	public boolean isuserActive(String username) {
		boolean active=dao.isuserActive(username);
		if(active==true)
		{
			System.out.println("logged in user is active");
			return true;
		}
		else
		{
			System.out.println("logged in user is not active");
			return false;
		}
	}

	@Override
	public company iscompanyexist(int id) {
		company comp=dao.iscompanyexist(id);
		return comp;
	}

	@Override
	public void sendemail(String emailSubject, String emailMessage, String emailToRecipient, String emailFromRecipient,
			CommonsMultipartFile attachFileObj, JavaMailSender mailSenderObj) {
		dao.sendemail(emailSubject, emailMessage, emailToRecipient, emailFromRecipient, attachFileObj, mailSenderObj);
		
	}

	@Override
	public boolean imageinsert(String image_file,int image_id,String Status,String image_name) throws IOException {
		boolean insert=false;
		insert=dao.imageinsert(image_file,image_id,Status,image_name);
		return insert;
		
	}
	
	@Override
	public List<car_product> getimages(String car_name) {
		List<car_product> finalList = new ArrayList<car_product>();
        for (car_product car : dao.getimages(car_name)) {
            car.setEncodedImage(new String(Base64.encodeBase64(car.getImage())));
            System.out.println(car.getImage_name());
            finalList.add(car);
        }
        return finalList;
	}

	@Override
	public car_product productbyimageid(int imageid) {
		car_product c_prod=dao.productbyimageid(imageid);
		return c_prod;
	}

	@Override
	public ProductDetails getproductbyid(int id) {
		ProductDetails c_details=dao.getproductbyid(id);
		return c_details;
	}

	@Override
	public boolean AddNewProduct(String product_name, String product_status, Float product_price,
			String product_specifications) {
		boolean newprod=dao.AddNewProduct(product_name, product_status, product_price, product_specifications);
		return newprod;
	}

	@Override
	public boolean UpdateProduct(String product_name, String product_status, Float product_price,
			String product_specifications) {
		// TODO Auto-generated method stub
		return false;
	}
}
