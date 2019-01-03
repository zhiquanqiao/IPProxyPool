package com.kteck.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;

import java.util.List;

/**
 * XPathParser
 *
 * @author qiaozhiquan
 */
public class XPathParser implements Parser {

    @Override
    public Object parse(String pattern, Object content) {
        Object result = null;
        if (content instanceof String) {
            Document document = Jsoup.parse(String.valueOf(content), "utf-8");
            JXDocument jxd = new JXDocument(document.children());
            List<JXNode> nodes = jxd.selN(pattern);
            result = nodes;
        } else if (content instanceof JXNode) {
            result = ((JXNode) content).sel(pattern).get(0).asElement().text();
        } else {
            throw new RuntimeException("type not support exception");
        }

        return result;
    }

}
