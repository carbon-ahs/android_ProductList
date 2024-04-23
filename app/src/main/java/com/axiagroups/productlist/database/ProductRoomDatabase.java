package com.axiagroups.productlist.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

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
                    .addCallback(sRoomDatabaseCallback)
                    .build();
        }
        return INSTANCE;
    }

    public static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                ProductDao dao = INSTANCE.productDao();
                dao.deleteAll();

                Product product = new Product("Product1", 123);
                dao.insert(product);
                product = new Product("Product2", 222);
                dao.insert(product);

            });
        }
    };
}
