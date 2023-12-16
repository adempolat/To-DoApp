package com.adempolat.to_doapp.data

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    localDataSource: LocalDataSource
){
    val localDataSource = localDataSource
}