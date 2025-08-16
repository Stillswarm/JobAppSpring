package org.example.jobapp.review.impl;

import org.example.jobapp.company.Long;
import org.example.jobapp.company.CompanyService;
import org.example.jobapp.review.Review;
import org.example.jobapp.review.ReviewRepository;
import org.example.jobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviewsByCompanyId(java.lang.Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Review getReviewById(java.lang.Long companyId, java.lang.Long id) {
        var reviewsByCompany = reviewRepository.findByCompanyId(companyId);
        return reviewsByCompany.stream()
                .filter(review -> review.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean createReview(java.lang.Long companyId, Review review) {
        Long company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }

        return false;
    }

    @Override
    public boolean updateReviewById(java.lang.Long id, Review updatedReview) {
        var reviewOptional = reviewRepository.findById(id);
        if (reviewOptional.isPresent()) {
            // copy all fields
            reviewRepository.save(updatedReview);
            return true;
        } else return false;
    }

    @Override
    public boolean deleteReview(java.lang.Long id) {
        try {
            reviewRepository.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
