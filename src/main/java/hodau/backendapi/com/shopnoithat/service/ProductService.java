package hodau.backendapi.com.shopnoithat.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import hodau.backendapi.com.shopnoithat.common.ResourceNotFoundException;
import hodau.backendapi.com.shopnoithat.dto.ProductDTO;
import hodau.backendapi.com.shopnoithat.entity.Product;
import hodau.backendapi.com.shopnoithat.responsitory.CategoryRepository;
import hodau.backendapi.com.shopnoithat.responsitory.ProductRepository;
import hodau.backendapi.com.shopnoithat.responsitory.UserRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileStorageService fileStorageService;

    public ProductDTO addProduct(ProductDTO productDTO, MultipartFile file) throws IOException {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setCategory(categoryRepository.findById(productDTO.getCategoryId()).orElse(null));
        product.setStatus(productDTO.getStatus());
        product.setCreatedBy(userRepository.findById(productDTO.getCreatedBy()).orElse(null));
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        // Xử lý tải lên hình ảnh
        if (file != null && !file.isEmpty()) {
            String imageUrl = fileStorageService.storeFile(file);
            product.setImageUrl(imageUrl);
        }

        product = productRepository.save(product);
        return toDTO(product);
    }

    public ProductDTO updateProduct(Long productId, ProductDTO productDTO, MultipartFile file) throws IOException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setCategory(categoryRepository.findById(productDTO.getCategoryId()).orElse(null));
        product.setStatus(productDTO.getStatus());
        product.setUpdatedAt(LocalDateTime.now());

        // Xử lý cập nhật ảnh
        if (file != null && !file.isEmpty()) {
            String imageUrl = fileStorageService.storeFile(file);
            product.setImageUrl(imageUrl);
        } else if (productDTO.getImageUrlPath() != null && !productDTO.getImageUrlPath().isEmpty()) {
            // Giữ nguyên URL hình ảnh hiện tại nếu không có file mới được tải lên
            product.setImageUrl(productDTO.getImageUrlPath());
        }

        product = productRepository.save(product);
        return toDTO(product);
    }

    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("Product not found");
        }
        productRepository.deleteById(productId);
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOs = products.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return productDTOs;
    }

    private ProductDTO toDTO(Product product) {
        String categoryName = (product.getCategory() != null) ? product.getCategory().getName() : "";
        Long categoryId = (product.getCategory() != null) ? product.getCategory().getCategoryId() : null;
        String createbyName = (product.getCreatedBy() != null) ? product.getCreatedBy().getFullName() : "";
        return new ProductDTO(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getImageUrl(),
                categoryName,
                categoryId,
                product.getStatus(),
                (product.getCreatedBy() != null) ? product.getCreatedBy().getUserId() : null,
                createbyName,
                product.getCreatedAt(),
                product.getUpdatedAt());
    }
}