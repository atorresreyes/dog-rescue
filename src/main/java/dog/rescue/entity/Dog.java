package dog.rescue.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Dog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dogId;
	
	//exclude them from the and hashcode methods to prevent recusion when creates the .equals method
	@EqualsAndHashCode.Exclude
	private String name;
	
	@EqualsAndHashCode.Exclude
	private int age;
	
	@EqualsAndHashCode.Exclude
	private String color;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	//point back to Location for relationship
	@ManyToOne
	//need to specify join column
	@JoinColumn(name = "location_id", nullable = false)
	private Location location;
	
	//many to many relationship
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST) //we don't want to delete breeds if we delete a dog
	@JoinTable(
			name = "dog_breed",
			joinColumns = @JoinColumn(name = "dog_id"),
			inverseJoinColumns = @JoinColumn(name = "breed_id")
	)
	private Set<Breed> breeds = new HashSet<>();
	
}
