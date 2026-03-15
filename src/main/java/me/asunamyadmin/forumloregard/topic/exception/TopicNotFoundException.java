package me.asunamyadmin.forumloregard.topic.exception;

public class TopicNotFoundException extends RuntimeException {
    public TopicNotFoundException() {
        super("Topic not found");
    }
}
