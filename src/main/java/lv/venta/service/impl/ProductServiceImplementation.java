package lv.venta.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;
import lv.venta.service.IProductFilteringService;
import lv.venta.service.IproductCRUDService;
import lv.venta.repo.*;






@Service
public class ProductServiceImplementation implements IproductCRUDService, IProductFilteringService {

	@Autowired
	private IProductRepo productRepo;
	
	@Override
	public void create(String title, String description, float price, int quantity) throws Exception {
		if (title == null || description == null || price < 0 || quantity < 0)
			throw new Exception("Problems in input");
		
		Product productFromDB = productRepo.findByTitleAndDescriptionAndPrice(title, description, price);
		// product exists
		if(productFromDB!=null) {
			productFromDB.setQuantity(productFromDB.getQuantity() + quantity);//will change only in back-end layer
			productRepo.save(productFromDB);//will change also in database layer
		}
		else
		{
			Product productNew = new Product(title, description, price, quantity);//will create new product only in back-end layer
			productRepo.save(productNew);//will save it also in database layer
		}
	}

	@Override
	public ArrayList<Product> retrieveALl() throws Exception {
		if(productRepo.count() == 0) throw new Exception("Product table is empty");
		ArrayList<Product> result = (ArrayList<Product>) productRepo.findAll();
		return result;
	}

	@Override
	public Product retrieveById(int id) throws Exception {
		if(id <= 0) throw new Exception("ID should be positive");
		if(productRepo.existsById(id))
			return productRepo.findById(id).get();
		throw new Exception ("Product with provided id does not exist in the table");
	}

	@Override
	public void updateById(int id, String title, String description, float price, int quantity) throws Exception {
		if(title == null || description == null || price < 0 || quantity < 0)
			throw new Exception("Problems with parameters");
		Product productForUpdating = retrieveById(id);
		productForUpdating.setDescription(description);
		productForUpdating.setPrice(price);
		productForUpdating.setQuantity(quantity);
		productForUpdating.setTitle(title);
		productRepo.save(productForUpdating);
		
	}

	@Override                                                         
	public void deleteById(int id) throws Exception {                              
		Product productForDeletingProduct = retrieveById(id);
		productRepo.delete(productForDeletingProduct);
		
	}

	@Override
	public ArrayList<Product> filterByQuantityThreshold(int threshold) throws Exception {
		if(productRepo.count() == 0) throw new Exception("Product table is empty");
		ArrayList<Product> result = productRepo.findByQuantityLessThan(threshold);
		if(result.isEmpty()) throw new Exception("There is no product which quantity is less then " + threshold);
		
		return result;
	}

	@Override
	public ArrayList<Product> filterByPriceBetween(float minPrice, float maxPrice) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
