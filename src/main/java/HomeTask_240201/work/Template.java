package HomeTask_240201.work;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.FileWriter;
import java.io.IOException;

public class Template {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) throws IOException {
        Template template = new Template();
        template.setAge(25);
        template.setName("Name");

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        FileWriter writer = new FileWriter("out.yaml");

        objectMapper.writeValue(writer, template);
        writer.close();

    }
}
