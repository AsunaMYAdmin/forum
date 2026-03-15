package me.asunamyadmin.forumloregard.profile.exception;

public class AlreadyHaveThisStatusException extends RuntimeException {
    public AlreadyHaveThisStatusException() {
        super("This profile has already this status");
    }
}
