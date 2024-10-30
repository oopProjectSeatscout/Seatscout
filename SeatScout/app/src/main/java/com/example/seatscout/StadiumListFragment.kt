package com.example.seatscout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.seatscout.databinding.FragmentStadiumListBinding

class StadiumListFragment : Fragment() {
    var binding: FragmentStadiumListBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentStadiumListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    fun setupRecyclerView() {
        val recyclerView = binding?.stadiumRecyclerView
        recyclerView?.layoutManager = GridLayoutManager(context, 2) // 그리드 매니저로 2열로 바꿈
        val adapter = StadiumAdapter(getStadiums())
        recyclerView?.adapter = adapter
    }

    fun getStadiums(): Array<Stadium> {
        return arrayOf(
            Stadium("서울 잠실야구장"),
            Stadium("고척 스카이돔"),
            Stadium("인천 랜더스필드"),
            Stadium("광주 챔피언스필드"),
            Stadium("대구 라이온즈파크"),
            Stadium("수원 KT위즈파크"),
            Stadium("부산 사직야구장"),
            Stadium("창원 NC파크"),
            Stadium("대전 이글스파크")
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}