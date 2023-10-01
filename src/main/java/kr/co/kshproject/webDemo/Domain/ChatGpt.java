package kr.co.kshproject.webDemo.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ChatGpt {
    @NotEmpty
    private String input;

    @NotEmpty
    private String apikey;
}
