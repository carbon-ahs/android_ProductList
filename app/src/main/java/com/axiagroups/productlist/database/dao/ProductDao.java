package com.axiagroups.productlist.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.axiagroups.productlist.model.Product;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void insert(Product product);

    @Query("DELETE FROM product_table")
    void deleteAll();

    /* ----------------------------------
    By default, to avoid poor UI performance, Room doesn't allow you to issue queries
    on the main thread. When Room queries return LiveData, the queries are automatically
    run asynchronously on a background thread.
     ------------------------------------- */
    @Query("SELECT * FROM product_table")
    LiveData<List<Product>> getAllProducts();
}
