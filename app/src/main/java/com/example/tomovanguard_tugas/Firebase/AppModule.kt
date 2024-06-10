package com.example.tomovanguard_tugas.Firebase

import android.content.Context
import androidx.room.Room
import com.example.tomovanguard_tugas.Retrofit.API
import com.example.tomovanguard_tugas.Retrofit.RetrofitInstance
import com.example.tomovanguard_tugas.RoomDB.Dao
import com.example.tomovanguard_tugas.RoomDB.MyDatabase
import com.example.tomovanguard_tugas.RoomDB.Repository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideAuthRepositoryImpl(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context): MyDatabase {
        return Room.databaseBuilder(
            context,
            MyDatabase::class.java,
            "local_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providedDataDao(db: MyDatabase): Dao = db.dao()

    @Provides
    @Singleton
    fun provideDataRepository(dao: Dao): Repository = Repository(dao)

    @Provides
    @Singleton
    fun provideQuoteApi(): API {
        return Retrofit.Builder()
            .baseUrl(RetrofitInstance.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(API::class.java)
    }
}