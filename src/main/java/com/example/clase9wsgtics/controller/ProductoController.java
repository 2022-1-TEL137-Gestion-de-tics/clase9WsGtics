package com.example.clase9wsgtics.controller;

import com.example.clase9wsgtics.entity.Product;
import com.example.clase9wsgtics.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class ProductoController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping(value = "/product")
    public List<Product> listarProductos() {
        return productRepository.findAll();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<HashMap<String, Object>> obtenerProductoPorId(@PathVariable("id") String idStr) {

        HashMap<String, Object> responseJson = new HashMap<>();
        try {
            Optional<Product> optProduct = productRepository.findById(Integer.parseInt(idStr));
            if (optProduct.isPresent()) {
                responseJson.put("result", "success");
                responseJson.put("product", optProduct.get());
                return ResponseEntity.ok(responseJson);
            } else {
                responseJson.put("msg", "Producto no encontrado");
            }
        } catch (NumberFormatException e) {
            responseJson.put("msg", "el ID debe ser un número entero positivo");
        }
        responseJson.put("result", "failure");
        return ResponseEntity.badRequest().body(responseJson);
    }

    @PostMapping(value = "/product")
    public ResponseEntity<HashMap<String, Object>> guardarProducto(
            @RequestBody Product product,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> responseMap = new HashMap<>();

        productRepository.save(product);
        if (fetchId) {
            responseMap.put("id", product.getId());
        }
        responseMap.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);

    }

    @PutMapping(value = "/product")
    public ResponseEntity<HashMap<String, Object>> actualizarProducto(@RequestBody Product product) {

        HashMap<String, Object> responseMap = new HashMap<>();

        if (product.getId() != null && product.getId() > 0) {
            Optional<Product> opt = productRepository.findById(product.getId());
            if (opt.isPresent()) {
                productRepository.save(product);
                responseMap.put("estado", "actualizado");
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("estado", "error");
                responseMap.put("msg", "El producto a actualizar no existe");
                return ResponseEntity.badRequest().body(responseMap);
            }
        } else {
            responseMap.put("estado", "error");
            responseMap.put("msg", "Debe enviar un ID");
            return ResponseEntity.badRequest().body(responseMap);
        }
    }

    @PutMapping(value = "/product/parcial")
    public ResponseEntity<HashMap<String, Object>> actualizarProductoParcial(@RequestBody Product product) {

        HashMap<String, Object> responseMap = new HashMap<>();

        if (product.getId() > 0) {
            Optional<Product> opt = productRepository.findById(product.getId());
            if (opt.isPresent()) {
                Product productFromDb = opt.get();

                if (product.getProductName() != null)
                    productFromDb.setProductName(product.getProductName());

                if (product.getUnitPrice() != null)
                    productFromDb.setUnitPrice(product.getUnitPrice());

                if (product.getUnitsInStock() != null)
                    productFromDb.setUnitsInStock(product.getUnitsInStock());

                if (product.getUnitsOnOrder() != null)
                    productFromDb.setUnitsOnOrder(product.getUnitsOnOrder());

                if (product.getSupplier() != null)
                    productFromDb.setSupplier(product.getSupplier());

                if (product.getCategory() != null)
                    productFromDb.setCategory(product.getCategory());

                if (product.getQuantityPerUnit() != null)
                    productFromDb.setQuantityPerUnit(product.getQuantityPerUnit());

                if (product.getReorderLevel() != null)
                    productFromDb.setReorderLevel(product.getReorderLevel());

                if (product.getDiscontinued() != null)
                    productFromDb.setDiscontinued(product.getDiscontinued());

                productRepository.save(productFromDb);
                responseMap.put("estado", "actualizado");
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("msg", "El producto a actualizar no existe");
            }
        } else {
            responseMap.put("msg", "Debe enviar un ID");
        }
        responseMap.put("estado", "error");
        return ResponseEntity.badRequest().body(responseMap);
    }

    @PutMapping(value = "/product/parcial/xwwwform",
            consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<HashMap<String, Object>> actualizarProductoParcialXwwwForm(Product product) {

        HashMap<String, Object> responseMap = new HashMap<>();

        if (product.getId() > 0) {
            Optional<Product> opt = productRepository.findById(product.getId());
            if (opt.isPresent()) {
                Product productFromDb = opt.get();

                if (product.getProductName() != null)
                    productFromDb.setProductName(product.getProductName());

                if (product.getUnitPrice() != null)
                    productFromDb.setUnitPrice(product.getUnitPrice());

                if (product.getUnitsInStock() != null)
                    productFromDb.setUnitsInStock(product.getUnitsInStock());

                if (product.getUnitsOnOrder() != null)
                    productFromDb.setUnitsOnOrder(product.getUnitsOnOrder());

                if (product.getSupplier() != null)
                    productFromDb.setSupplier(product.getSupplier());

                if (product.getCategory() != null)
                    productFromDb.setCategory(product.getCategory());

                if (product.getQuantityPerUnit() != null)
                    productFromDb.setQuantityPerUnit(product.getQuantityPerUnit());

                if (product.getReorderLevel() != null)
                    productFromDb.setReorderLevel(product.getReorderLevel());

                if (product.getDiscontinued() != null)
                    productFromDb.setDiscontinued(product.getDiscontinued());

                productRepository.save(productFromDb);
                responseMap.put("estado", "actualizado");
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("msg", "El producto a actualizar no existe");
            }
        } else {
            responseMap.put("msg", "Debe enviar un ID");
        }
        responseMap.put("estado", "error");
        return ResponseEntity.badRequest().body(responseMap);
    }

    @DeleteMapping(value = "/product/{id}")
    public ResponseEntity<HashMap<String, Object>> borrarProducto(@PathVariable("id") String idStr) {

        HashMap<String, Object> responseMap = new HashMap<>();

        try {
            int id = Integer.parseInt(idStr);
            if (productRepository.existsById(id)) {
                productRepository.deleteById(id);
                responseMap.put("estado", "borrado exitoso");
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("estado", "error");
                responseMap.put("msg", "no se encontró el producto con id: " + id);
                return ResponseEntity.badRequest().body(responseMap);
            }
        } catch (NumberFormatException ex) {
            responseMap.put("estado", "error");
            responseMap.put("msg", "El ID debe ser un número");
            return ResponseEntity.badRequest().body(responseMap);
        }
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap<String, String>> gestionExcepcion(HttpServletRequest request) {

        HashMap<String, String> responseMap = new HashMap<>();
        if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
            responseMap.put("estado", "error");
            responseMap.put("msg", "Debe enviar un producto");
        }
        return ResponseEntity.badRequest().body(responseMap);
    }


}
