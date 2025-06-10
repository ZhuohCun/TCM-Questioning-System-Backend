package com.InnovativePractice.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import lombok.Data;

@Data
public class HistoricalDialogueInfo {

    private Integer dialogueId;

    private Date date;

    private Integer sequenceId;

    private String question;

    private String responds;

    private Integer userId;


}
