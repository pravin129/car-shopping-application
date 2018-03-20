package com.pravin.controller;

import java.security.Key;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import java.io.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;

import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwa.AlgorithmConstraints.ConstraintType;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.keys.AesKey;
import org.jose4j.lang.ByteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pravin.dao.DaoOperation;
import com.pravin.dao.DaoOperationImpl;
import com.pravin.model.ProductDetails;
import com.pravin.model.car_product;
import com.pravin.model.company;
import com.pravin.model.user;
import com.pravin.service.ServiceOperation;

import sun.org.mozilla.javascript.internal.Context;
import net.tanesha.recaptcha.*;

@Controller
public class LoginController  {

	static String emailToRecipient, emailSubject, emailMessage;
	static final String emailFromRecipient = "pravinghadge065@gmail.com";
	@Autowired
    private ReCaptcha reCaptchaService = null;
	
	String option="";
	String car_name="";
	static int id1=0;
	ProductDetails c_details=null;
	
	List<car_product> car=null;
	@Autowired
	private JavaMailSender mailSenderObj;

	@Autowired
	private ServiceOperation service;

	public ProductDetails getC_details() {
		return c_details;
	}


	public void setC_details(ProductDetails c_details) {
		this.c_details = c_details;
	}

	public static int getId1() {
		return id1;
	}


	public static void setId1(int id1) {
		LoginController.id1 = id1;
	}
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String executeSecurity(ModelMap model, Principal principal )throws Exception {
		String name = principal.getName();
		boolean i=service.RoleBasedAccess(name);
		if(i==true)//for admin
		{
		model.addAttribute("Admin", name);
		model.addAttribute("message", "welcome to the admin page");
		
		return "welcomeadmin";
		}
		else//for user
		{
			model.addAttribute("User", name);
			model.addAttribute("message1", "welcome to the users car world");
			return "user";
		}

	}


	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model) {


		return "login";
	}
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView register() {

		ModelAndView mav=new ModelAndView("Registrationform");
		return mav;
	}
	@RequestMapping(value="/registration", method=RequestMethod.POST)
	public ModelAndView submitRegistrationForm(@ModelAttribute("user1") user User)throws Exception
	{				
		
		int user_id=User.getUser_id();
		String username=User.getUsername();
		String password=User.getPassword();
		String active=User.getActive();
		String authority=User.getAuthority();
		String email=User.getEmail();

		
		service.RegisterUser(user_id,username,password,active,authority,email);

		ModelAndView mav=new ModelAndView("Registrationsuccess");
		 
			 return mav;
	}
	
	/*@RequestMapping(value="/admin123", method = RequestMethod.POST)
	public ModelAndView admin(Principal principal)throws Exception {

		String name=principal.getName();
		
		boolean i=service.RoleBasedAccess(name);
		if(i==true)
		{
			ModelAndView mav=new ModelAndView("welcomeadmin");
			return mav;
		}
		else
		{
			ModelAndView mav=new ModelAndView("user");

			return mav;
		}
	}
*/
	@RequestMapping(value="/admin123/managedata", method = RequestMethod.POST)
	public ModelAndView listuser(HttpServletRequest req,Model m)throws Exception {

		String request=req.getParameter("action");
		if(request.equalsIgnoreCase("car_productimage_management"))
		{
			ModelAndView mav=new ModelAndView("car_product_management");
			return mav;
		}
		else if(request.equalsIgnoreCase("show_user_list"))
		{
		List<user> users1=new ArrayList<user>();
		users1=service.getallusers();
		m.addAttribute("users1",users1);
		ModelAndView mav=new ModelAndView("userlist");
		return mav;
		}
		else if(request.equalsIgnoreCase("add_new_product"))
		{
			ModelAndView mav=new ModelAndView("add_new_product");
			return mav;
		}
		else
		{
			ModelAndView mav=new ModelAndView("update_product");
			return mav;
		}
	}


	@RequestMapping(value="/fail2login", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {

		model.addAttribute("error", "true");
		return "login";

	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {

		return "login";

	}
	
	

	@RequestMapping(value="/index/userlist", method = RequestMethod.GET)
	public ModelAndView nonsecure(Model m)throws Exception {

		List<user> users1=new ArrayList<user>();
		users1=service.getallusers();
		m.addAttribute("users1",users1);
		ModelAndView mav=new ModelAndView("userlist");
		return mav;
	}

	@RequestMapping(value="/index/log", method = RequestMethod.GET)
	public ModelAndView log1()throws Exception {

		
		ModelAndView mav=new ModelAndView("log");
		return mav;
	}

	@RequestMapping(value="index/log/userdetails", method=RequestMethod.POST)
	public ModelAndView userdetails(Model m)throws Exception
	{
		Map<String,String> map=service.userdetails();
		m.addAttribute("userdetails", map);
		ModelAndView mav=new ModelAndView("userdetails");
		return mav;
	}
	
	@RequestMapping(value="/company" , method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView findCompanybyuser_id(HttpServletRequest req)throws Exception
	{
		
		option=req.getParameter("car_company");
		System.out.println(option);
		
		if(option.equalsIgnoreCase("TATA"))
		{
		ModelAndView mav=new ModelAndView("TATA");
		return mav;
		}
		else if(option.equalsIgnoreCase("mahindra"))
		{
		ModelAndView mav=new ModelAndView("mahindra");
		return mav;
		}
		else if(option.equalsIgnoreCase("maruti_suzuki"))
		{
		ModelAndView mav=new ModelAndView("suzuki");
		return mav;
		}
		else
		{
			ModelAndView mav=new ModelAndView("volvo");
			return mav;
		}
		
	}
	
	
	@RequestMapping(value="/index/retailer" , method=RequestMethod.GET)
	public ModelAndView retailer_list(Principal p,Model m)throws Exception
	{
		String username=p.getName();
		List<company> s=service.retailer_list(username);
		m.addAttribute("retailer", s);
		ModelAndView mav=new ModelAndView("retailerlist");
		return mav;
		
	}
	
	@RequestMapping(value="/index/isuserActive" , method=RequestMethod.GET)
	public ModelAndView isActiveuser(Principal p)
	{
		boolean active=false;
		String username=p.getName();
		active=service.isuserActive(username);
		ModelAndView mav=new ModelAndView();
		return mav;
	}
	
	@RequestMapping(value="/index/iscompanyexist/{company}" , method=RequestMethod.GET)
	public ModelAndView isexistcompany(@PathVariable String company)
	{
		company comp=service.iscompanyexist(Integer.parseInt(company));
		if(comp!=null)
		{
			System.out.println(comp.getCompany_name());
			System.out.println("company exist");
		}
		else
		{
			System.out.println("company not exist");
		}
		ModelAndView mav=new ModelAndView();
		return mav;
	}
	
	@RequestMapping(value ="/services/getUsersByIds", method = RequestMethod.GET)
    public ModelAndView getUsersByIds() 
	{
		ModelAndView mav=new ModelAndView();
		List<Integer> list1=new ArrayList<Integer>();
		List<String> list=null;
		String userid="1,2,3,5";
		if((userid!=null)&&(!userid.trim().equals("")))
		{
		list = new ArrayList<String>(Arrays.asList(userid.split(",")));
		}
		if((list!=null)&&(list.size()>0))
		{
			for(String str:list)
			{
				list1.add(Integer.parseInt(str));
			}
		}
		if((list1!=null)&&(list1.size()>0))
		{
		service.getUsersByIds(list1);
		}
		return mav;
	}
	
	@RequestMapping(value = "/services/getcompaniesByIds", method = RequestMethod.GET)
    public ModelAndView getcompaniesByIds() 
    {
    	ModelAndView mav=new ModelAndView();
    	Map<String, Object> model = new HashMap<String, Object>();
    	List<company> complist=null;
    	List<String> complistid=null;
    	List<Integer> complistids=new ArrayList<Integer>();
    	String compid="1,2,4";
    	if((compid!=null)&&(!compid.trim().equals("")))
    	{
    		complistid = new ArrayList<String>(Arrays.asList(compid.split(",")));
    	}
    	if((complistid!=null)&&(complistid.size()>0))
    	{
    		for(String str:complistid)
    		{
    			complistids.add(Integer.parseInt(str));
    		}
    	}
    	if((complistids!=null)&&(complistids.size()>0))
    	{
    		complist=service.getcompaniesByIds(complistids);
    	}
    	return mav;
    }
	
	@RequestMapping(value="/index/writedata", method=RequestMethod.GET)
	public ModelAndView writecsvfile()
	{
		final String csvFilePath = "C:Users/Asus/workspace2/Spring_Assignment_using_Hibernate/WebContent/users.csv";
		service.writedatatocsv(csvFilePath);
		ModelAndView mav=new ModelAndView();
		return mav;
	}
	
	@RequestMapping(value="/index/readdata", method=RequestMethod.GET)
	public ModelAndView readcsvfile()
	{
		final String csvFilePath = "C:Users/Asus/workspace2/Spring_Assignment_using_Hibernate/WebContent/users.csv";
		List<List<String>> datastr=service.readdatafromcsv(csvFilePath);
		for(int i=0;i<datastr.size();i++)
		{
			System.out.println(datastr.get(i));
		}
		ModelAndView mav=new ModelAndView();
		return mav;
	}
	
	@RequestMapping(value="/index/readcsvftp", method=RequestMethod.GET)
	public ModelAndView readfilefromftp()
	{
		service.readftpdata();
		ModelAndView mav=new ModelAndView();
		return mav;
	}
	
	@RequestMapping(value="/index/readxmlftp", method=RequestMethod.GET)
	public ModelAndView readxmlftp()
	{
		service.readxmldata();
		ModelAndView mav=new ModelAndView();
		return mav;
	}
	
	@RequestMapping(value="/index/readxlsftp", method=RequestMethod.GET)
	public ModelAndView readxlsftp()
	{
		service.readxlsdata();
		ModelAndView mav=new ModelAndView();
		return mav;
	}
	
	
	@RequestMapping(value ="/index/emailform", method = RequestMethod.GET)
	
	    public ModelAndView showEmailForm(ModelMap model) {
		ModelAndView mav=new ModelAndView();
	        mav = new ModelAndView("emailForm");
	
	        return  mav;       
	
	    }
	
	 
	
	    // This Method Is Used To Prepare The Email Message And Send It To The Client
	
	    @RequestMapping(value ="emailform/sendEmail", method = RequestMethod.POST)
	
	    public ModelAndView sendEmailToClient(HttpServletRequest request, final @RequestParam CommonsMultipartFile attachFileObj) {
	
	 
	
	        // Reading Email Form Input Parameters      
	    	emailSubject = request.getParameter("subject");
	
	        emailMessage = request.getParameter("message");
	
	        emailToRecipient = request.getParameter("mailTo");
	
	        System.out.println("\nReceipient?= " + emailToRecipient + ", Subject?= " + emailSubject + ", Message?= " + emailMessage + "\n");
	        ModelAndView mav=new ModelAndView();
	        service.sendemail(emailSubject,emailMessage,emailToRecipient,emailFromRecipient,attachFileObj,mailSenderObj);
	
	        mav = new ModelAndView("success","messageObj","Thank You! Your Email Has Been Sent!");
	
	        return  mav;   
	
	    }
	    
	   
	    /* jose4j (javascript object signin and encryption) library used for message encryption*/
	    @RequestMapping(value ="/index/msgencryption", method = RequestMethod.GET)
		public ModelAndView messageencryption(HttpServletRequest req) {
	    	ModelAndView mav=new ModelAndView();
	    	try
	    	{
	    	Key key = new AesKey(ByteUtil.randomBytes(16));
	    	 JsonWebEncryption jwe = new JsonWebEncryption();
	    	 jwe.setPayload("Hello World!");
	    	 jwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.A128KW);
	    	 jwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256);
	    	 jwe.setKey(key);
	    	 String serializedJwe = jwe.getCompactSerialization();
	    	 System.out.println("Serialized Encrypted JWE: " + serializedJwe);
	    	 jwe = new JsonWebEncryption();
	    	 jwe.setAlgorithmConstraints(new AlgorithmConstraints(ConstraintType.WHITELIST,KeyManagementAlgorithmIdentifiers.A128KW));
	    	 jwe.setContentEncryptionAlgorithmConstraints(new AlgorithmConstraints(ConstraintType.WHITELIST,ContentEncryptionAlgorithmIdentifiers.AES_128_CBC_HMAC_SHA_256));
	    	 jwe.setKey(key);
	    	 jwe.setCompactSerialization(serializedJwe);
	    	 System.out.println("Payload: " + jwe.getPayload());
	    	}catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	        return  mav;       
	
	    }
	    
	    
	    @RequestMapping(value ="/frontpage", method = RequestMethod.GET)
		public ModelAndView multipalaction(HttpServletRequest req)throws Exception {
		ModelAndView mav=new ModelAndView();
	        
	        String action=req.getParameter("action");
	        System.out.println(action);
	        if(action.equalsIgnoreCase("register"))
	        {
	        	mav=new ModelAndView("Registrationform");
	    		return mav;
	        }
	        if(action.equalsIgnoreCase("login"))
	        {
	        	mav=new ModelAndView("captcha");
	    		return mav;
	        	
	        }
	        return mav;  
	
	    }
	    
	    @RequestMapping(value="/select_car" , method={RequestMethod.GET,RequestMethod.POST})
		public ModelAndView selectcar(HttpServletRequest req,Model m)throws Exception
		{
	    	 car_name=req.getParameter("car_type");
	    	car=service.getimages(car_name);
	    	m.addAttribute("car_product",car);
	    	m.addAttribute("carname",car_name);
	    	ModelAndView mav;
			if(option.equalsIgnoreCase("TATA"))
			{
				if(car_name.equalsIgnoreCase("vista"))
				{
					mav=new ModelAndView("car_models");
					return mav;
				}
				else if(car_name.equalsIgnoreCase("TataHexa"))
				{
					mav=new ModelAndView("car_models");
					return mav;
				}
				else if(car_name.equalsIgnoreCase("nano"))
				{
					mav=new ModelAndView("car_models");
					return mav;
				}
				else
				{
					mav=new ModelAndView("car_models");
					return mav;
				}
			}
			else if(option.equalsIgnoreCase("mahindra"))
			{
				if(car_name.equalsIgnoreCase("MahindraTUV"))
				{
					mav=new ModelAndView("car_models");
					return mav;
				}
				else if(car_name.equalsIgnoreCase("MahindraThar"))
				{
					mav=new ModelAndView("car_models");
					return mav;
				}
				else if(car_name.equalsIgnoreCase("MahindraScorpio"))
				{
					mav=new ModelAndView("car_models");
					return mav;
				}
				else
				{
					mav=new ModelAndView("car_models");
					return mav;
				}
			}
			else if(option.equalsIgnoreCase("maruti_suzuki"))
			{
				if(car_name.equalsIgnoreCase("MarutiSuzuki_swift"))
				{
					mav=new ModelAndView("car_models");
					return mav;
				}
				else if(car_name.equalsIgnoreCase("MarutiSuzuki_breeza"))
				{
					mav=new ModelAndView("car_models");
					return mav;
				}
				else if(car_name.equalsIgnoreCase("MarutiSuzuki_dzire"))
				{
					mav=new ModelAndView("car_models");
					return mav;
				}
				else
				{
					mav=new ModelAndView("car_models");
					return mav;
				}
			}
			else
			{
				if(car_name.equalsIgnoreCase("volvo_xc90"))
				{
					mav=new ModelAndView("car_models");
					return mav;
				}
				else if(car_name.equalsIgnoreCase("volvo_xc60"))
				{
					mav=new ModelAndView("car_models");
					return mav;
				}
				else if(car_name.equalsIgnoreCase("volvo_v90"))
				{
					mav=new ModelAndView("car_models");
					return mav;
				}
				else
				{
					mav=new ModelAndView("car_models");
					return mav;
				}
			}
			
		}
	    
	    //api to save image into database
	    @RequestMapping(value ="/admin123/insertimages", method = RequestMethod.POST)
		public ModelAndView insertimagesintodb(HttpServletRequest req,ModelMap model)throws IOException {
		ModelAndView mav=new ModelAndView();
			String image_file=req.getParameter("select_file");
			int image_id=Integer.parseInt(req.getParameter("image_id"));
			String Status=req.getParameter("product_status");
			String image_name=req.getParameter("image_name");
			System.out.println(image_file);
			boolean insert=service.imageinsert(image_file,image_id,Status,image_name);
		
			if(insert==true)
			{
				mav = new ModelAndView("imageinsertsuccessful");
			}
			else
			{
				mav=new ModelAndView("imageinserterror");
			}
	        return  mav;       
	
	    }
	    
	    @RequestMapping(value ="/admin123/addproduct", method = RequestMethod.POST)
		public ModelAndView addnewproduct(HttpServletRequest req,ModelMap model)throws IOException {
		ModelAndView mav=new ModelAndView();
			String product_name=req.getParameter("product_name");
			String product_status=req.getParameter("product_status");
			float product_price=Float.parseFloat(req.getParameter("product_price"));
			String product_specifications=req.getParameter("product_specifications");
			boolean newprod=service.AddNewProduct(product_name,product_status,product_price,product_specifications);
		
			if(newprod==true)
			{
				mav = new ModelAndView("addproductsuccess");
			}
			else
			{
				mav=new ModelAndView("addproducterror");
			}
	        return  mav;       
	
	    }
	    
	    @RequestMapping(value ="/admin123/updateproduct", method = RequestMethod.POST)
		public ModelAndView updateproduct(HttpServletRequest req,ModelMap model)throws IOException {
		ModelAndView mav=new ModelAndView();
			String product_name=req.getParameter("product_name");
			String product_status=req.getParameter("product_status");
			float product_price=Float.parseFloat(req.getParameter("product_price"));
			String product_specifications=req.getParameter("product_specifications");
			boolean newprod=service.UpdateProduct(product_name,product_status,product_price,product_specifications);
		
			if(newprod==true)
			{
				mav = new ModelAndView("productupdatesuccess");
			}
			else
			{
				mav=new ModelAndView("productupdateerror");
			}
	        return  mav;       
	
	    }
	    
	    @RequestMapping(value ="/cart", method = {RequestMethod.POST,RequestMethod.GET})
		public ModelAndView cartoperation(HttpServletRequest request, Model m)throws Exception {
	    	
	    	String action=request.getParameter("cart");
	    	//System.out.println("action is"+action);
	    	m.addAttribute("c_details",c_details);
	    	ModelAndView mav=new ModelAndView();
	    	if(action.equalsIgnoreCase("Add to cart"))
	    	{
	    		mav=new ModelAndView("AddToShoppingCart");
	    		return mav;
	    	}
	    	else if(action.equalsIgnoreCase("share details")){
	    		mav=new ModelAndView("emailForm");
	    		return mav;
	    	}else{
	    		String action1=request.getParameter("cart");
	    		System.out.println("action is"+action1);
	    		if(action1.equalsIgnoreCase("Add"))
	    		{
	    			mav=new ModelAndView("AddToShoppingCart");
	    		}
	    		else
	    		{
	    			mav=new ModelAndView("AddToShoppingCart");
	    		}
	    		return mav;
	    	}
	    }
	    
	    @RequestMapping(value ="/shoppingcart", method = RequestMethod.GET)
		public ModelAndView shoppingcart()throws Exception {
	    	
		ModelAndView mav=new ModelAndView("ShoppingCart");
	        
	        
	        return mav;  
	
	    }
	    
	    @RequestMapping(value ="/cardetails/{image_id}/{id}", method = RequestMethod.GET)
		public ModelAndView cardetails(@PathVariable int id,@PathVariable int image_id,HttpServletRequest req,Model m)throws Exception {
	    	id1=id;
	    	m.addAttribute("carname",car_name);
	    	m.addAttribute("car_product",car);
	    	System.out.println(image_id);
	    	car_product c_prod=service.productbyimageid(image_id);
	    	c_details=service.getproductbyid(id);
	    	m.addAttribute("c_prod",c_prod);
	    	m.addAttribute("c_details",c_details);
		ModelAndView mav=new ModelAndView("cardetail");
	        
	        
	        return mav;  
	
	    }
	    
	    @RequestMapping(value ="/cartdisp", method = RequestMethod.GET)
		public ModelAndView shoppingcart(HttpServletRequest req,Model m)throws Exception {
	    	
	    	m.addAttribute("c_details",c_details);
		ModelAndView mav=new ModelAndView("ShoppingCart");
	        
	        
	        return mav;  
	
	    }
	    
	    @RequestMapping(value="/captcha",  method=RequestMethod.GET)
	    public String show() {  
	        return "captcha";
	    }
	     
	    @RequestMapping(value="/recaptcha", method=RequestMethod.POST)
	    public String verify(ServletRequest request, Model model) { 
	        String challenge = request.getParameter("recaptcha_challenge_field");
	        String response = request.getParameter("recaptcha_response_field");
	        String remoteAddr = request.getRemoteAddr();
	         
	        ReCaptchaResponse reCaptchaResponse = reCaptchaService.checkAnswer(remoteAddr, challenge, response);
	         
	        if(reCaptchaResponse.isValid()) {
	            model.addAttribute("message", "you are not robot");
	            return "login";
	        } else {
	            model.addAttribute("message", "Try again and prove it.");
	            return "captcha";
	        }
	    }
}
