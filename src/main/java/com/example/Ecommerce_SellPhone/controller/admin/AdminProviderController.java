package com.example.Ecommerce_SellPhone.controller.admin;

import com.example.Ecommerce_SellPhone.DTO.ProviderDTO;
import com.example.Ecommerce_SellPhone.common.ApiResponse;
import com.example.Ecommerce_SellPhone.models.Provider;
import com.example.Ecommerce_SellPhone.service.Provider.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/provider")
public class AdminProviderController {
    @Autowired
    private ProviderService providerService;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProvider(@RequestBody ProviderDTO providerDTO) {
        providerService.createProvider(providerDTO);
        return new ResponseEntity<>(new ApiResponse(true,"Provider created"), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{provider_id}")
    public ResponseEntity<ApiResponse> getAllProvider(@PathVariable("provider_id")int provider_id) {
        if (providerService.deleteProvider(provider_id)){
            return new ResponseEntity<>(new ApiResponse(true,"deleted success"), HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(new ApiResponse(false, "Provider not found or deletion failed"), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateProvider(@RequestBody Provider provider){
        try {
            providerService.updateProvider(provider);
            return new ResponseEntity<>(new ApiResponse(true, "Update provider success"), HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();
            return new ResponseEntity<>(new ApiResponse(false, "Failed to update provider"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
