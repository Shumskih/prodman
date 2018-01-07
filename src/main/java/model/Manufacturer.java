package model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "manufacturers")
public class Manufacturer {

    @Id
    @Column(name = "id")
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "manufacturer")
    private Set<Product> products;

    public Manufacturer() {

    }

    public Manufacturer(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public Manufacturer(UUID id, String name, Set<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "<ul class=\"text-center\">" +
                    "<li class=\"list\"><strong>ID:</strong> " + id + "</li>" + '\n' +
                    "<li class=\"list\"><strong>Name:</strong> " + name + "</li>" +
                "</ul>" + '\n';
    }
}
