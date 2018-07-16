package sample;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import javafx.event.ActionEvent;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertNotNull;

public class Controller {
    public void pressButton(ActionEvent actionEvent)
    {
        System.out.println("Button Pressed");
        XmlMapper xmlMapper = new XmlMapper();

        //xmlMapper.writeValue(new File("simple_bean.xml"), new SimpleBean());
        File file = new File("simple_bean.xml");
        //assertNotNull(file);


    }




}
