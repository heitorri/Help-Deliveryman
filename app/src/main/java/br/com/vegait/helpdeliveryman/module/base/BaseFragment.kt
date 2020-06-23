package br.com.vegait.helpdeliveryman.module.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    open val viewModel: BaseViewModel? = null

    open val progressBar: ProgressBar? = null

    abstract fun getLayoutId(): Int

    private lateinit var mViewDataBinding: T

    fun getViewDataBinding(): T = mViewDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.subscribeErrorMessageLiveData()
        this.subscribeShowProgressBarLiveData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        bundle: Bundle?
    ): View? {
        return performDataBinding(inflater, container)
    }

    private fun performDataBinding(inflater: LayoutInflater, container: ViewGroup?): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mViewDataBinding.lifecycleOwner = this
        return mViewDataBinding.root
    }

    private fun subscribeShowProgressBarLiveData() {
        viewModel?.showProgressDialog?.observe(this, Observer {
            showProgressBar(it)
        })
    }

    private fun subscribeErrorMessageLiveData() {
        viewModel?.errorMessageLiveDate?.observe(this, Observer { errorMessageId ->
            showErrorMessage(errorMessageId)
        })

        viewModel?.errorMessageIdLiveDate?.observe(this, Observer {
            showErrorMessage(it)
        })
    }

    private fun showErrorMessage(errorMessageId: Int) {
        (this.context as BaseActivity<*>).showErrorMessage(errorMessageId)
    }

    private fun showErrorMessage(errorMessage: String) {
        (this.context as BaseActivity<*>).showErrorMessage(errorMessage)
    }

    fun navigate(navigate: NavDirections) {
        this.findNavController().navigate(navigate)
    }

    private fun showProgressBar(show: Boolean?) {
        when (show) {
            true -> {
                progressBar?.visibility = View.VISIBLE
            }
            false -> {
                progressBar?.visibility = View.GONE
            }
        }
    }
}