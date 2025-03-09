package com.sotil.micronaut.demo.model;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;

//@Serdeable.Serializable
@Entity
@Table(name = "TB_PRODUCT")
@SequenceGenerator(name = "product_seq", sequenceName = "tb_product_id_seq", allocationSize = 1)
  public class Product{

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;


    @Transient
    private String name;
    @Transient
    private String code;
    @Transient
    private String description;


    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public Customer getCustomer() {
      return customer;
    }

    public void setCustomer(Customer customer) {
      this.customer = customer;
    }

    public Long getProductId() {
      return productId;
    }

    public void setProductId(Long productId) {
      this.productId = productId;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getCode() {
      return code;
    }

    public void setCode(String code) {
      this.code = code;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }
  }
