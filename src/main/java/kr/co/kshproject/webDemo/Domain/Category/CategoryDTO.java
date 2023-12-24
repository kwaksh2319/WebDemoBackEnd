package kr.co.kshproject.webDemo.Domain.Category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    @Schema(example  = "null")
    private Long id;
    @Schema(example  = "카테고리명")
    @NotNull
    @NotBlank
    private String categoryName;
    @Schema(example  = "1900-01-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String createdDate;
    @Schema(example  = "1900-01-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String updateDate;
    @Schema(example  = "null")
    private Long parentCategoryId;

    public CategoryDTO(Category category){
        this.id=category.getId();
        this.categoryName=category.getCategoryName();
        this.createdDate=category.getCreatedDate();
        this.updateDate=category.getUpdateDate();
        if(category.getParentCategory()!=null){
            this.parentCategoryId=category.getParentCategory().getId();
        }
    }
}
