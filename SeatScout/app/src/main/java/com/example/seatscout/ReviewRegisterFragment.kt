package com.example.seatscout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.seatscout.databinding.FragmentReviewRegisterBinding
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.navigation.fragment.findNavController
import com.example.seatscout.model.Review
import com.example.seatscout.repository.ReviewRepository
import com.example.seatscout.viewmodel.ReviewViewModel
import com.google.firebase.auth.FirebaseAuth

class ReviewRegisterFragment : Fragment() {

    private var binding: FragmentReviewRegisterBinding? = null
    private val args: ReviewRegisterFragmentArgs by navArgs()
    private lateinit var reviewViewModel: ReviewViewModel
    private lateinit var auth: FirebaseAuth

    // Fragment가 생성될 때 호출
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentReviewRegisterBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 좌석 이름 설정
        binding?.subTitleTextView?.text = args.seatName // args에서 seatName 가져와서 설정

        // Repository를 직접 생성하여 ViewModel에 전달
        val repository = ReviewRepository()
        reviewViewModel = ReviewViewModel(repository)

        setupUI()
        observeViewModel()
    }

    private fun setupUI() {
        binding?.submitReviewButton?.setOnClickListener {
            submitReview()
        }
        binding?.uploadPhotoButton?.setOnClickListener {
            uploadPhoto()
        }
    }
    private fun navigateToReviewRegister() {
        val action = SeatReviewFragmentDirections.actionSeatReviewFragmentToReviewRegisterFragment(
            stadiumId = args.stadiumId,
            seatName = args.seatName // 여기서 seatName을 전달
        )
        findNavController().navigate(action)
    }


    private fun submitReview() {
        val rating = binding?.ratingBar?.rating ?: 0f
        val seatLocation = binding?.seatLocationText?.text.toString()
        val reviewContent = binding?.reviewContentText?.text.toString()
        val selectTagId = binding?.tagOptionsRadioGroup?.checkedRadioButtonId
        val userEmail = auth.currentUser?.email

        val selectTag = when (selectTagId) {
            R.id.tagOption1 -> "시야 좋은"
            R.id.tagOption2 -> "시야 가림"
            R.id.tagOption3 -> "편안한 관람"
            R.id.tagOption4 -> "가볍다"
            R.id.tagOption5 -> "중간 정도"
            else -> "태그 없음"
        }

        if (reviewContent.isNotBlank() && seatLocation.isNotBlank()) {
            // Review 객체 생성 시 stadiumId와 seatName을 args에서 가져오기
            val review = Review(
                stadiumId = args.stadiumId,
                seatName = args.seatName,
                seatLocation = seatLocation,
                rating = rating,
                content = reviewContent,
                tag = selectTag,
                userEmail = userEmail
            )

            // 리뷰 제출 로직
            reviewViewModel.submitReview(review)

            Toast.makeText(requireContext(), "리뷰가 제출되었습니다: ${args.stadiumId}, ${args.seatName}, $rating, $seatLocation, $reviewContent, $selectTag", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        } else {
            Toast.makeText(requireContext(), "좌석위치와 리뷰내용을 입력해주세요.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeViewModel() {
        reviewViewModel.submissionStatus.observe(viewLifecycleOwner) { status ->
            Toast.makeText(requireContext(), status, Toast.LENGTH_SHORT).show()
        }
    }

    private fun uploadPhoto() {
        // 사진 업로드 로직 추가 필요
        Toast.makeText(requireContext(), "사진 업로드 완료", Toast.LENGTH_SHORT).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}