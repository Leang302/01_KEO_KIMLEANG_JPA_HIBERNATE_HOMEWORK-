package org.example.hw002.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    @Schema(description = "Product name", example = "Soju")
    @NotBlank
    @NotEmpty
    @Size(max = 255,message = "Name must not be longer than 255 characters")
    @Pattern(regexp = ".*[a-zA-Z].*", message = "Name must contain at least one letter (a-z or A-Z)")
    private String name;
    @Schema(description = "Product price", example = "10")
    @NotNull(message = "Price must not be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than or equal to 0")
    private Double price;
    @Schema(description = "Product quantity", example = "100")
    @NotNull(message = "Quantity must not be null")
    @Min(value = 0,message = "Quantity must be greater than or equal to 0")
    private Integer quantity;
}
