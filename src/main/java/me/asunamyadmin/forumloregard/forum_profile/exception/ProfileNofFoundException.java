package me.asunamyadmin.forumloregard.forum_profile.exception;

public class ProfileNofFoundException extends RuntimeException {
    public ProfileNofFoundException() {
        super("Profile not found");
    }
}
