package com.example.seatscout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.seatscout.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    var binding: FragmentRegisterBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater)

        // Inflate the layout for this fragment
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.buttonRegister?.setOnClickListener {
            val name = binding?.editTextName?.text.toString()
            val nickName = binding?.editTextNickname?.text.toString()
            val email = binding?.editTextEmail?.text.toString()
            val password = binding?.editTextPassword?.text.toString()
            val confirmPassword = binding?.editTextConfirmPassword?.text.toString()

            if (password == confirmPassword) {
                // TODO: 회원가입 처리 구현
            } else {
                // 비밀번호 불일치 처리 
            }


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}