package dev.lumme.localstackdemo.service;

import software.amazon.awssdk.regions.Region;

import java.net.URI;

public final class Config {

    public static final Region DEFAULT_REGION = Region.EU_WEST_2;

    public static final URI ENDPOINT = URI.create("http://localhost:4566");
}
