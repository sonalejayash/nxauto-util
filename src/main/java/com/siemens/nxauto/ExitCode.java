package com.siemens.nxauto;

public enum ExitCode {

    SUCCESS(0),
    PROCESSING_ERROR(1),
    VALIDATION_ERROR(2),
    INVALID_ARGUMENTS(3);

    private final int code;

    ExitCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
