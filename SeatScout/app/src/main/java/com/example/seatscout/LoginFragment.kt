package com.example.seatscout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.seatscout.databinding.FragmentLoginBinding


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    var binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater)
        
        // Inflate the layout for this fragment
        return binding?.root
    }

    //버튼이 눌렀을 때 설정
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.buttonLogin?.setOnClickListener {
            val id = binding?.editTextId?.text.toString()
            val password = binding?.editTextId?.text.toString()
            // TODO: 로그인 처리 구현

            findNavController().navigate(R.id.action_entryFragment_to_loginFragment3)
        }

        binding?.editTextPassword?.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}