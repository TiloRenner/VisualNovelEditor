package sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

public class SimpleBeanTest {

    @Test
    public void whenJavaSerializedToXmlFile_thenCorrect() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File("simple_bean.xml"), new SimpleBean());
        File file = new File("simple_bean.xml");
        assertNotNull(file);

    }

    @Test
    public void whenJavaSerializedToJsonFile_thenCorrect() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(
                new FileOutputStream("output-2.json"),new SimpleBean());



       // XmlMapper xmlMapper = new XmlMapper();
       // xmlMapper.writeValue(new File("simple_bean.json"), new SimpleBean());
        File file = new File("output-2.json");
        assertNotNull(file);

    }



}