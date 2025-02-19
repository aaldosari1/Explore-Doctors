package com.example.demo.Evaluation;

import com.example.demo.Doctor.Doctor;
import com.example.demo.Doctor.DoctorRepository;
import com.example.demo.User.User;
import com.example.demo.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationServies {
    private final EvaluationRepository evaluationRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;


    @Autowired
    public EvaluationServies(EvaluationRepository evaluationRepository, DoctorRepository doctorRepository, UserRepository userRepository) {
        this.evaluationRepository = evaluationRepository;
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
    }

    public List<Evaluation> getEvaluaitons() {
        return evaluationRepository.findAll();
    }

    public Evaluation getEvaluaiton(String id) {
        Long evaluation_id = Long.parseLong(id);
        return evaluationRepository.findById(evaluation_id).orElse(null);
    }

    public Evaluation createEvaluation(Evaluation evaluation,Long docId,Long userId) {
        Doctor doctor = (Doctor) this.doctorRepository.findById(docId).orElse(null);
        evaluation.setDoct(doctor);
        System.out.println(docId);

        User user = (User) this.userRepository.findById(userId).orElse(null);
        evaluation.setUser(user);
        System.out.println(user);

        return evaluationRepository.save(evaluation);
    }


    public void deleteEvaluation(String id) {
        Long evaluation_id = Long.parseLong(id);
        evaluationRepository.deleteById(evaluation_id);
    }

    public void updateEvaluation(String id, Evaluation data) {
        Long evaluation_id = Long.parseLong(id);
        Evaluation evaluation = evaluationRepository.findById(evaluation_id).orElse(null);


        if (evaluation != null) {
            evaluation.setComment(data.getComment());
            evaluation.setCategory(data.getCategory());
            evaluation.setCategory(data.getCategory());
            evaluation.setEvaluateDate(data.getEvaluateDate());
            evaluation.setRate(data.getRate());
            evaluation.setAproved(data.isAproved());
            evaluationRepository.save(evaluation);
        }
    }

    public void deleteAllEvaluation() {
        evaluationRepository.deleteAll();
    }
}
