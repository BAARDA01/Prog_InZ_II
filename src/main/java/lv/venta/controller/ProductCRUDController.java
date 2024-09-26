package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.model.Product;
import lv.venta.service.IProductFilteringService;
import lv.venta.service.IproductCRUDService;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/product/crud")
public class ProductCRUDController {
	
	@Autowired
	private IproductCRUDService productCRUDservice;

	@GetMapping("/CRUD") //localhost:8080/product/crud/CRUD
	public String getProductCRUDAll(Model model) {
		
	try {
		ArrayList<Product> result = productCRUDservice.retrieveALl();
		System.out.println(result);
		model.addAttribute("mypackage", result);
		return "show-all-product-page";
		}
	catch(Exception e) {
			model.addAttribute("mypackage", e.getMessage());
			return "error-page";//will show error page.html with exception message
		}
		
	}
	//localhost:8080/product/crud/CRUD/1
	
	@GetMapping("/CRUD/{id}")  //localhost:8080/product/crud/CRUD/1
	public String getProductCrudByID(@PathVariable("id")int id, Model model) {
		
		try {
			Product result = productCRUDservice.retrieveById(id);
			model.addAttribute("mypackage", result);
			return "show-one-product-page";
		}
		catch (Exception e) {
			model.addAttribute("mypackage", e.getMessage());
			return "error-page"; //will show error page.html with exception message
		}
	  
	}
	@GetMapping("/one")//localhost:8080/product/crud/one?id=1
	public String getProductCrudByIDWithQuestionMark(@RequestParam("id")int id, Model model) {
		
		try {
			Product result = productCRUDservice.retrieveById(id);
			model.addAttribute("mypackage", result);
			return "show-one-product-page";
		}
		catch (Exception e) {
			model.addAttribute("mypackage", e.getMessage());
			return "error-page"; //will show error page.html with exception message
		}
	  
	}
	
	@GetMapping("/create") //localhost:8080/product/crud/create
	public String getProductCRUDCreate(Model model) {
		model.addAttribute("product", new Product());
		return "create-product-page";	// will show create-product-page with deafault product
		
		
		
	}
	@PostMapping("/create")
	public String postproductCRUDCreate(@Valid Product product, BindingResult result, Model model)
	{
		if(result.hasErrors()) {
			return "create-product-page";
		}
		else {
		try {
			productCRUDservice.create(product.getTitle(), product.getDescription(), product.getPrice(),
					product.getQuantity());
			return "redirect:/product/crud/CRUD";// the endpoint localhost:8080/product/crud/CRUD will be called

		} catch (Exception e) {
			model.addAttribute("mypackage", e.getMessage());
			return "error-page";// will show error-page.html page with exception message
			}
		}
	}
	
	@GetMapping("/update/{id}") //localhost:8080/product/crud/update/1
	public String getproductCRUDUpdateByID(@PathVariable("id")int id, Model model) {
		try {
			Product productForUpdating = productCRUDservice.retrieveById(id);
			model.addAttribute("product", productForUpdating);
			model.addAttribute("id", id);
			return "update-product-page";
		}
		catch(Exception e) {
			model.addAttribute("mypackage", e.getMessage());
			return "error-page";
			
		}
	
	}
	
	@PostMapping("/update/{id}")
	public String postproductCRUDUpdateByID(@PathVariable("id") int id, @Valid Product product, BindingResult result, Model model ){
		if(result.hasErrors()) {
			return "update-product-page";
		}
		else {
		try {
			productCRUDservice.updateById(id, product.getTitle(), product.getDescription(), product.getPrice() ,product.getQuantity());
			return "redirect:/product/crud/CRUD/" + id;
			}
		catch(Exception e){
			model.addAttribute("mypackage", e.getMessage());
			return "error-page";
			}
		}
	}
	@GetMapping("/delete/{id}") //localhost:8080/product/crud/delete/1
	public String getProductCRUDDeleteByID (@PathVariable("id") int id, Model model) {
		try {
			productCRUDservice.deleteById(id);
			ArrayList<Product> result = productCRUDservice.retrieveALl();
			model.addAttribute("mypackage", result);
			return "show-all-product-page";
		}
		catch(Exception e){
			model.addAttribute("mypackage", e.getMessage());
			return "error-page";
		}
	}

}
