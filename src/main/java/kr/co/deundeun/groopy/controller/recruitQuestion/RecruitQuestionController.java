package kr.co.deundeun.groopy.controller.recruitQuestion;

import kr.co.deundeun.groopy.controller.recruitQuestion.dto.RecruitQuestionRequestDto;
import kr.co.deundeun.groopy.controller.recruitQuestion.dto.RecruitQuestionResponseDto;
import kr.co.deundeun.groopy.service.RecruitQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Past;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recruits/{recruitId}/questions")
public class RecruitQuestionController {

    private final RecruitQuestionService recruitQuestionService;

    @PostMapping
    public ResponseEntity<Void> createQuestions(@PathVariable Long recruitId,
                                                @RequestBody List<RecruitQuestionRequestDto> recruitQuestionRequestDtos) {
        recruitQuestionService.createQuestions(recruitId, recruitQuestionRequestDtos);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<RecruitQuestionResponseDto>> getQuestions(@PathVariable Long recruitId) {
        return ResponseEntity.ok(recruitQuestionService.getQuestions(recruitId));
    }

    @PatchMapping
    public ResponseEntity<Void> updateQuestions(@PathVariable Long recruitId,
                                                @RequestBody List<RecruitQuestionRequestDto> recruitQuestionResponseDtos) {
        recruitQuestionService.updateQuestions(recruitId, recruitQuestionResponseDtos);
        return ResponseEntity.ok().build();
    }
}
