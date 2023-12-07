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
    @Schema(example  = "null")
    private Long id;
    @Schema(example  = "카테고리명")
    private String categoryName;
    @Schema(example  = "생성날짜")
    private String createdDate;
    @Schema(example  = "업데이트날짜")
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
