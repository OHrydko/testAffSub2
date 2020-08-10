package com.example.testaffsub2.ui

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.testaffsub2.databinding.FragmentContentBinding
import com.example.testaffsub2.model.Results


class ContentFragment : Fragment() {

    private lateinit var binding: FragmentContentBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var fragmentActivity: FragmentActivity
    private lateinit var user: Results

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
        binding = FragmentContentBinding.inflate(inflater, container, false).apply {
            viewModel = mainViewModel
            lifecycleOwner = this@ContentFragment
        }
        user = mainViewModel.userInfo!!
        val name: String =
            user.name.first + " " + user.name.last

        binding.name.text = name
        binding.phone.text = user.phone
        val years: String = user.dob.age.toString() + " years old"
        binding.years.text = years
        Glide.with(this).load(user.picture.large)
            .apply(RequestOptions.circleCropTransform()).into(binding.userInfoAvatar)
        binding.email.text = user.email
        binding.call.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    fragmentActivity,
                    Manifest.permission.CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED
            ) {

                startPhoneCall()

            } else {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(
                        arrayOf(Manifest.permission.CALL_PHONE),
                        1
                    )
                } else {
                    ActivityCompat.requestPermissions(
                        fragmentActivity,
                        arrayOf(Manifest.permission.CALL_PHONE),
                        1
                    )
                }
            }
        }
        return binding.root
    }


    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>,
        grantResults: IntArray
    ) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startPhoneCall()
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun startPhoneCall() {
        val intent = Intent(
            Intent.ACTION_CALL,
            Uri.parse("tel:${user.phone}")
        )
        startActivity(intent)
    }

    companion object {

        @JvmStatic
        fun newInstance() = ContentFragment()
    }


}