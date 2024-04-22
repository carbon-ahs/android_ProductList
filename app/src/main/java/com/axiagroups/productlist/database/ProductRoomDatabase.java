package com.axiagroups.productlist.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.axiagroups.productlist.database.dao.ProductDao;
import com.axiagroups.productlist.model.Product;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Product.class}, version = 1, exportSchema = true)
public abstract class ProductRoomDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
    public static final int NUMBER_OF_THREADS = 4;
    public static final String DB_NAME = "product_database";
    private static volatile ProductRoomDatabase INSTANCE;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // returns singletone db
    public static ProductRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    ProductRoomDatabase.class, DB_NAME)
                    .build();
        }
        return INSTANCE;
    }
}
