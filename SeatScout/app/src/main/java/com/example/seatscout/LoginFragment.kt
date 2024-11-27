package com.example.seatscout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.seatscout.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {
    private var binding: FragmentLoginBinding? = null
    private lateinit var auth: FirebaseAuth

    // Fragment가 생성될 때 호출
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

    //버튼이 눌렀을 때 설정
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.buttonLogin?.setOnClickListener {
            val email = binding?.editTextId?.text.toString()
            val password = binding?.editTextPassword?.text.toString()
            signIn(email, password)
        }

        binding?.buttonRegister?.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(action)
        }
    }

    private fun signIn(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "이메일과 비밀번호를 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // FirebaseAuth.getInstance().currentUser
                    Toast.makeText(context, "${auth.currentUser?.email}님 로그인 성공", Toast.LENGTH_SHORT).show()

                    val action = LoginFragmentDirections.actionLoginFragmentToStadiumListFragment()
                    // 뒤로 가는 스택 제거
                    findNavController().navigate(action, NavOptions.Builder().setPopUpTo(R.id.loginFragment, true).build())
                } else {
                    // 로그인 실패 시, task.exception?.message
                    Toast.makeText(context, "올바른 아이디와 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}