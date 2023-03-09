package com.atomic.commons.utils;

public class IncorrectConfigException extends Exception {

    public IncorrectConfigException(String missingArg) {

        super("Missing argument in configuration: " + missingArg);

    }
}
