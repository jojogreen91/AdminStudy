package com.example.AdminStudy.service;

import com.example.AdminStudy.ifs.CrudInterface;
import com.example.AdminStudy.model.entity.Category;
import com.example.AdminStudy.model.network.Header;
import com.example.AdminStudy.model.network.request.CategoryApiRequest;
import com.example.AdminStudy.model.network.response.CategoryApiResponse;
import com.example.AdminStudy.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryApiLogicService implements CrudInterface<CategoryApiRequest, CategoryApiResponse> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {

        CategoryApiRequest categoryApiRequest = request.getData();

        Category category = Category.builder()
                .type(categoryApiRequest.getType())
                .title(categoryApiRequest.getTitle())
                .build();

        Category newCategory = categoryRepository.save(category);

        return response(newCategory);
    }

    @Override
    public Header<CategoryApiResponse> read(Long id) {

        Category category = categoryRepository.getById(id);

        return response(category);
    }

    @Override
    public Header<CategoryApiResponse> update(Header<CategoryApiRequest> request) {

        CategoryApiRequest categoryApiRequest = request.getData();

        Category category = categoryRepository.getById(categoryApiRequest.getId())
                .setType(categoryApiRequest.getType())
                .setTitle(categoryApiRequest.getTitle());

        Category newCategory = categoryRepository.save(category);

        return response(newCategory);
    }

    @Override
    public Header delete(Long id) {

        Category category = categoryRepository.getById(id);

        categoryRepository.deleteById(id);

        return Header.OK();
    }

    private Header<CategoryApiResponse> response (Category category) {

        CategoryApiResponse categoryApiResponse = CategoryApiResponse.builder()
                .id(category.getId())
                .type(category.getType())
                .title(category.getTitle())
                .build();

        return Header.OK(categoryApiResponse);
    }
}
