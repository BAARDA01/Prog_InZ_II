package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
//@RequiredArgsConstructor
@ToString
@Table(name = "ProductTable")
@Entity

public class Product {
	@Setter(value = AccessLevel.NONE)
	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotNull
	@Pattern(regexp = "[A-Za-z]+", message = "Only letters allowed")
	@Size(min = 2, max = 20)
	@Column(name = "Title")
	private String title;
	
	@NotNull
	@Pattern(regexp = "[A-Za-z ,]+")
	@Size(min = 2, max = 40)
	@Column(name = "Description")
	private String description;
	
	@Min(0)
	@Max(1000)
	@Column(name = "Price")
	private float price;
	
	@Min(0)
	@Max(1000)
	@Column(name = "Quantity")
	private int quantity;

	
	private static int counter = 1;
	 
	public void setId() {
		this.id = counter;
		counter++;
	}
	
	public Product(String title, String description, float price, int quanity) {
		setId();
		setTitle(title);
		setDescription(description);
		setPrice(price);
		setQuantity(quanity);
	}

}
