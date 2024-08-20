package ru.gladyshev.springcourse.MyProjectPlusJwt.utils;

public class NoFoundUserException extends RuntimeException{
    public NoFoundUserException(String message) {
        super(message);
    }
}
