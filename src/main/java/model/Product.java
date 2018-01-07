package model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    @Type(type = "big_decimal")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

    public Product() {

    }

    public Product(UUID id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(UUID id, String name, BigDecimal price, Manufacturer manufacturer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "<p class=\"text-center\">==============<p>" +
                "<ul class=\"text-center\">" +
                    "<li class=\"list\"><strong>ID:</strong> " + id + "</li>" + '\n' +
                    "<li class=\"list\"><strong>Name:</strong> " + name + "</li>" +
                    "<li class=\"list\"><strong>Price:</strong> " + price + " rub</li>" +
                    "<li class=\"list\"><strong>Manufacturer:</strong> " + manufacturer.getName() + "</li>" +
                "</ul>" + '\n';
    }
}