package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

public class ReadRevisionUserTest  {

    @Test
    public void revisionUserTest() throws IOException {
        String result = parse(readSampleFileAsString());
        Assertions.assertEquals( "ShadowDragon343" , result);
    }


    private InputStream readSampleFileAsString() throws NullPointerException, IOException {
        InputStream sampleFile = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        return new InputStream(Objects.requireNonNull(sampleFile).readAllBytes(), Charset.defaultCharset());
    }

//    private JSONArray getRevisionsFromJson(String jsonData) {
//        return JsonPath.read(jsonData, "$..revisions[*]");
//    }
}
