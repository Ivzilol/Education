package com.example.errors.web;

import com.example.errors.model.ApiErrorDTO;
import com.example.errors.model.ObjectNotFoundException;
import com.example.errors.model.ProductDTO;
import com.example.errors.model.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/product")
public class ProductRestController {

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long id) {
        ProductDTO productDTO = getProductDTOById(id);

        if (productDTO == null){
            throw new ProductNotFoundException(id);
        }
        return ResponseEntity.ok(productDTO);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ProductNotFoundException.class})
    public @ResponseBody ApiErrorDTO handleRESTErrors(ProductNotFoundException e) {
        return new ApiErrorDTO(e.getId(),
                "Product was not found!");
    }


    private ProductDTO getProductDTOById(Long id) {
        return null;
    }

}
