package com.example.seatscout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.seatscout.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    var binding: FragmentSettingsBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.myInfoLayout?.setOnClickListener {
            // 내 정보 화면으로 이동하는 로직
        }

        binding?.darkModeSwitch?.setOnCheckedChangeListener { _, isChecked ->
            // 다크 모드 설정 변경 로직
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}