package com.example.piggytech.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.piggytech.Model.Category;
import com.example.piggytech.NotFoundException.CategoryNotFoundException;
import com.example.piggytech.Repository.CategoryRepository;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    
    CategoryRepository repo;

    public CategoryController(CategoryRepository repo) {
        this.repo = repo;
    }

    // GET ALL CATEGORY
    @GetMapping("/all")
    public List<Category> getCategory(){
        return repo.findAll();
    }

    // GET ONE CATEGORY
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id){
        return repo.findById(id)
        .orElseThrow(()-> new CategoryNotFoundException(id));
    }

    // CREATE ENDPOINTS
    @PostMapping("/new")
    public String addCategory(@RequestBody Category newCategory){
        repo.save(newCategory);
        return "A new category is added. Yey!";
    }

    // UPDATE ENDPOINTS
    @PutMapping("/edit/{id}")
    public Category updateCategory(@PathVariable Long id,
    @RequestBody Category newCategory){
        return repo.findById(id)
        .map(category ->{
            category.setCategoryName(newCategory.getCategoryName());
            
            return repo.save(category);
        }).orElseGet(()->{
            return repo.save(newCategory);
        });
    }

    // DELETE ENDPOINTS
    @DeleteMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id){
        repo.deleteById(id);
        return "A category is deleted!";
    }
}
