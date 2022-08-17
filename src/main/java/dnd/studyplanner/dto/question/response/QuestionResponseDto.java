package dnd.studyplanner.dto.question.response;

import java.util.List;

import dnd.studyplanner.dto.option.response.OptionResponseDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionResponseDto {
	private Long questionId;
	private String questionContent;
	private List<OptionResponseDto> optionList;

	@Builder
	public QuestionResponseDto(Long questionId, String questionContent, List<OptionResponseDto> optionList) {
		this.questionId = questionId;
		this.questionContent = questionContent;
		this.optionList = optionList;
	}
}
