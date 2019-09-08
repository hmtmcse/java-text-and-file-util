package com.hmtmcse.jtfutil.markdown;

import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.NodeRenderer;
import org.commonmark.renderer.html.*;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class PluginTest {

    public static void main(String[] args) {
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder()
                .nodeRendererFactory(new HtmlNodeRendererFactory() {
                    public NodeRenderer create(HtmlNodeRendererContext context) {
                        return new IndentedCodeBlockNodeRenderer(context);
                    }
                })
                .attributeProviderFactory(new AttributeProviderFactory() {
                    public AttributeProvider create(AttributeProviderContext context) {
                        return new ImageAttributeProvider();
                    }
                })
                .build();

        Node document = parser.parse("<div><p>ora manus nah</p><touhid> Touhid </touhid></div>");
        System.out.println(renderer.render(document));
    }
}


class IndentedCodeBlockNodeRenderer implements NodeRenderer {

    private final HtmlWriter html;

    IndentedCodeBlockNodeRenderer(HtmlNodeRendererContext context) {
        this.html = context.getWriter();
    }

    @Override
    public Set<Class<? extends Node>> getNodeTypes() {
        // Return the node types we want to use this renderer for.
        return Collections.<Class<? extends Node>>singleton(Paragraph.class);
    }

    @Override
    public void render(Node node) {
        // We only handle one type as per getNodeTypes, so we can just cast it here.
//        IndentedCodeBlock codeBlock = (IndentedCodeBlock) node;
//        html.line();
//        html.tag("pre");
//        html.text(codeBlock.getLiteral());
//        html.tag("/pre");
//        html.line();
        System.out.println("Asse");
        System.out.println(node);
    }
}


class ImageAttributeProvider implements AttributeProvider {
    @Override
    public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
        if (node instanceof Image) {
            attributes.put("class", "border");
        }
    }
}