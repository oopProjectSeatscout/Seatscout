package com.example.seatscout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seatscout.databinding.FragmentSeatsBinding
import androidx.navigation.fragment.findNavController

class SeatsFragment : Fragment() {
    var binding: FragmentSeatsBinding? = null
    private val args: SeatsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSeatsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    fun setupRecyclerView() {
        val recyclerView = binding?.seatRecyclerView
        recyclerView?.layoutManager = LinearLayoutManager(context) // 세로 방향 리스트로 설정
        val adapter = SeatAdapter(getSeats(args.stadiumId)) { seat ->
            navSeatsToSeatReview(seat)
        }
        recyclerView?.adapter = adapter
    }

    private fun navSeatsToSeatReview(seat : Seat){
        val action = SeatsFragmentDirections.actionSeatsFragmentToSeatReviewFragment(
            stadiumId = args.stadiumId,
            seatName = seat.name
        )
        findNavController().navigate(action)
    }


    private fun getSeats(stadiumId: Int): Array<Seat> {
        // stadiumId에 따라 다른 좌석 정보를 반환
        return when (stadiumId) {
            1 -> arrayOf(
                Seat("프리미엄석", 16000),
                Seat("테이블석", 16000),
                Seat("익사이팅존", 9000),
                Seat("블루석", 9000),
                Seat("오렌지석", 9000),
                Seat("레드석", 9000),
                Seat("네이비석", 9000),
                Seat("그린석", 9000),
                Seat("휠체어석", 9000)
            )
            2 -> arrayOf(
                Seat("로얄 다이아몬드석", 15000),
                Seat("커플석", 15000),
                Seat("외야 일반석", 8000),
                Seat("지정석", 15000),
                Seat("버건디석", 15000),
                Seat("다크버건디석", 15000)
            )
            3 -> arrayOf(
                Seat("스카이박스", 72000),
                Seat("미니스카이박스", 67000),
                Seat("랜더스 라이브존", 63000),
                Seat("피코크테이블석", 49000),
                Seat("노브랜드 테이블석", 40000),
                Seat("내야패밀리존", 40000),
                Seat("바비큐존", 35000),
                Seat("이마트프렌들리존", 29000),
                Seat("외야패밀리존", 28000),
                Seat("홈런커플존", 27000),
                Seat("SKY탁자석", 27000),
                Seat("내야일반석", 15000),
                Seat("외야일반석", 14000)
            )
            4 -> arrayOf(
                Seat("챔피언석", 30000),
                Seat("테이블석", 25000),
                Seat("가족석", 15000),
                Seat("파티석", 12000),
                Seat("피크닉석", 8000),
                Seat("K석", 8000),
                Seat("스카이박스", 8000),
                Seat("휠체어석", 8000),
                Seat("서프라이즈석", 7000)
            )
            5 -> arrayOf(
                Seat("VIP석", 50000),
                Seat("테이블석", 45000),
                Seat("익사이팅석", 25000),
                Seat("블루존", 15000),
                Seat("3루 내야지정석", 13000),
                Seat("1루 내야지정석", 10000),
                Seat("외야지정석", 10000),
                Seat("외야패밀리석", 18000),
                Seat("외야테이블석", 72000),
                Seat("외야미니테이블석", 30000)
            )
            6 -> arrayOf(
                Seat("테이블석", 60000),
                Seat("하이파이브존", 35000),
                Seat("익사이팅존", 30000),
                Seat("지정석", 15000),
                Seat("스카이존", 10000),
                Seat("잔디자유석", 25000)
            )
            7 -> arrayOf(
                Seat("내야상단석", 50000),
                Seat("중앙상단석", 30000),
                Seat("외야석", 25000),
                Seat("G-라운드석", 12000),
                Seat("탁자석", 8000),
                Seat("3루단체석", 10000),
                Seat("로케트배터리존", 7000),
                Seat("롯데이노베이트존", 10000),
                Seat("내야필드석", 10000)
                )
            8 -> arrayOf(
                Seat("3층 테라스석", 55000),
                Seat("프리미엄석", 30000),
                Seat("테이블석", 25000),
                Seat("서브응원석", 14000),
                Seat("메인응원석", 9000),
                Seat("일반석", 22000),
                Seat("피크닉테이블석", 8000)
            )
            9 -> arrayOf(
                Seat("중앙특화석", 50000),
                Seat("내야특화석", 25000),
                Seat("외야특화석", 22000),
                Seat("내야지정석", 12000),
                Seat("외야지정석", 8000)
            )
            else -> arrayOf(
                Seat("1루 내야지정석", 15000),
                Seat("3루 내야지정석", 15000),
                Seat("외야 일반석", 8000)
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}