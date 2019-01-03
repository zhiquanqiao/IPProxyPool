package com.kteck.utils;

import org.seimicrawler.xpath.JXNode;

/**
 * interface parser
 */
public interface Parser {
    Object parse(String pattern, Object content);
}
