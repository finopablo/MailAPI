package edu.utn.mail.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponseDto {
    @JsonProperty
    int code;
    @JsonProperty
    String description;

    public ErrorResponseDto(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
