package com.example.hofprog.Migration;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class MyMigration extends Migration {
    public MyMigration(int startVersion, int endVersion) {
        super(startVersion, endVersion);
    }

    @Override
    public void migrate(SupportSQLiteDatabase database) {
        // Здесь вы определяете, какие изменения нужно внести
        database.execSQL("ALTER TABLE your_table_name ADD COLUMN new_column_name TEXT");
    }
}

