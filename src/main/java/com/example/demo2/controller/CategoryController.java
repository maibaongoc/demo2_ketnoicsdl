package com.example.demo2.controller;

import com.example.demo2.entity.*;
import com.example.demo2.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import com.example.demo2.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/categories")

public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showAllCategories(Model model){
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "category/listcategories";
    }

    @GetMapping("/addcate")
    public String addCateForm(Model model){
        model.addAttribute("category", new Category());
//        model.addAttribute("categories", categoryService.getAllCategories());
        return "category/addcategory";
    }

    @PostMapping("/addcate")
    public String addCate(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){

            return "category/addcategory";
        }
        categoryService.addCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/deletecate/{id}")
    public String deleteCate(@PathVariable("id") Long id){
        Category category = categoryService.getCategoryById(id);
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }

    @GetMapping("/editcate/{id}")
    public String editCateForm(@PathVariable("id") Long id, Model model){
        Category category = categoryService.getCategoryById(id);
        if(category!=null){
            model.addAttribute("category", category);
            return "category/editcategory";
        }
        else{
            return "not-found";
        }
    }

    @PostMapping("/editcate")
    public String editCate(@Valid @ModelAttribute("category") Category updatedCate, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "category/editcategory";
        }
        categoryService.updateCategory(updatedCate);
        return "redirect:/categories";
    }



}
