package gb.spring.homework5.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.TextNode;
import gb.spring.homework5.model.Product;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

@JsonComponent
public class ProductDeserialiser extends JsonDeserializer<Product> {
    @Override
    public Product deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        BigInteger id = null;
        BigDecimal cost = null;
        String title = null;
        String producer = null;


        TreeNode node = treeNode.get("id");
        if (node != null) {
            id = new BigInteger(((NumericNode) node).asText());
        }
        node = treeNode.get("cost");
        if (node != null) {
            switch (node.numberType()){
                case DOUBLE:
                    cost = ((DoubleNode) node).decimalValue();
                    break;
                case INT:
                    cost = ((IntNode) node).decimalValue();
                default:
                    cost = ((NumericNode) node).decimalValue();
            }

        }
        node =  treeNode.get("title");
        if (node != null) {
            title = ((TextNode)node).asText();
        }
        node =  treeNode.get("producer");
        if (node != null) {
            producer =((TextNode)node).asText();
        }
        node = treeNode.get("code");
        if (node != null) {
            String[] tmp = ((TextNode)node).asText().split(" - ", 2);
            title = tmp[0];
            producer = tmp[1];
        }


        return new Product(id, title, cost, producer);
    }
}
