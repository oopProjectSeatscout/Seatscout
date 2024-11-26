package com.example.seatscout

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.seatscout.databinding.FragmentSeatsBinding
import androidx.navigation.fragment.findNavController
import com.example.seatscout.model.Review
import com.example.seatscout.repository.ReviewRepository
import com.example.seatscout.viewmodel.ReviewViewModel

class SeatsFragment : Fragment() {
    var binding: FragmentSeatsBinding? = null
    private val args: SeatsFragmentArgs by navArgs()
    private lateinit var reviewViewModel: ReviewViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSeatsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reviewViewModel = ReviewViewModel(ReviewRepository()) // ViewModel 초기화
        setupRecyclerView()

        // 옵저버를 한 번만 등록
        reviewViewModel.reviews.observe(viewLifecycleOwner) { reviews ->
            Log.d("SeatsFragment", "Fetched reviews: ${reviews.size}") // 전체 리뷰 개수 로그
            updateSeatReviews(reviews) // 리뷰 업데이트 호출
        }

        fetchReviews() // 리뷰 가져오기
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
                Seat("프리미엄석", 3),
                Seat("테이블석", 0),
                Seat("익사이팅존", 0),
                Seat("블루석", 0),
                Seat("오렌지석", 0),
                Seat("레드석", 0),
                Seat("네이비석", 0),
                Seat("그린석", 0),
                Seat("휠체어석", 0)
            )
            2 -> arrayOf(
                Seat("로얄 다이아몬드석", 0),
                Seat("커플석", 0),
                Seat("외야 일반석", 0),
                Seat("지정석", 0),
                Seat("버건디석", 0),
                Seat("다크버건디석", 0)
            )
            3 -> arrayOf(
                Seat("스카이박스", 0),
                Seat("미니스카이박스", 0),
                Seat("랜더스 라이브존", 0),
                Seat("피코크테이블석", 0),
                Seat("노브랜드 테이블석", 0),
                Seat("내야패밀리존", 0),
                Seat("바비큐존", 0),
                Seat("이마트프렌들리존", 0),
                Seat("외야패밀리존", 0),
                Seat("홈런커플존", 0),
                Seat("SKY탁자석", 0),
                Seat("내야일반석", 0),
                Seat("외야일반석", 0)
            )
            4 -> arrayOf(
                Seat("챔피언석", 0),
                Seat("테이블석", 0),
                Seat("가족석", 0),
                Seat("파티석", 0),
                Seat("피크닉석", 0),
                Seat("K석", 0),
                Seat("스카이박스", 0),
                Seat("휠체어석", 0),
                Seat("서프라이즈석", 0)
            )
            5 -> arrayOf(
                Seat("VIP석", 0),
                Seat("테이블석", 0),
                Seat("익사이팅석", 0),
                Seat("블루존", 0),
                Seat("3루 내야지정석", 0),
                Seat("1루 내야지정석", 0),
                Seat("외야지정석", 0),
                Seat("외야패밀리석", 0),
                Seat("외야테이블석", 0),
                Seat("외야미니테이블석", 0)
            )
            6 -> arrayOf(
                Seat("테이블석", 0),
                Seat("하이파이브존", 0),
                Seat("익사이팅존", 0),
                Seat("지정석", 0),
                Seat("스카이존", 0),
                Seat("잔디자유석", 0)
            )
            7 -> arrayOf(
                Seat("내야상단석", 0),
                Seat("중앙상단석", 0),
                Seat("외야석", 0),
                Seat("G-라운드석", 0),
                Seat("탁자석", 0),
                Seat("3루단체석", 0),
                Seat("로케트배터리존", 0),
                Seat("롯데이노베이트존", 0),
                Seat("내야필드석", 0)
                )
            8 -> arrayOf(
                Seat("3층 테라스석", 0),
                Seat("프리미엄석", 0),
                Seat("테이블석", 0),
                Seat("서브응원석", 0),
                Seat("메인응원석", 0),
                Seat("일반석", 0),
                Seat("피크닉테이블석", 0)
            )
            9 -> arrayOf(
                Seat("중앙특화석", 0),
                Seat("내야특화석", 0),
                Seat("외야특화석", 0),
                Seat("내야지정석", 0),
                Seat("외야지정석", 0)
            )
            else -> arrayOf(
                Seat("1루 내야지정석", 0),
                Seat("3루 내야지정석", 0),
                Seat("외야 일반석", 0)
            )
        }
    }

    private fun fetchReviews() {
        reviewViewModel.fetchAllReviews(args.stadiumId) // 모든 리뷰 가져오기
    }


    private fun updateSeatReviews(reviews: List<Review>) {
        val seats = getSeats(args.stadiumId)
        val reviewCounts = mutableMapOf<String, Int>() // 좌석별 리뷰 개수를 저장할 맵

        // 리뷰를 좌석별로 집계
        seats.forEach { seat ->
            val seatReviews = reviews.filter { it.seatName == seat.name } // 해당 좌석의 리뷰 필터링
            val newReviewCount = seatReviews.size // 새로운 리뷰 개수
            reviewCounts[seat.name] = newReviewCount // 리뷰 개수 저장
        }

        // 모든 좌석의 리뷰 개수를 한 번에 로그로 출력
        reviewCounts.forEach { (seatName, count) ->
            Log.d("SeatsFragment", "Seat: $seatName, Reviews: $count") // 각 좌석의 리뷰 개수 로그
        }

        // 어댑터에 데이터 업데이트
        reviewCounts.forEach { (seatName, count) ->
            (binding?.seatRecyclerView?.adapter as? SeatAdapter)?.updateSeatReviewCount(seatName, count)
        }
    }




    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}