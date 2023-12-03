package kr.co.kshproject.webDemo.Domain.Category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    @Schema(example  = "카테고리명")
    private String categoryName;
    @Schema(example  = "생성날짜")
    private String createdDate;
    @Schema(example  = "업데이트날짜")
    private String updateDate;
    @Schema(example  = "null")
    private Category parentCategory;

    public CategoryDTO(Category category){
        this.categoryName=category.getCategoryName();
        this.createdDate=category.getCreatedDate();
        this.updateDate=category.getUpdateDate();
        this.parentCategory=category.getParentCategory();
    }
}
