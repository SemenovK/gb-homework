package gb.spring.homework4.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import gb.spring.homework4.model.Product;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.math.BigDecimal;

@JsonComponent
public class ProductDeserialiser extends JsonDeserializer<Product> {
    @Override
    public Product deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        BigDecimal cost;
        String[] tmp;

        TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        cost = new BigDecimal(((TextNode) treeNode.get("cost")).asDouble());
        tmp = ((TextNode) treeNode.get("code")).asText().split(" - ",2);
        return new Product(0,tmp[0], cost, tmp[1]);
    }
}
