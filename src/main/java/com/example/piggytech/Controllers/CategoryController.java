package com.example.piggytech.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.piggytech.Model.Category;
import com.example.piggytech.NotFoundException.CategoryNotFoundException;
import com.example.piggytech.Repository.CategoryRepository;

@RestController
public class CategoryController {
    
    CategoryRepository repo;

    public CategoryController(CategoryRepository repo) {
        this.repo = repo;
    }

    // GET ALL CATEGORY
    // http://127.0.0.1:8080/category
    @GetMapping("/category")
    public List<Category> getCategory(){
        return repo.findAll();
    }

    // GET ONE CATEGORY
    // http://127.0.0.1:8080/category/1
    @GetMapping("/category/{id}")
    public Category getCategoryById(@PathVariable Long id){
        return repo.findById(id)
        .orElseThrow(()-> new CategoryNotFoundException(id));
    }

    // CREATE ENDPOINTS
    // http://127.0.0.1:8080/category/new
    @PostMapping("/category/new")
    public String addCategory(@RequestBody Category newCategory){
        repo.save(newCategory);
        return "A new category is added. Yey!";
    }

    // UPDATE ENDPOINTS
    // http://127.0.0.1:8080/category/edit/1
    @PutMapping("/category/edit/{id}")
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
    // http://127.0.0.1:8080/category/delete/1
    @DeleteMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable Long id){
        repo.deleteById(id);
        return "A category is deleted!";
    }
}
