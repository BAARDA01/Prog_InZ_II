package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lv.venta.model.Product;

@Controller
public class FirstController {
	
	
	@GetMapping("/hello") //localhost:8080/hello
	public String getHello() {
		System.out.println("The first controller is working!!!");
		return "hello-page";//it will show hello-page.html file in the browser
	}
	
	Random rand = new Random();
	@GetMapping("/hello/msg") //localhost:8080/hello/msg
	public String getHelloMsg(Model model)
	{
		System.out.println("The second controller is working!!!");
		model.addAttribute("mypackage", "Hello from JAVA: " + rand.nextInt(0, 100));
		return "hello-msg-page";//it will show hello-msg-page.html file in the browser
	}
	
	@GetMapping("/product/tst") //localhost:8080/product/tst
	public String getProductTest(Model model) {
		System.out.println("The third constroller");
		Product p = new Product("Apple", "Red", 0.99f, 4);
		model.addAttribute("mypackage", p);
		return "product-page";
		
	}
	
	public List<Product> getProductList() {
		System.out.println("The fourth controller");
		List<Product> allProducts = new ArrayList<Product>();
		Product milk = new Product("Milk", "is white", 0.89f, 1);
		Product banana = new Product("Banana", "is vegetable", 0.5f, 3);
		Product carrot = new Product("Carrot", "does not improve vision", 0.33f, 5);
		allProducts.addAll(Arrays.asList(milk, banana, carrot));
		return allProducts;	
	}
}
