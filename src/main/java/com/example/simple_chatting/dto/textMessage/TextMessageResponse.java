package com.example.simple_chatting.dto.textMessage;

import com.example.simple_chatting.domain.text.Text;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TextMessageResponse {
    @NotNull
    private List<Text> textLogResponseLogs;

    public static TextMessageResponse of(List<Text> textLogs) {
        return new TextMessageResponse(textLogs);
    }
}
