package com.axiagroups.productlist.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.axiagroups.productlist.model.Product;
import com.axiagroups.productlist.repository.ProductRepository;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private ProductRepository mRepository;
    private final LiveData<List<Product>> mAllProducts;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ProductRepository(application);
        mAllProducts = mRepository.getAllProducts();
    }

    public LiveData<List<Product>> getAllProducts() {
        return mAllProducts;
    }

    public void insertProduct(Product product) {
        mRepository.insertProduct(product);
    }

    public void deleteProduct(Product product) {
        mRepository.deleteProduct(product);
    }

    public void updateProduct(Product product) {
        mRepository.updateProduct(product);
    }
}
