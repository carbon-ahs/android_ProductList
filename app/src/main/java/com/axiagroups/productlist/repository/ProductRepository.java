package com.axiagroups.productlist.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.axiagroups.productlist.database.ProductRoomDatabase;
import com.axiagroups.productlist.database.dao.ProductDao;
import com.axiagroups.productlist.model.Product;

import java.util.List;

public class ProductRepository {
    private ProductDao mProductDao;
    private LiveData<List<Product>> mAllProducts;

    public ProductRepository(Application application) {
        ProductRoomDatabase db = ProductRoomDatabase.getDatabase(application);
        mProductDao = db.productDao();
        mAllProducts = mProductDao.getAllProducts();
    }

    public LiveData<List<Product>> getAllProducts() {
        return mAllProducts;
    }

    public void insert(Product product) {
        ProductRoomDatabase.databaseWriteExecutor.execute(() ->{
            mProductDao.insert(product);
        });
    }
}
