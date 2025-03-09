package com.sotil.micronaut.demo.service;

import com.sotil.micronaut.demo.dto.CustomerDTO;
import com.sotil.micronaut.demo.dto.ProductDTO;
import com.sotil.micronaut.demo.integration.ProductBackupClient;
import com.sotil.micronaut.demo.integration.ProductClient;
import com.sotil.micronaut.demo.model.Customer;
import com.sotil.micronaut.demo.model.Product;
import com.sotil.micronaut.demo.repository.CustomerRepository;
import com.sotil.micronaut.demo.repository.ProductRepository;
//import io.github.resilience4j.micronaut.annotation.CircuitBreaker;
import io.micronaut.context.annotation.Executable;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.util.CollectionUtils;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class CustomerServiceImpl implements CustomerService {
    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Inject
    private CustomerRepository customerRepository;

    @Inject
    private ProductRepository productRepository;

    @Inject
    private ProductClient productClient;

    @Inject
    private ProductBackupClient productBackupClient;

    @Override
    public List<CustomerDTO> listAll() {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        List<Customer> customerList = customerRepository.findAll();
        return customerList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDTO> findById(Long id) {
        List<ProductDTO>  productDTOList = new ArrayList<>();
        Optional<Customer> customerDB = customerRepository.findById(id);
        if(!customerDB.isPresent()){
            Optional.empty();
        }
        Customer customer = customerDB.get();
        List<Product> products =customer.getProducts();
        if(CollectionUtils.isNotEmpty(products)){
            for(Product product : products){
                log.info("find Product by id ::{}",product.getProductId());
                ProductDTO productClientDTO = getProductDTOFromClient(product.getId());
                productDTOList.add(productClientDTO);
            }
        }

        return Optional.of(new CustomerDTO(
                customer.getId(),
                customer.getAccountNumber(),
                customer.getAddress(),
                customer.getCode(),
                customer.getNames(),
                customer.getPhone(),
                productDTOList
        ));
    }

    //@CircuitBreaker(name = "product-backup-api", fallbackMethod = "futureFallback")
    public ProductDTO getProductDTOFromClient(Long id) {
        try {
            return productClient.findById(id);
        } catch (Exception ex) {
            throw new RuntimeException("No available services for ID: product-manager-api", ex);
        }
    }

    @Executable
    public ProductDTO futureFallback(Long id, Throwable throwable) {
        System.out.println("Ejecutando futureFallback debido a: " + throwable.getMessage());
        return productBackupClient.fallback(id);
    }

    //@Override
    public Optional<CustomerDTO> findByIdtest2(Long id) {
        return customerRepository.findById(id)
                .map(this::toDTO); // Convert entity to DTO
    }

    //@Override
    public Optional<CustomerDTO> findByIdtest(Long id) {
        Optional<CustomerDTO> customerDTO = customerRepository.findById(id)
            .map(this::toDTO)
                .map(customer -> {
                    // add product client
                    List<ProductDTO> products = new ArrayList<>(customer.products());
                    for (ProductDTO dto: products){
                        log.info("find Product by id ::{}",dto.id());
                        ProductDTO productDTO = productClient.findById(dto.id());
                        log.info("productDTO:::{}",productDTO);
                        products.add(productDTO);
                    }

                    // Retornar un nuevo CustomerDTO con la lista de productos actualizada
                    return new CustomerDTO(
                            customer.id(),
                            customer.accountNumber(),
                            customer.address(),
                            customer.code(),
                            customer.names(),
                            customer.phone(),
                            products
                    );
                });
        return customerDTO;
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = toEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return toDTO(savedCustomer);
    }

    @Override
    public void delete(Long id) {
        Optional<Customer> Customer = customerRepository.findById(id);
        if(Customer.isPresent()){
        customerRepository.delete(Customer.get());
        }
    }

    // Convert CustomerDTO to Customer entity
    private Customer toEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setId(dto.id());
        customer.setAccountNumber(dto.accountNumber());
        customer.setAddress(dto.address());
        customer.setCode(dto.code());
        customer.setNames(dto.names());
        customer.setPhone(dto.phone());

        if (dto.products() != null) {
            List<Product> products = dto.products().stream()
                    .map(this::toEntity)
                    .collect(Collectors.toList());
            customer.setProducts(products);
        }

        return customer;
    }

    // Convert ProductDTO to Product entity
    private Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setProductId(dto.id());
        return product;
    }


    private CustomerDTO toDTO(Customer customer) {
        List<ProductDTO> productDTOs = customer.getProducts().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new CustomerDTO(
                customer.getId(),
                customer.getAccountNumber(),
                customer.getAddress(),
                customer.getCode(),
                customer.getNames(),
                customer.getPhone(),
                productDTOs
        );
    }

    private ProductDTO toDTO(Product product) {
        return new ProductDTO(
                product.getProductId(),
                //null, // Avoid circular reference
                product.getName(),
                product.getCode(),
                product.getDescription()
        );
    }


    private CustomerDTO toDTO(Customer customer, ProductDTO productDTORest) {
        List<ProductDTO> productDTOs = customer.getProducts().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return new CustomerDTO(
                customer.getId(),
                customer.getAccountNumber(),
                customer.getAddress(),
                customer.getCode(),
                customer.getNames(),
                customer.getPhone(),
                productDTOs
        );
    }

    private ProductDTO toDTO(Product product,ProductDTO productDTORest) {
        return new ProductDTO(
                product.getProductId(),
                //null, // Avoid circular reference
                product.getName(),
                product.getCode(),
                product.getDescription()
        );
    }

}
