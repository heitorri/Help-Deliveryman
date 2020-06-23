package br.com.vegait.helpdeliveryman.module.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.vegait.helpdeliveryman.R
import br.com.vegait.helpdeliveryman.data.model.Error
import br.com.vegait.helpdeliveryman.domain.exception.NoContentException
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    val errorMessageIdLiveDate = MutableLiveData<Int>()
    val errorMessageLiveDate = MutableLiveData<String>()
    val showProgressDialog = MutableLiveData(false)

    private val executionJob: Job by lazy { Job() }

    override val coroutineContext: CoroutineContext by lazy {
        Dispatchers.Default + executionJob
    }

    override fun onCleared() {
        executionJob.cancel()
        super.onCleared()
    }

    protected fun handleError(error: Exception) {
        when (error) {
            is HttpException -> handleHttpException(error)
            is NoContentException -> onNoContentErrorError()
            else -> onUnknownError(error)
        }
    }

    protected open fun handleHttpException(httpError: HttpException) {
        errorMessageLiveDate.value = getErrorMessageFromHttpError(httpError) ?: ""
    }

    private fun getErrorMessageFromHttpError(httpError: HttpException): String? {

        val strErrorBody = httpError.response()?.errorBody()?.string() ?: return null

        return try {
            val error = Gson().fromJson(strErrorBody, Error::class.java)

            error.errorMessage

        } catch (error: Exception) {
            onUnknownError(error)
            return null
        }
    }


    private fun onUnknownError(error: Exception) {
        error.printStackTrace()
        errorMessageIdLiveDate.value = R.string.default_error
    }

    private fun onNoContentErrorError() {
        errorMessageIdLiveDate.value = R.string.error_204
    }
}