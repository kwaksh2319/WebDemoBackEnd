package kr.co.kshproject.webDemo.Domain.Category;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import kr.co.kshproject.webDemo.Domain.Products.Products;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Category")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_SEQ")
    @SequenceGenerator(name = "CATEGORY_SEQ", sequenceName = "CATEGORY_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id; //key

    @NotEmpty
    @Setter
    private String categoryName;

    @NotEmpty
    @Setter
    @Column(name = "created_date")
    private String createdDate;

    @NotEmpty
    @Setter
    @Column(name = "update_date")
    private String updateDate;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id", nullable = true)
    @Setter
    private Category parentCategory;

    @JsonManagedReference(value="category-products")
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Products> products = new HashSet<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Category> childCategories = new HashSet<>();

    public Category(CategoryDTO categoryDTO){
        this.categoryName=categoryDTO.getCategoryName();
        this.createdDate=categoryDTO.getCreatedDate();
        this.updateDate=categoryDTO.getUpdateDate();
        this.parentCategory=categoryDTO.getParentCategory();
    }
}
