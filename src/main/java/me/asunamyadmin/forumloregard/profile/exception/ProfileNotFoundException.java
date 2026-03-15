package me.asunamyadmin.forumloregard.profile.exception;

public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException() {
        super("Profile not found!");
    }
}
