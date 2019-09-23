package com.savitoh.demoqrcodeapi.exceptions.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public final class CustomApiErroResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss",
                locale = "UTC-03", timezone="GMT-3")
    private LocalDateTime timestamp;
    private int codeStatus;
    private String status;
    private String error;

    private CustomApiErroResponse(int codeStatus, String status, String error) {
        this.timestamp = LocalDateTime.now();
        this.codeStatus = codeStatus;
        this.status = status;
        this.error = error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getCodeStatus() {
        return codeStatus;
    }

    public String getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public static class Builder {
        private int codeStatus;
        private String status;
        private String error;


        public Builder withCodeStatus(int codeStatus) {
            this.codeStatus = codeStatus;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder withError(String error) {
            this.error = error;
            return this;
        }

        public CustomApiErroResponse build() {
            return new CustomApiErroResponse(this.codeStatus, this.status, this.error);
        }
    }

    @Override
    public String toString() {
        return "ApiErroResponse{" +
                "timestamp=" + timestamp +
                ", codeStatus=" + codeStatus +
                ", status='" + status + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
