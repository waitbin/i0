package com.thoughtworks.i0.samples.blog;

import com.thoughtworks.i0.Application;

public class BlogApplication extends Application {
    {
        name("blog");
        db("jdbc:h2:mem", "org.h2.Driver");

        persistence("domain");
        api("com.thoughtworks.i0.samples.blog.api");
    }

    public static void main(String... arguments) throws Exception {
        new BlogApplication().standalone();
    }
}