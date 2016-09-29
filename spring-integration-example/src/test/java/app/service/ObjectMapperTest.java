package app.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.dto.A;

public class ObjectMapperTest {
  
  private ObjectMapper mapper = new ObjectMapper();
  
  
  
  @Test
  public void test() throws IOException {
    Map<String, Object> map = new HashMap<>();
    map.put("a", 1);
    String jsonStr = mapper.writeValueAsString(map);
    System.out.println(jsonStr);
    Map r = mapper.readValue(jsonStr, Map.class);
    System.out.println(r);
    A a = mapper.convertValue(r, A.class);
    System.out.println(a.getA());
  }
}
