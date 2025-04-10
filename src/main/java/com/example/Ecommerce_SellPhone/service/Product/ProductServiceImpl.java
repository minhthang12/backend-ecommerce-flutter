package com.example.Ecommerce_SellPhone.service.Product;

import com.example.Ecommerce_SellPhone.DTO.ProductDTO;
import com.example.Ecommerce_SellPhone.Exception.ProductNotExistsException;
import com.example.Ecommerce_SellPhone.models.Category;
import com.example.Ecommerce_SellPhone.models.Product;
import com.example.Ecommerce_SellPhone.models.Provider;
import com.example.Ecommerce_SellPhone.repository.CategoryRepository;
import com.example.Ecommerce_SellPhone.repository.ProductRepository;
import com.example.Ecommerce_SellPhone.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public void createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setProductDescription(productDTO.getProductDescription());
        product.setPictures(productDTO.getPictures());
        product.setSize(productDTO.getSize());

        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Provider provider = providerRepository.findById(productDTO.getProviderId())
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        product.setCategory(category);
        product.setProvider(provider);

        productRepository.save(product);
    }

    public ProductDTO getProductDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setProductName(product.getProductName());
        productDTO.setProductDescription(product.getProductDescription());
        productDTO.setPictures(product.getPictures());
        productDTO.setPrice(product.getPrice());
        productDTO.setSize(product.getSize());
        productDTO.setCategoryId(product.getCategory().getId());
        productDTO.setProviderId(product.getProvider().getId());
        return productDTO;
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : products) {
            productDTOList.add(getProductDTO(product));
        }
        return productDTOList;
    }

    @Override
    public List<ProductDTO> getAllProductByCategory(int categoryId) {
        List<Product> products = productRepository.findAllByCategoryId(categoryId);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : products) {
            productDTOList.add(getProductDTO(product));
        }
        return productDTOList;
    }

    @Override
    public Product findById(int productId) throws ProductNotExistsException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotExistsException("Product ID is not valid: " + productId);
        }
        return optionalProduct.get();
    }

    @Override
    public List<ProductDTO> searchProduct(String productName) {
        List<Product> products = productRepository.searchProduct(productName);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : products) {
            productDTOList.add(getProductDTO(product));
        }
        return productDTOList;
    }

    @Override
    public void updateProduct(ProductDTO productDTO) {
        Product product = productRepository.findById(productDTO.getId())
                .orElseThrow(() -> new ProductNotExistsException("Product ID is not valid: " + productDTO.getId()));

        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setProductDescription(productDTO.getProductDescription());
        product.setPictures(productDTO.getPictures());
        product.setSize(productDTO.getSize());

        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Provider provider = providerRepository.findById(productDTO.getProviderId())
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        product.setCategory(category);
        product.setProvider(provider);

        productRepository.save(product);
    }

    @Override
    public ProductDTO FindByID(int productID) {
        Optional<Product> optionalProduct = productRepository.findById(productID);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotExistsException("Product ID is not valid: " + productID);
        }
        return getProductDTO(optionalProduct.get());
    }

    @Override
    public List<ProductDTO> getAllProductByProviderID(int providerId) {
        List<Product> products = productRepository.findAllByProviderId(providerId);
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : products) {
            productDTOList.add(getProductDTO(product));
        }
        return productDTOList;
    }
}
