package dev.nxonxon.exampleview.authenticate.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.nxonxon.exampleview.authenticate.AuthenticateViewModel
import dev.nxonxon.exampleview.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    val viewModel: AuthenticateViewModel by lazy {
        ViewModelProviders
            .of(activity!!)
            .get(AuthenticateViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_login, container, false)
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this@LoginFragment
        binding.viewModel = viewModel
        return binding.root
    }

}