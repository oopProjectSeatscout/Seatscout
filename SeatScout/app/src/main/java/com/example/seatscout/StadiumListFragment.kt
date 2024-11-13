package com.example.seatscout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.seatscout.databinding.FragmentStadiumListBinding
import androidx.navigation.fragment.findNavController

class StadiumListFragment : Fragment() {
    var binding: FragmentStadiumListBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentStadiumListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupLoginButton()
    }

    private fun setupRecyclerView() {
        val recyclerView = binding?.stadiumRecyclerView
        recyclerView?.layoutManager = GridLayoutManager(context, 2) // 그리드 매니저로 2열로 바꿈
        val adapter = StadiumAdapter(getStadiums()){ stadium ->
            navToSeatsFragment(stadium)
        }// 람다함수를 사용하여 유용함, Stadium 타입의 매게변수를 받고 반환은 없이 navToSeatsFragment(stadium)를 호출, 어댑터에서 매게변수로 (Stadium) -> Unit 형태로 받음 (Unit은 void?)
        recyclerView?.adapter = adapter
    }

    private fun navToSeatsFragment(stadium: Stadium) {
        val action = StadiumListFragmentDirections.actionStadiumListFragmentToSeatsFragment(
            stadiumId = stadium.id,
            stadiumName = stadium.name
        )
        findNavController().navigate(action)
    }

    private fun setupLoginButton() {
        binding?.loginButton?.setOnClickListener {
            navToLoginFragment()
        }
    }

    private fun navToLoginFragment() {
        val action = StadiumListFragmentDirections.actionStadiumListFragmentToLoginFragment()
        findNavController().navigate(action)
    }

    private fun getStadiums(): Array<Stadium> {
        return arrayOf(
            Stadium(1, "서울 잠실야구장"),
            Stadium(2, "고척 스카이돔"),
            Stadium(3, "인천 랜더스필드"),
            Stadium(4, "광주 챔피언스필드"),
            Stadium(5, "대구 라이온즈파크"),
            Stadium(6, "수원 KT위즈파크"),
            Stadium(7, "부산 사직야구장"),
            Stadium(8, "창원 NC파크"),
            Stadium(9, "대전 이글스파크")
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}