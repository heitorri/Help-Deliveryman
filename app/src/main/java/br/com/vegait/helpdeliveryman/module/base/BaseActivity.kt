package br.com.vegait.helpdeliveryman.module.base

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer


abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    private lateinit var mViewDataBinding: T

    open val viewModel: BaseViewModel? = null

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        subscribeErrorMessageLiveData()
    }

    fun getViewDataBinding(): T = mViewDataBinding

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewDataBinding.executePendingBindings()
    }

    private fun subscribeErrorMessageLiveData() {
        viewModel?.errorMessageLiveDate?.observe(this, Observer { errorMessageId ->
            showErrorMessage(errorMessageId)
        })

        viewModel?.errorMessageIdLiveDate?.observe(this, Observer {
            showErrorMessage(it)
        })
    }

    fun showErrorMessage(errorMessageId: Int) {
        Toast.makeText(this, errorMessageId, Toast.LENGTH_LONG).show()
    }

    fun showErrorMessage(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }
}
