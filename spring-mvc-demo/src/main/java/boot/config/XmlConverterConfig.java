package boot.config;

import java.io.Writer;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

@Configuration
public class XmlConverterConfig {
  
  @Bean
  public XStream xStream() {
    XStream magicApi = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("_-", "_")) {
      @Override
      public HierarchicalStreamWriter createWriter(Writer out) {
        return new PrettyPrintWriter(out, getNameCoder()) {
          private String PREFIX_CDATA = "<![CDATA[";
          private String SUFFIX_CDATA = "]]>";
          private String PREFIX_MEDIA_ID = "<MediaId>";
          private String SUFFIX_MEDIA_ID = "</MediaId>";

          @Override
          protected void writeText(QuickWriter writer, String text) {
            if (text.startsWith(PREFIX_CDATA) && text.endsWith(SUFFIX_CDATA)) {
              writer.write(text);
            } else if (text.startsWith(PREFIX_MEDIA_ID) && text.endsWith(SUFFIX_MEDIA_ID)) {
              writer.write(text);
            } else {
              writer.write(PREFIX_CDATA);
              writer.write(text);
              writer.write(SUFFIX_CDATA);
            }

          }
        };
      }
    });

    magicApi.registerConverter(new MapEntryConverter());
    magicApi.alias("xml", Map.class);
    return magicApi;
  }

  public static class MapEntryConverter implements Converter {

    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class clazz) {
      return AbstractMap.class.isAssignableFrom(clazz);
    }

    @SuppressWarnings("rawtypes")
    public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {

      AbstractMap map = (AbstractMap) value;
      for (Object obj : map.entrySet()) {
        Map.Entry entry = (Map.Entry) obj;
        writer.startNode(entry.getKey().toString());
        Object val = entry.getValue();
        if (null != val) {
          writer.setValue(val.toString());
        }
        writer.endNode();
      }

    }

    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {

      Map<String, String> map = new HashMap<String, String>();

      while (reader.hasMoreChildren()) {
        reader.moveDown();

        String key = reader.getNodeName(); // nodeName aka element's name
        String value = reader.getValue();
        map.put(key, value);

        reader.moveUp();
      }

      return map;
    }

  }
}
