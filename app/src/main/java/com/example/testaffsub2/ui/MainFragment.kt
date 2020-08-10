package com.example.testaffsub2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testaffsub2.App
import com.example.testaffsub2.R
import com.example.testaffsub2.adapter.UserAdapter
import com.example.testaffsub2.databinding.FragmentMainBinding
import com.example.testaffsub2.model.Results
import com.example.testaffsub2.util.LoadMoreCallback
import com.example.testaffsub2.util.LoadMoreItemsRV


class MainFragment : Fragment(), UserAdapter.OnItemClick {

    private lateinit var binding: FragmentMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var fragmentActivity: FragmentActivity
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private var page: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentActivity = activity!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mainViewModel = ViewModelProvider(fragmentActivity).get(MainViewModel::class.java)
        binding = FragmentMainBinding.inflate(inflater, container, false).apply {
            viewModel = mainViewModel
            lifecycleOwner = this@MainFragment
        }
        userAdapter = UserAdapter(fragmentActivity, ArrayList(), this)
        recyclerView = binding.recyclerView
        val layoutManagerRV = LinearLayoutManager(fragmentActivity)
        recyclerView.apply {
            layoutManager = layoutManagerRV
            setHasFixedSize(true)
            adapter = userAdapter
        }
        //set context and create repository
        mainViewModel.init(activity?.application as App)
        //observer
        getListUsers()

        val loadMoreItemsRV = LoadMoreItemsRV(5)
        loadMoreItemsRV.setView(recyclerView)
        loadMoreItemsRV.setLoadMore(object : LoadMoreCallback {
            override fun loadMore() {
                mainViewModel.isLoader.value = true
                page += 1
                mainViewModel.getUsers(page)
            }

        })
        return binding.root
    }

    private fun getListUsers() {
        mainViewModel.getUsers(page)?.observe(fragmentActivity, Observer {
            mainViewModel.isLoader.value = false
            if (it != null) {
                userAdapter.addUsers(it.results)
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    override fun onClick(user: Results) {
        mainViewModel.userInfo = user
        fragmentActivity.supportFragmentManager.beginTransaction()
            .add(R.id.frame, ContentFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}