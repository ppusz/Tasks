package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TrelloDto {

    @JsonProperty("board")
    private int board;
    @JsonProperty("card")
    private int card;
}
