package com.pravin.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import java.io.InputStream;
import java.util.Scanner;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import javax.mail.internet.MimeMessage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.apache.commons.net.ftp.FTPClient;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.apache.catalina.core.ApplicationContext;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.w3c.dom.Document;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.commons.codec.binary.Base64;

import com.pravin.model.ProductDetails;

//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pravin.model.UserAccountDTO;
import com.pravin.model.car_product;
import com.pravin.model.company;
import com.pravin.model.user;

@Repository("DaoOperation")
public class DaoOperationImpl implements DaoOperation{

	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	public void RegisterUser(int user_id,String username,String password,String active,String authority,String email) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  
		String encrypted = passwordEncoder.encode(password);  


		user u=new user();
		u.setUser_id(user_id);
		u.setUsername(username);
		u.setPassword(encrypted);
		u.setActive(active);
		u.setAuthority(authority);
		u.setEmail(email);

		
		Session session=sessionFactory.getCurrentSession();
		try{
			
		session.save(u);
		}catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
}
	@Override
	public boolean RoleBasedAccess(String name)throws Exception {

		Session session=sessionFactory.getCurrentSession();
		int id = (int) session.createQuery("select user.user_id from user user where user.username = :username").setString("username",name).uniqueResult();
		String role=(String) session.createQuery("select user1.authority from user_role user1 where user1.user_id = :user_id").setInteger("user_id",id).uniqueResult();
		if(role.equalsIgnoreCase("ROLE_ADMIN"))
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
		String query="from user";
		
		Session session=sessionFactory.getCurrentSession();
		Query q=session.createQuery(query);
		List<user> users=new ArrayList<user>();
		for(Iterator it=q.iterate();it.hasNext();)
		{
			user u=(user) it.next();
			u.getUser_id();
			u.getUsername();
			u.getPassword();
			u.getActive();
			System.out.println(u.getUsername());
			users.add(u);
		}
		return users;
		
	}
	@Override
	public Map<String,String> userdetails() throws Exception {
		
		
		Session session=sessionFactory.getCurrentSession();
		
		ProjectionList pl=Projections.projectionList();
		pl.add(Projections.property("user_id"));
		pl.add(Projections.property("username"));
		pl.add(Projections.property("password"));
		Criteria c=session.createCriteria(user.class);
		c.setProjection(pl);
		c.addOrder(Order.asc("user_id"));
		List<user> l=c.list();
		Iterator it=l.iterator();
		
		Map<String,String> m=new LinkedHashMap<String,String>();
		while(it.hasNext())
		{
			Object ob[]=(Object[])it.next();
			m.put(ob[1].toString(), ob[2].toString());
			System.out.println("username:"+ob[1]+"       password:"+ob[2]);
		}
		
		return m;
	}
	@Override
	public company companyby_username(String username) throws Exception {
		
		Session session=sessionFactory.getCurrentSession();
		int id = (int) session.createQuery("select user.user_id from user user where user.username = :username").setString("username",username).uniqueResult();
		company c=new company();
		String company_name=(String) session.createQuery("select comp.company_name from company comp where comp.user_id = :user_id").setInteger("user_id",id).uniqueResult();
		c.setCompany_name(company_name);
		return c;
	}
	
	@Override
	public List<company> retailer_list(String username) throws Exception {
		Session session=sessionFactory.getCurrentSession();
		Query q=session.createQuery("from company where company_type='retailer'");
		List<company> s=(List<company>)q.list();
		
		
		return s;
	}
	@Override
	public List<user> getUsersByIds(List<Integer> list1) {
		//String query="select * from USER where userId IN(:list)";
		//List<User> userlist =(List<User>) getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(query).setParameterList("list", list1).list();
		Session session=sessionFactory.getCurrentSession();
		Query q=session.createQuery("from user where user_id IN(:list)").setParameterList("list", list1);
		List<user> userlist=(List<user>)q.list();
		for(user u:userlist)
		{
			System.out.println(u.getUser_id());
			System.out.println(u.getUsername());
			System.out.println(u.getPassword());
		}
		return userlist;
	}
	@Override
	public List<company> getcompaniesByIds(List<Integer> complistids) {
		Session session=sessionFactory.getCurrentSession();
		Query q=session.createQuery("from company where user_id IN(:list) and status='active'").setParameterList("list", complistids);
		List<company> complist=(List<company>)q.list();
		for(company comp:complist)
		{
			System.out.println(comp.getCompany_id());
			System.out.println(comp.getCompany_name());
			System.out.println(comp.getStatus());
		}
		return complist;
	}
	@Override
	public void writedatatocsv(String csvFilePath){
		System.out.println("Prepare file row data. ");
		UserAccountDTO userDto1 = new UserAccountDTO(1, "jack", "jack123456789", "jack@gmail.com", "35", "12390973837");
		UserAccountDTO userDto2 = new UserAccountDTO(2, "jackie", "jackie123456789", "jackie@gmail.com", "38", "13901223456");
		UserAccountDTO userDto3 = new UserAccountDTO(3, "tom", "tom", "tom@gmail.com", "30", "12390973837");
		UserAccountDTO userDto4 = new UserAccountDTO(4, "steven", "steven22222", "steven@gmail.com", "37", "12390973837");
		UserAccountDTO userDto5 = new UserAccountDTO(5, "john", "john66366", "john@gmail.com", "50", "98979898576532");
		UserAccountDTO userDto6 = new UserAccountDTO(6, "harry", "harry123456789", "harry@gmail.com", "35", "12390973837");
		
		
		List<UserAccountDTO> userAccountList = new ArrayList<UserAccountDTO>();
		userAccountList.add(userDto1);
		userAccountList.add(userDto2);
		userAccountList.add(userDto3);
		userAccountList.add(userDto4);
		userAccountList.add(userDto5);
		userAccountList.add(userDto6);
		
		
		CSVFormat csvFormat = CSVFormat.DEFAULT.withRecordSeparator("\n");
		
		FileWriter fWriter = null;
		
		CSVPrinter csvPrinter = null;
		
		try
		{
			System.out.println("Prepare CSVPrinter object. ");
			
			fWriter = new FileWriter(csvFilePath);

			csvPrinter = new CSVPrinter(fWriter, csvFormat);
			
			System.out.println("Print header in file. ");
			csvPrinter.printRecord("Id", "UserName", "Password", "Email", "Age", "Mobile");

			
			System.out.println("Loop in the row list and print each row to csv file " + csvFilePath);
			
			for(int i=0; i<userAccountList.size(); i++)
			{
				UserAccountDTO userDto = userAccountList.get(i);
				
				List<String> rowDataList = new ArrayList<String>();
				rowDataList.add(String.valueOf(userDto.getId()));
				rowDataList.add(userDto.getUserName());
				rowDataList.add(userDto.getPassword());
				rowDataList.add(userDto.getEmail());
				rowDataList.add(userDto.getAge());
				rowDataList.add(userDto.getMobile());
				
				csvPrinter.printRecord(rowDataList);
			}
			
			System.out.println("Create file compelete successfully. ");
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			try
			{
				if(fWriter!=null)
				{
					fWriter.flush();
					fWriter.close();
				}
				
				if(csvPrinter!=null)
				{
					csvPrinter.close();
				}
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}

}
	@Override
	public List<List<String>> readdatafromcsv(String csvFilePath) {
List<List<String>> retList = new ArrayList<List<String>>();
		
		FileReader fReader = null;
		
		CSVParser csvParser = null;
		
		try
		{
			System.out.println("Prepare csv parser object. ");
			
			fReader = new FileReader(csvFilePath);

			CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader("Id", "UserName", "Password", "Email", "Age", "Mobile");
			
			csvParser = new CSVParser(fReader, csvFormat);
			
			List<CSVRecord> rowList = csvParser.getRecords();
			
			System.out.println("Loop in the csvrecords list from the second line data, and read each line from csv file " + csvFilePath);
			
			for(int i=1; i<rowList.size(); i++)
			{
				CSVRecord row = rowList.get(i);
				int id = Integer.parseInt(row.get("Id"));
				String userName = row.get("UserName");
				String password = row.get("Password");
				String email = row.get("Email");
				String age = row.get("Age");
				String mobile = row.get("Mobile");
				
				//UserAccountDTO userDto = new UserAccountDTO(id, userName, password, email, age, mobile);
				//System.out.println(userDto.toString());
				
				List<String> lineList = new ArrayList<String>();
				lineList.add(String.valueOf(id));
				lineList.add(userName);
				lineList.add(password);
				lineList.add(email);
				lineList.add(age);
				lineList.add(mobile);
				
				retList.add(lineList);
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}finally
		{
			try
			{
				if(fReader!=null)
				{
					fReader.close();
				}
				
				if(csvParser!=null)
				{
					csvParser.close();
				}
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
			
			return retList;
		}

	}
	@Override
	public void readftpdata() {
		String ftpUrl = "localhost";
		  int defaultPort = 21;

		  String user = "user1";
		  String pass = "pravin129";
		  
		   try {
		           
		           FTPClient ftpClient = new FTPClient();
		           ftpClient.connect(ftpUrl, defaultPort);
		           ftpClient.login(user, pass);
		           ftpClient.enterLocalPassiveMode();
		    
		           ftpClient.changeWorkingDirectory("/privatelink/test");
		           InputStream inputStream = ftpClient.retrieveFileStream("users.csv");
		           Scanner sc = new Scanner(inputStream);
		      
		           sc.useDelimiter(",");
		           
		           while (sc.hasNextLine()) 
		           //System.out.println(sc.nextLine());
		        	   System.out.print(sc.next() + "|");
		      
		           //Closing the channels
		           sc.close();
		           inputStream.close();
		   }
		  
		  catch (Exception e) 
		  {
		   e.printStackTrace();
		  } 
		
	}
	@Override
	public void readxmldata() {
		String ftpUrl = "localhost";
		  int defaultPort = 21;

		  String user = "user1";
		  String pass = "pravin129";
		  
		   try {
		           
		           FTPClient ftpClient = new FTPClient();
		           ftpClient.connect(ftpUrl, defaultPort);
		           ftpClient.login(user, pass);
		           ftpClient.enterLocalPassiveMode();
		    
		           
		           ftpClient.changeWorkingDirectory("/privatelink/test");
		           InputStream inputStream = ftpClient.retrieveFileStream("staff.xml");
		           DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		       		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		       		Document doc = dBuilder.parse(inputStream);
		       		doc.getDocumentElement().normalize();
		       		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		       		NodeList nList = doc.getElementsByTagName("staff");

		       		System.out.println("----------------------------");

		       		for (int temp = 0; temp < nList.getLength(); temp++) {

		       			Node nNode = nList.item(temp);

		       			System.out.println("\nCurrent Element :" + nNode.getNodeName());

		       			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

		       				Element eElement = (Element) nNode;

//		       				System.out.println("Staff id : " + eElement.getAttribute("id"));
//		       				System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
//		       				System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
//		       				System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
//		       				System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
		           
		   }
		
	}
		       		inputStream.close();
		   }catch (Exception e) 
			  {
			   e.printStackTrace();
			  } 
	}
	
	@Override
	public void readxlsdata() {
		String ftpUrl = "localhost";
		  int defaultPort = 21;

		  String user = "user1";
		  String pass = "pravin129";
		  
		   try {
		           
		           FTPClient ftpClient = new FTPClient();
		           ftpClient.connect(ftpUrl, defaultPort);
		           ftpClient.login(user, pass);
		           ftpClient.enterLocalPassiveMode();
		    
		           
		           ftpClient.changeWorkingDirectory("/privatelink/test");
		           InputStream inputStream = ftpClient.retrieveFileStream("SampleXLSFile.xls");
		           HSSFWorkbook wb = new HSSFWorkbook(inputStream);

		   		HSSFSheet sheet=wb.getSheetAt(0);
		   		HSSFRow row; 
		   		HSSFCell cell;

		   		Iterator rows = sheet.rowIterator();

		   		while (rows.hasNext())
		   		{
		   			row=(HSSFRow) rows.next();
		   			Iterator cells = row.cellIterator();
		   			
		   			while (cells.hasNext())
		   			{
		   				cell=(HSSFCell) cells.next();
		   		
		   				if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		   				{
		   					System.out.print(cell.getStringCellValue()+" | ");
		   				}
		   				else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		   				{
		   					System.out.print(cell.getNumericCellValue()+" | ");
		   				}
		   				else
		   				{
		   					//U Can Handel Boolean, Formula, Errors
		   				}
		   			}
		   			System.out.println();
		   		}
		   		
		       		inputStream.close();
		   }catch (Exception e) 
			  {
			   e.printStackTrace();
			  } 
		
	}
	
	@Override
	public boolean isuserActive(String username) {
		Session session=sessionFactory.getCurrentSession();
		String active = (String) session.createQuery("select user.active from user user where user.username = :username").setString("username",username).uniqueResult();
		if(active.trim().equals("1"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public company iscompanyexist(int id) {
		Session session=sessionFactory.getCurrentSession();
		company comp=(company) session.get(company.class, id);
		return comp;
	}
	
	@Override
	public void sendemail(final String emailSubject,final String emailMessage,final String emailToRecipient,final String emailFromRecipient,
			final CommonsMultipartFile attachFileObj, final JavaMailSender mailSenderObj) {
		
		mailSenderObj.send(new MimeMessagePreparator() {
			
            public void prepare(MimeMessage mimeMessage) throws Exception {

 

                MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");             

                mimeMsgHelperObj.setTo(emailToRecipient);

                mimeMsgHelperObj.setFrom(emailFromRecipient);               

                mimeMsgHelperObj.setText(emailMessage);

                mimeMsgHelperObj.setSubject(emailSubject);

 

                // Determine If There Is An File Upload. If Yes, Attach It To The Client Email              

                if ((attachFileObj != null) && (attachFileObj.getSize() > 0) && (!attachFileObj.equals(""))) {

                    System.out.println("\nAttachment Name?= " + attachFileObj.getOriginalFilename() + "\n");
                    
                    mimeMsgHelperObj.addAttachment(attachFileObj.getOriginalFilename(), new InputStreamSource() {                   

                        public InputStream getInputStream() throws IOException {

                            return attachFileObj.getInputStream();

                        }

                    });

                } else {

                    System.out.println("\nNo Attachment Is Selected By The User. Sending Text Email!\n");

                }

            }

        });
		
		System.out.println("\nMessage Send Successfully.... Hurrey!\n");
		
	}
	
	@Override
	public boolean imageinsert(String image_file,int image_id,String Status,String image_name)throws IOException {
		Session session=sessionFactory.getCurrentSession();
		File imagePath = new File(image_file); //here we given fully specified image path.
		  
		byte[] imageInBytes = new byte[(int)imagePath.length()]; //image convert in byte form
		String test=new String(imageInBytes);
		//System.out.println(test);
		byte[] encodedBytes = Base64.encodeBase64(test.getBytes());
		FileInputStream inputStream = new FileInputStream(imagePath);  //input stream object create to read the file
		inputStream.read(encodedBytes); // here inputstream object read the file
		inputStream.close();
		
		car_product cproduct=new car_product();
		cproduct.setImage(encodedBytes);
		cproduct.setImage_id(image_id);
		cproduct.setImage_name(image_name);
		cproduct.setProduct_status(Status);
		
		
		session.save(cproduct);
		return true;
	}
	
	@Override
	public List<car_product> getimages(String carname) {
		System.out.println(carname);
		Session session=sessionFactory.getCurrentSession();
//		List<car_product> car = session.createQuery("from car_product car where car.image_name like '%:car1%'").setString("car1",carname).list();
		List<car_product> car=session.createQuery("From car_product as rb where rb.image_name  like ?").setString(0, "%"+carname+"%").list();
		System.out.println("car list size"+car.size());
		return car;
	}
	
	@Override
	public car_product productbyimageid(int imageid) {
		Session session=sessionFactory.getCurrentSession();
		car_product c_prod=(car_product) session.get(car_product.class, imageid);
		return c_prod;
	}
	
	@Override
	public ProductDetails getproductbyid(int id) {
		Session session=sessionFactory.getCurrentSession();
		ProductDetails c_prod=(ProductDetails) session.get(ProductDetails.class, id);
		return c_prod;
	}
	
	@Override
	public boolean AddNewProduct(String product_name, String product_status, Float product_price,
			String product_specifications) {
		
		ProductDetails c_details=new ProductDetails();
		c_details.setProduct_name(product_name);
		c_details.setProduct_status(product_status);
		c_details.setProduct_price(product_price);
		c_details.setProduct_specifications(product_specifications);

		Session session=sessionFactory.getCurrentSession();
		try{
			
		session.save(c_details);
		return true;
		}catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
		
	}
	@Override
	public boolean UpdateProduct(String product_name, String product_status, Float product_price,
			String product_specifications) {
		ProductDetails c_details=new ProductDetails();
		c_details.setProduct_name(product_name);
		c_details.setProduct_status(product_status);
		c_details.setProduct_price(product_price);
		c_details.setProduct_specifications(product_specifications);

		Session session=sessionFactory.getCurrentSession();
		try{
			
		session.saveOrUpdate(c_details);
		return true;
		}catch(Exception e)
		{
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
	}
}

