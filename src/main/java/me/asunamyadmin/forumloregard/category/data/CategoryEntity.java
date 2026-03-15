package me.asunamyadmin.forumloregard.category.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import me.asunamyadmin.forumloregard.security.Roles;

@Entity
@Getter
@Setter
@Table(name = "forum_categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "name")
    String categoryName;
    @Column()
    String description;
    String icon;
    @Column(name = "sort_order")
    Integer sortOrder;
    @Enumerated(EnumType.STRING)
    @Column(name = "min_role")
    Roles role;
}
