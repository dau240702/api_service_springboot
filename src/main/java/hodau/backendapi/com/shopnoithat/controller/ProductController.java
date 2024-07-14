package hodau.backendapi.com.shopnoithat.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import hodau.backendapi.com.shopnoithat.dto.ProductDTO;
import hodau.backendapi.com.shopnoithat.service.FileStorageService;
import hodau.backendapi.com.shopnoithat.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final FileStorageService fileStorageService;

    private final ProductService productService;

    public ProductController(FileStorageService fileStorageService, ProductService productService) {
        this.fileStorageService = fileStorageService;
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = fileStorageService.storeFile(file);
        return new ResponseEntity<>(fileName, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductDTO> addProduct(@ModelAttribute ProductDTO productDTO) throws IOException {
        productDTO.setCreatedAt(LocalDateTime.now());
        return new ResponseEntity<>(productService.addProduct(productDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @ModelAttribute ProductDTO productDTO)
            throws IOException {
        return new ResponseEntity<>(productService.updateProduct(id, productDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
