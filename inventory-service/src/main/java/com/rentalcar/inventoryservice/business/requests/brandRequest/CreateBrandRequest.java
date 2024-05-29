package com.rentalcar.inventoryservice.business.requests.brandRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandRequest {

    @NotNull
    @NotBlank
    @Size(min = 3,max = 20)
    private String name;

    private byte[] returnedImage;

    private MultipartFile image;

}
