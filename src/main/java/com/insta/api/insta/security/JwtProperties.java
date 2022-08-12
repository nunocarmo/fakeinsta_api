package com.insta.api.insta.security;

public class JwtProperties {
    public static final String SECRET = "MindSwap2022";
    public static final int EXPIRATION_TIME = 854000000; //10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
