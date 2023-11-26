package kr.co.kshproject.webDemo.Domain.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private String categoryName;

    private String createdDate;

    private String updateDate;

    private Category parentCategory;

    public CategoryDTO(Category category){
        this.categoryName=category.getCategoryName();
        this.createdDate=category.getCreatedDate();
        this.updateDate=category.getUpdateDate();
        this.parentCategory=category.getParentCategory();
    }
}
