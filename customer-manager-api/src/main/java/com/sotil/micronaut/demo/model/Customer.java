package com.sotil.micronaut.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import java.util.List;

//@Serdeable.Serializable
@Entity
@Table(name = "TB_CUSTOMER")
@SequenceGenerator(name = "customer_seq", sequenceName = "tb_customer_id_seq", allocationSize = 1)
public class Customer{

  @Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
  private Long id;

  @Column(name = "accountNumber", length = 255)
  private String accountNumber;

  @Column(name = "address", length = 255)
  private String address;

  @Column(name = "code", length = 255)
  private String code;

  @Column(name = "names", length = 255)
  private String names;

  @Column(name = "phone", length = 255)
  private String phone;

  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  //@JsonIgnore
  private List<Product> products;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getNames() {
    return names;
  }

  public void setNames(String names) {
    this.names = names;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }
}
