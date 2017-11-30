/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.wemakesites.em.bandschallenge.injection.module;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Factory;
import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory;

import com.squareup.sqlbrite3.BriteDatabase;
import com.squareup.sqlbrite3.SqlBrite;

import net.wemakesites.em.bandschallenge.data.local.DbCallback;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;

import javax.inject.Singleton;

@Module
public final class DbModule {
    @Provides
    @Singleton
    SqlBrite provideSqlBrite() {
        return new SqlBrite.Builder()
                .build();
    }

    @Provides
    @Singleton
    BriteDatabase provideDatabase(SqlBrite sqlBrite, Application application) {
        Configuration configuration = Configuration.builder(application)
                .name("band_search_history.db")
                .callback(new DbCallback())
                .build();
        Factory factory = new FrameworkSQLiteOpenHelperFactory();
        SupportSQLiteOpenHelper helper = factory.create(configuration);
        BriteDatabase db = sqlBrite.wrapDatabaseHelper(helper, Schedulers.io());
        db.setLoggingEnabled(true);
        return db;
    }
}
