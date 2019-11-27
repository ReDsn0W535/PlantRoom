package com.example.plantroom.repository.firebase

import com.example.plantroom.repository.data.Result
import com.google.android.gms.tasks.Tasks
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject
import com.example.plantroom.utils.ImageUtils.Companion.toBitmap

class FirebaseCloudStorageRepository @Inject constructor(private var storage: FirebaseStorage) {
    suspend fun downloadFile(address: String): Result = withContext(Dispatchers.IO) {
        try {
            var file = File.createTempFile("Images", "png")
            var reference = storage.reference.child(address)
            var taskResult = Tasks.await(reference.getBytes(1024*1024))
            Result(sucess = true, result = taskResult.toBitmap(), errorMessage = null)
        } catch (e: Exception) {
            Result(sucess = false, result = null, errorMessage = e.message)
        }
    }




}